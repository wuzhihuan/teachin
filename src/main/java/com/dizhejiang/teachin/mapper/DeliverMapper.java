package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Deliver;
import com.dizhejiang.teachin.model.Position;
import com.dizhejiang.teachin.vo.PositionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface DeliverMapper {
    /**
     * 保存投递
     * @param deliver
     * @return
     */
    int save(@Param("deliver") Deliver deliver);

    /**
     * 我的投递列表
     * @return
     */
    List<PositionVo> selectDeliverListByUserId(@Param("userId") Integer userId);
    /**
     * 通过职位查询对应的投递
     * @param positionId
     * @return
     */
    List<Deliver> selectDeliverListByPositionId(@Param("positionId") Integer positionId);
    List<Deliver> selectModelMax();
}
