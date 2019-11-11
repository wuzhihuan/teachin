package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.CompanyAttestation;
import com.dizhejiang.teachin.vo.CompanyAttestationVo;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface CompanyAttestationService {
    /**
     * 企业认证
     * @param companyAttestationVo
     * @param userId
     * @return
     */
    ResponseVo enterpriseCertification(CompanyAttestationVo companyAttestationVo, Integer userId);
}
