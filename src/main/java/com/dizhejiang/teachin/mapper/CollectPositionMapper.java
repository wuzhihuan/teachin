package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.CollectPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
public interface CollectPositionMapper {
    /**
     * 保存方法
     * @param collectPosition
     * @return
     */
    int save(@Param("collectPosition") CollectPosition collectPosition);

    /**
     * 跟新
     * @param collectPosition
     * @return
     */
    int update(@Param("collectPosition") CollectPosition collectPosition);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CollectPosition selectModelById(@Param("id") Integer id);

    /**
     * 通过职务id查询
     * @param positionId
     * @return
     */
    CollectPosition selectModelByPosition(@Param("positionId") Integer positionId);

    /**
     * 我收藏的职位
     * @param collectPosition
     * @return
     */
    List<CollectPosition> selectModelByUserId(@Param("collectPosition") CollectPosition collectPosition);
}
