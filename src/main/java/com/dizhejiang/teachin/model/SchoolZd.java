package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/25
 * 指点的学校导入到我们库
 */
@Data
public class SchoolZd {
    private  Integer schoolId;
    //学校名字
    private String schoolName;
//省
    private String province;
//识别码
    private String target;
    //城市
    private String city;
}
