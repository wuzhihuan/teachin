package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/23
 * 宣讲会数据
 */
@Data
public class TeachinData {

    private Integer id;
    //宣讲会id
    private  Integer teachinId;
    //报名人数
    private Integer enrollNum;
    //查看人数
    private Integer lookNum;
    //关注人数
    private Integer foocusNum;
    //本地报名数
    private Integer localEnrollNum;
    //外地报名数
    private Integer otherEnrollNum;
    private String status;
    private String updateTime;

}
