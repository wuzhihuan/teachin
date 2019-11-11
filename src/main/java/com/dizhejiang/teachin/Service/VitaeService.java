package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.Vitae;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface VitaeService {
    /**
     * 保存我的简历
     * @param vitae
     * @param userId
     * @return
     */
     ResponseVo saveMyResumeInfo(Vitae vitae, Integer userId);

    /**
     * 我的简历
     * @param userId
     * @return
     */
     ResponseVo getMyResumeInfo(Integer userId);

     ResponseVo getWord();
}
