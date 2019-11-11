package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/11/5
 * 收藏职位
 */
@Data
public class CollectPosition {
    private  Integer id;
    //职位id
    private Integer positionId;
    //01收藏，02取消收藏
    private String status;
    //收藏时间
    private String createTime;
    private Integer userId;
}
