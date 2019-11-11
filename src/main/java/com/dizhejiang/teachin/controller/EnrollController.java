package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.EnrollService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.EnrollDto;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/enroll")
public class EnrollController {
    @Autowired
    private EnrollService enrollService;
    /**
     * 报名，取消报名
     * @return
     */
    @RequestMapping(value = "/toastmastersSignup",method = {RequestMethod.POST})
    public ResponseVo toastmastersSignup(@RequestBody @Validated EnrollDto dto){
        return enrollService.toastmastersSignup(dto, AuthenticationUtil.getUserId());
    }
}
