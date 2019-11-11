package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 * 投递表
 */
@Data
public class Deliver {
    private Integer id;
    //职位id
    private  Integer positionId;
    //用户id
    private Integer userId;
    //投递时间
    private String deliveryTime;
    //状态（已投01，已接受02，已拒03，进一步安排面试04
    private String status;
}
