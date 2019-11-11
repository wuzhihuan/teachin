package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.VitaeService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.CollectDto;
import com.dizhejiang.teachin.model.Vitae;
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
@RequestMapping(value = "/api/vitae")
public class VitaeController {
    @Autowired
    private VitaeService vitaeService;

    /**
     * 保存简历
     */
    @RequestMapping(value = "/saveMyResumeInfo",method = {RequestMethod.POST})
    public ResponseVo saveMyResumeInfo(@RequestBody @Validated Vitae vitae){
        return vitaeService.saveMyResumeInfo(vitae, AuthenticationUtil.getUserId());
    }

    /**
     * 我的简历
     * @return
     */
    @RequestMapping(value = "/getMyResumeInfo",method = {RequestMethod.POST})
    public ResponseVo getMyResumeInfo(){
        return vitaeService.getMyResumeInfo(AuthenticationUtil.getUserId());
    }
}
