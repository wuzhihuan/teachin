package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/21
 * 学校表
 */
@Data
public class School {
    //id
    private  Integer id;
    //学校名称
    private  String schoolName;
    //学校代码
    private  String schoolCode;
    //分部名
    private  String subName;
    //logo
    private String logo;
    //联系人姓名
    private String contractName;
    //联系人手机号
    private String contractPhone;
    //联系人微信
    private  String contractWx;
    //所属省份
    private String province;

    private String city;
}
