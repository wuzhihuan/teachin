package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/28
 */
public interface TbPositionMapper {
    List<Position> searchJobs(@Param("positionName") String positionName);
}
