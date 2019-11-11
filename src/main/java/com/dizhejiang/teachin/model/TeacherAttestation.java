package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/23
 * 老师认证
 */
@Data
public class TeacherAttestation {
    private Integer id;
    private Integer schoolId;
    private String schoolName;
    //身份证号码
    private String idNo;
    //工号
    private String jobNo;
    //职务
    private String position;
    //状态01待审核,已审核02
    private String status;
    //文件地址
    private  String materialUrl;
    private String name;
    private  Integer userId;
    private  String department;
}
