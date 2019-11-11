package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.CompanyAttestationService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.DeliverDto;
import com.dizhejiang.teachin.model.CompanyAttestation;
import com.dizhejiang.teachin.vo.CompanyAttestationVo;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/companyAttestation")
public class CompanyAttestationController {
    @Autowired
    private CompanyAttestationService companyAttestationService;

    /**
     * 企业认证
     * @return
     */
    @RequestMapping(value = "/enterpriseCertification",method = {RequestMethod.POST})
    public ResponseVo enterpriseCertification(@RequestBody @Validated CompanyAttestationVo companyAttestationVo){
        return companyAttestationService.enterpriseCertification(companyAttestationVo, AuthenticationUtil.getUserId());
    }
}
