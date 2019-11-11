package com.dizhejiang.teachin.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
@Data
public class GroupAttestationVo {
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
    private List<String> prove;
    //
    private  String phone;
    private String department;
}
