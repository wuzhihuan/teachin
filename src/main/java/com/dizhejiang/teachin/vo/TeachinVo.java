package com.dizhejiang.teachin.vo;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Data
public class TeachinVo {
    private  Integer id;
    private  Integer teachinId;
    private  Integer userId;
    private  String   title;
    private  String  place;
    private  String  schoolName;
    private  String  happenTime;
    private  String  schoolUrl;
    //05取消
    private  String  status;

    private Integer opUserId;

    //报名人数
    private Integer enrollNum;
    //点击人数
    private Integer lookNum;
    //关注人数
    private  Integer foocusNum;
    //本地报名
    private Integer localEnrollNum;
    //外地报名数
    private Integer otherEnrollNum;

    private String isEnd;

    private String isEnroll;
    //操作时间
    private String operateTime;
    private String createTime;
}
