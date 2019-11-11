package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
@Data
public class ErrorLogs {
    private Integer id;
    private Integer userId;
    private String method;
    private String errorType;
    private String errorInfo;
    private String createTime;
}
