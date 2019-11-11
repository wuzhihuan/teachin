package com.dizhejiang.teachin.model;

import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/28
 */
@Data
public class TbPosition {
    private Integer id;//level:0-父节点1-子节点
    private String  position;//职位名称
    private Integer level;//level:0-父节点1-子节点
    private Integer pid;//父id
    private  Integer sort;//排序
}
