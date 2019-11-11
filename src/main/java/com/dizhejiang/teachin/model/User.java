package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/21
 * 用户模型
 */
@Data
public class User {
    //id
    private Integer id;
    //微信号
    private  String wxCode;
    //手机号
    private String mobile;
    //微信识别号
    private String wxAuthcode;
    //学校名称
    private String schoolName;
    //用户类型，普户01，校户02，企户03,学校社群用户04
    private String userType;
    //邮箱
    private String email;
    //企业id
    private Integer companyId;
    //社群id
    private Integer groupId;
    //学校id
    private Integer schoolId;
    //推荐人id
    private Integer parentId;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //状态01有效，02无效
    private String status;

    //昵称
    private String userName;
    //头像
    private String head;
    //向别
    private String sex;
    //地址
    private String address;

    private String companyName;

    private String inviteCode;

    private String credit;
}
