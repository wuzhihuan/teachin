package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 * 公司表
 */
@Data
public class Company {
    private  Integer id;
    //公司名字
    private  String companyName;
    //代码
    private String companyCode;
    //营业执照图
    private String certificate;
    //类别
    private String companyType;
    //分公司
    private String depart;
    //联系人
    private String contractName;
    //手机号
    private String mobile;
    //行业
    private String industry;
    //状态 待审核01，有效02，付费用户03，失效04
    private String status;

}
