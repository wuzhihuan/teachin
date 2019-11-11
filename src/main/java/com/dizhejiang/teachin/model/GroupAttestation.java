package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/23
 * 社群认证
 */
@Data
public class GroupAttestation {
    private Integer id;
    private  Integer schoolId;
    private  String schoolName;
    //姓名
    private String name;
    //身份证号码
    private  String idNo;
    //学号
    private String sno;
    //状态01待审核，02审核通过
    private String status;
    private  Integer userId;
    //证明材料
    private String prove;
    //
    private  String phone;

    private String department;
}
