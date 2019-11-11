package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/21
 * 地区模型
 */
@Data
public class Area {
    //id
    private Integer areaId;
    //名字
    private String areaName;
    //pid
    private Integer pid;
    //0省，1非省
    private Integer level;
}
