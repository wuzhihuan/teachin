package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.dto.EnrollDto;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface EnrollService {
    /**
     * 报名，取消报名
     * @param dto
     * @param userId
     * @return
     */
    ResponseVo toastmastersSignup(EnrollDto dto,Integer userId);
}
