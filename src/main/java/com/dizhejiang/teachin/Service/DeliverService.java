package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface DeliverService {
    /**
     * 投递
     * @param positionId
     * @param userId
     * @return
     */
    ResponseVo jobDelivery(Integer positionId,Integer userId);

    /**
     * 我的投递列表
     * @param userId
     * @return
     */
    ResponseVo getMyJobDeliveryList(Integer userId);
}
