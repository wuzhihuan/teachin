package com.dizhejiang.teachin.vo;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Data
public class PositionVo {
    //用户id
    private Integer  userId;
    //投递时间
    private String  deliveryTime;
    //投递状态
    private String status;
    //职务
    private String position;
    //工作性质
    private String  workType;
    //薪资
    private String salaryMin;
    //薪资
    private String salaryMax;
    //部门
    private String part;
    //招聘人数
    private String num;
    //工作地点
    private String workPlace;
    //公司id
    private Integer  companyId;
    //公司名字
    private String  companyName;
    //职位发布时间
    private String createTime;

    private Integer positionId;

   private Integer views;
    private Integer deliverNum;
    private Integer pageSize;
    private Integer pageNum;
}
