package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 * 宣讲会收藏
 */
@Data
public class Collect {
    private Integer id;
    //宣讲会id
    private Integer teachinId;
    //用户id
    private  Integer userId;
    //状态01收藏，02取消
    private  String status;
}
