package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.GroupAttestationService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.DeliverDto;
import com.dizhejiang.teachin.model.GroupAttestation;
import com.dizhejiang.teachin.vo.GroupAttestationVo;
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
@RequestMapping(value = "/api/groupAttestation")
public class GroupAttestationController {
    @Autowired
    private GroupAttestationService groupAttestationService;
    /**
     * 社群认证
     * @return
     */
    @RequestMapping(value = "/saveGroupAttestation",method = {RequestMethod.POST})
    public ResponseVo saveGroupAttestation(@RequestBody @Validated GroupAttestationVo groupAttestationVo){
        return groupAttestationService.saveGroupAttestation(groupAttestationVo, AuthenticationUtil.getUserId());
    }
}
