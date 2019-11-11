package com.dizhejiang.teachin.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dizhejiang.teachin.Service.UserService;
import com.dizhejiang.teachin.common.*;
import com.dizhejiang.teachin.config.RedisConst;
import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.dto.UserDto;
import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.mapper.ActionLogMapper;
import com.dizhejiang.teachin.mapper.UserMapper;
import com.dizhejiang.teachin.model.ActionLog;
import com.dizhejiang.teachin.model.User;
import com.dizhejiang.teachin.vo.LoginCacheVo;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import static com.dizhejiang.teachin.common.RandomUtils.*;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ActionLogMapper actionLogMapper;

    /**
     * 登录
     * @param dto
     * @return
     */
    @Override
    public ResponseVo login(UserDto dto) {
        String appid="wx10bc6dd25f760c1b";
        String secret="a07440faa40636ac6fbb5c31c29e0564";
        //逻辑处理
        getOpenIdutil getopenid=new getOpenIdutil();
        String jsonId=getopenid.getopenid(appid,dto.getCode(),secret);
        JSONObject jsonObject = JSONObject.parseObject(jsonId);

        //从json字符串获取openid和session_key
        String openid=jsonObject.getString("openid");
        String session_key=jsonObject.getString("session_key");
       // String access_token=jsonObject.getString("access_token");
        //通过openid来查询
        User user =  userMapper.selectUserByOpenId(openid);
        User user1 = new User();
        User userCode = userMapper.selectModelByCode(dto.getInviteCode());
        //没有登陆过
        String  json = WechatDecryptDataUtil.decryptData(dto.getEncryptedData(),session_key,dto.getIv());
        JSONObject jsonObject1 = JSONObject.parseObject(json);
        if(user==null){
            //获取手机号
            String phone =jsonObject1.getString("phoneNumber");
            user1.setMobile(phone);
            user1.setWxAuthcode(openid);
            user1.setStatus("00");
            user1.setUserType("01");
            user1.setWxAuthcode(openid);
            user1.setCreateTime(DateUtil.DateToString(new Date()));
            String userName = "";
            try {
                userName=    URLEncoder.encode(dto.getUserName(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            user1.setUserName(userName);
            user1.setSex(dto.getSex());
            user1.setHead(dto.getHead());
            user1.setInviteCode( RandomUtils.genRandomNum());
            if(userCode==null|| "".equals(dto.getInviteCode()) || dto.getInviteCode()==null){
                user1.setParentId(0);
            }else{
                user1.setParentId(userCode.getId());
            }
            userMapper.save(user1);
        }
        //可能是更新，之前没有保存
        User user2 =  userMapper.selectUserByOpenId(openid);
        if(user!=null){
            String phone =jsonObject1.getString("phoneNumber");
            user2.setMobile(phone);
            user2.setHead(dto.getHead());
            user2.setUserName(dto.getUserName());
            user2.setSex(dto.getSex());
            userMapper.update(user2);
        }
        //更新用户不在登录处理
        UserVo userVo = new UserVo();
        userVo.setUserType(user2.getUserType());
        String token=UUID.randomUUID().toString();
        userVo.setToken(token);

         //=====================现在不保存对象========

        //BeanUtils.copyProperties(user2, userVo);
       // String token = JWTUtil.getJWT(userVo.toJwtMap());
        //保存token到缓存
        //LoginCacheVo loginCacheVo = new LoginCacheVo();
        //loginCacheVo.setUserId(user2.getId());
        //loginCacheVo.setUserVo(userVo);
//=====================现在不保存对象========

        //String loginCache = user2.getId().toString();
        String  loginCache= null;
        try {
            loginCache = URLEncoder.encode(user2.getId().toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //缓存
        boolean a = redisUtil.set(token, loginCache, RedisConst.DEFAULT_EXPIRAION_TIME);
        if (!a) {
            throw new ResponseException(ResponseEnum.ERROR);
        }
        //Integer userId,String logType,Integer source,String operate
        ActionLog actoinlog =  SaveActionLog.SaveActionLog(user2.getId(),"30",user2.getId(),"登录操作");
        actionLogMapper.save(actoinlog);
        return ResponseVo.success(userVo);
    }

    /**
     * 绑定邮箱
     * @param email
     * @param userId
     * @return
     */
    @Override
    public ResponseVo bindEmail(String email, Integer userId) {
        User user =userMapper.selectUserById(userId);
        if(StringUtils.isEmpty(user.getEmail())){
            userMapper.updateEmail(email,userId);
            return ResponseVo.success("绑定邮箱成功");
        }else{
            userMapper.updateEmail(email,userId);
            return ResponseVo.success("更新邮箱成功");
        }

    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getUserInfo(Integer userId) {
        User user =  userMapper.selectUserById(userId);
        String userName = "";
        try {
            userName =   URLDecoder.decode(user.getUserName(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setUserName(userName);
        return  ResponseVo.success(user);
    }

    /**
     * 绑定手机号
     * @param dto
     * @return
     */
    @Override
    public ResponseVo bindPhone(UserDto dto) {
        userMapper.updatePhone(dto);
        return ResponseVo.success("绑定手机成功");
    }
}
