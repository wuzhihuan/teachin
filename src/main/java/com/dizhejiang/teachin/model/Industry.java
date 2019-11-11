package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Data
public class Industry {
    private Integer industryId;
    private String industryName;
    private  Integer pid;
    //级别:0-最高级1-子级
    private Integer level;
}
