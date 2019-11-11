package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Data
public class WorkExperience {
    //id
    private Integer id;
    //用户id
    private  Integer userId;
    private Integer companyId;
    private String companyName;
    //企业性质
    private String companyType;
    //企业规模
    private String scope;
    //部门
    private String part;
    //职位
    private String position;
    private String enterDate;
    private  String leaveDate;
    //月薪
    private String salary;
    //职责
    private String duty;
    //证明人
    private String voucherName;
    //证明人电话
    private String voucherPhone;
    //个人描述
    private String detail;
    //01有效，02无效
    private String status;
}
