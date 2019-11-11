package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.dto.UserDto;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface UserService {
    /**
     * 登录
     * @param dto
     * @return
     */
    ResponseVo login(UserDto dto);

    /**
     * 绑定邮箱
     * @param email
     * @param userId
     * @return
     */
    ResponseVo bindEmail(String email,Integer userId);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    ResponseVo getUserInfo(Integer userId);

    /**
     * 绑定手机
     * @return
     */
    ResponseVo bindPhone(UserDto dto);
}
