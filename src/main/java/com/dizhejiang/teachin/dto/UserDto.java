package com.dizhejiang.teachin.dto;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Data
public class UserDto {
    //code
    private String code;
    //邮箱
    private String email;
//微信号
    private String wxCode;
//手机号
    private String mobile;
    //昵称
    private String userName;
    //头像
    private String head;
//向别
    private String sex;
    //地址
    private String address;

    private Integer userId;
//获取手机号用（加密）
    private String encryptedData;
    //获取手机号用（加密）
    private String iv;

    private String inviteCode;
}
