package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.FeedBack;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/11/4
 */
public interface FeedBackService {
    /**
     * 意见反馈
     * @param feedBack
     * @return
     */
    ResponseVo saveFeedBack(FeedBack feedBack);
}
