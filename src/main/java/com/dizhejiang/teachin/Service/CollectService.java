package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.dto.CollectDto;
import com.dizhejiang.teachin.vo.PageModel;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface CollectService {
    /**
     * 宣讲会收藏，取消
     * @param dto
     * @param userId
     * @return
     */
    ResponseVo toastmastersCollection(CollectDto dto, Integer userId);

    /**
     * 获取我的收藏
     * @param userId
     * @return
     */
    ResponseVo getToastmastersCollectionList(PageModel pageModel, Integer userId);


}
