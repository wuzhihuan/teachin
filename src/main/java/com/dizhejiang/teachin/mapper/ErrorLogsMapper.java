package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.ErrorLogs;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
public interface ErrorLogsMapper {
    int save(@Param("errorLogs") ErrorLogs errorLogs);
}
