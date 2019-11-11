package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.UserService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.UserDto;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 登录接口
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public ResponseVo login(@RequestBody @Validated UserDto dto){
        return userService.login(dto);
    }

    /**
     * 绑定邮箱
     * @param dto
     * @return
     */
    @RequestMapping(value = "/bindEmail",method = {RequestMethod.POST})
    public ResponseVo bindEmail(@RequestBody @Validated UserDto dto){
        return userService.bindEmail(dto.getEmail(), AuthenticationUtil.getUserId());
    }
    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/getUserInfo",method = {RequestMethod.POST})
    public ResponseVo getUserInfo(){
        return userService.getUserInfo(AuthenticationUtil.getUserId());
    }

    /**
     * 更新手机号码
     */
    @RequestMapping(value = "/bindPhone",method = {RequestMethod.POST})
    public ResponseVo bindPhone(@RequestBody @Validated UserDto dto){
        dto.setUserId(AuthenticationUtil.getUserId());
        return userService.bindPhone(dto);
    }

}
