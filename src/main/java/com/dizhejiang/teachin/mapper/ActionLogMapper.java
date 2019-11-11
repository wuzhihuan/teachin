package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.ActionLog;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
public interface ActionLogMapper {
    int save(@Param("actionLog") ActionLog actionLog);
}
