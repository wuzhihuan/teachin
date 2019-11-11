package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 * 宣讲会报名表
 */
@Data
public class Enroll {
    private Integer id;
    //宣讲会id
    private  Integer teachinId;
    //用户id
    private  Integer userId;
    //状态（已报01，取消02）
    private String status;
    private String createTime;
}
