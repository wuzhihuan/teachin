package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Data
public class ItemExperience {
    private Integer id;
    private Integer userId;
    private Integer companyId;
    private String companyName;
    //项目名
    private String projectName;
    //项目简介
    private String introduction;
    //自责
    private String duty;
    //成果
    private String achievement;
    //状态
    private String status;
    private String startTime;
    private String endTime;
    //项目描述
    private String description;
}
