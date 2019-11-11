package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.GroupAttestation;
import com.dizhejiang.teachin.vo.GroupAttestationVo;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface GroupAttestationService {
    /**
     * 认证社群
     * @param groupAttestationVo
     * @param userId
     * @return
     */
    ResponseVo saveGroupAttestation(GroupAttestationVo groupAttestationVo, Integer userId);
}
