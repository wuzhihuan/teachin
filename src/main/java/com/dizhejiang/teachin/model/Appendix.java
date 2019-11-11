package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/29
 * 附件表
 */
@Data
public class Appendix {
    private  Integer id;
    private String url;
    private Integer  source;
    //来源，教师认证10，企业认证20，社群认证30
    private String sourceType;
}
