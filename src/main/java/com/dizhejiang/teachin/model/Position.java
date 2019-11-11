package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 * 职位表
 */
@Data
public class Position {
    private Integer id;
    //职位名称
    private String position;
    //工作性质全职01，兼职02，实习03
    private String workType;
    //薪资
    private String salaryMin;
    private String salaryMax;
    //部门
    private String part;
    //人数
    private String num;
    //工作地点
    private String workPlace;
    //状态01待开放，02已开放
    private String status;
    //公司id
    private Integer companyId;
    //公司名字
    private String companyName;
    private String createTime;
    //浏览数
    private Integer views;
    //投递数
    private Integer deliverNum;
    private Integer userId;
    //描述
    private String description;
    //01投，02未投
    private String isDeliver;
//01关闭，02不关闭,null不关闭
    private String isClose;
//是否收藏01，02
    private String isCollect;

    private String credit;
}
