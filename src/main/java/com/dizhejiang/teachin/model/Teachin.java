package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/21
 * 宣讲会
 */
@Data
public class Teachin {

    private Integer id;
    //标题
    private String title;
    //企业id
    private Integer companyId;
    //企业名称
    private String companyName;
    //学校id
    private Integer schoolId;
    //地点
    private String place;
    //简介
    private String introduction;
    //时间
    private  String happenTime;
    //分公司
    private String subCompany;
    //专业信息
    private String disciplineInfo;
    //行业信息
    private String industryInfo;
    //企业方联系人
    private String companyContractName;
    //企业方联系人电话
    private String companyContractPhone;
    //创建者id
    private Integer opUserId;
    //草稿01，待审02，已审03，失效04,05取消
    private String status;
    //行业id
    private Integer industryId;

    private String industry;
    //学校
    private  String schoolName;
    //校徽
    private  String schoolUrl;

    private  String createTime;
    //是否所有人可见01是，02否
    private String isPublic;
    //是否已报名01报名，02未报名
    private String isEnroll;
    //是否收藏01收藏，02否
    private String isCollect;
    //是否结束01结束，02未结束
    private String isEnd;
    private String credit;

    private String operateTime;

    /**
     * 浏览相关数据
     */
    private  Integer  enrollNum;
    private  Integer lookNum;
    private  Integer foocusNum;
    private  Integer localEnrollNum;
    private  Integer otherEnrollNum;
    //01标识可以取消，02不可以取消
    private  String isCancle;

    private Integer orderEnd;

}
