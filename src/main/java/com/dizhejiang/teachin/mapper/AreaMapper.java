package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Area;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface AreaMapper {
    /**
     * 获取省份类表
     * @return
     */
   List<Area> getProvinceList();
}
