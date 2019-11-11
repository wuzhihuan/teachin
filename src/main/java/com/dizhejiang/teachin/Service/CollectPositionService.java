package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.CollectPosition;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
public interface CollectPositionService {
    /**
     * 收藏或者取消收藏
     * @param collectPosition
     * @return
     */
   ResponseVo cancelOrCollectPosition(CollectPosition collectPosition);

    /**
     * 收藏列表
     * @param collectPosition
     * @return
     */
    ResponseVo collectPositionList(CollectPosition collectPosition);
}
