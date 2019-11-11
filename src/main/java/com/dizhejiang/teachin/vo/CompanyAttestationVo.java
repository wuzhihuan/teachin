package com.dizhejiang.teachin.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
@Data
public class CompanyAttestationVo {
    private  Integer id;
    private Integer companyId;
    private String companyName;
    //信用代码
    private String credit;
    private String name;
    //身份证号码
    private String idNo;
    //职务
    private String position;
    //文件
    private List<String> materialUrl;
    private  Integer userId;
    //01待审核，02审核通过
    private String status;
    //行业
    private String industry;
}
