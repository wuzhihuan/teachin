package com.dizhejiang.teachin.model;

import lombok.Data;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/11/4
 * 反馈与建议
 */
@Data
public class FeedBack {
    private Integer id;
//描述
    private String detailed;

    private Integer userId;

    private List<String> url;
    //01待处理，02已处理
    private String status;
    private String createTime;
}
