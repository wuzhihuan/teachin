package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 * 简历表
 */
@Data
public class Vitae {
    private  Integer id;
    //用户id
    private Integer userId;

    private String sex;

    private String birthday;

    private String name;

    private String education;//最高学历

    private String discipline;//专业

    private  String degree;//学位

    private Integer schoolId;

    private  String schoolName;

    private String createTime;

    private  String updateTime;
    //状态，01可见，02不可见
    private  String status;
    private String phone;
    private String email;
    //院系
    private String department;
    //入学时间
    private String enterDate;
    //毕业时间
    private String leaveDate;
    //自我介绍
    private String detail;

    private Integer age;


}
