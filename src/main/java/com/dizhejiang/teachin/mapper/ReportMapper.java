package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Report;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
public interface ReportMapper {
    int save(@Param("report") Report report);
}
