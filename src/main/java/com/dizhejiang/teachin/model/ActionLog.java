package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
@Data
public class ActionLog {
    private Integer id;
    private Integer userId;
    private String createTime;
    //错误类型，10投递，20报名，30登录，40发布宣讲会，50发布职位
    private String logType;
    //来源，主表id
    private Integer source;
    //操作
    private String operate;
}
