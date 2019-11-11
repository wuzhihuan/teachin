package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.TeacherAttestationService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.model.TeacherAttestation;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeacherAttestationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/teacherAttestation")
public class TeacherAttestationController {
    @Autowired
    private TeacherAttestationService teacherAttestationService;
    /**
     * 老师认证
     * @return
     */
    @RequestMapping(value = "/teacherCertification",method = {RequestMethod.POST})
    public ResponseVo teacherCertification(@RequestBody @Validated TeacherAttestationVo teacherAttestationVo){
        return teacherAttestationService.teacherCertification(teacherAttestationVo, AuthenticationUtil.getUserId());
    }
}
