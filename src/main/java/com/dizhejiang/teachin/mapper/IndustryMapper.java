package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Industry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface IndustryMapper {
    /**
     * 获取行业列表
     * @return
     */
    List<Industry> getIndustryList();
    List<Industry> getIndustryListByName(@Param("name") String name);
}
