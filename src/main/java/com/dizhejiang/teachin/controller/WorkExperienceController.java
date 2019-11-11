package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.WorkExperienceService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.CollectDto;
import com.dizhejiang.teachin.model.WorkExperience;
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
@RequestMapping(value = "/api/workExperience")
public class WorkExperienceController {
    @Autowired
    private  WorkExperienceService workExperienceService;

    /**
     * 新加工作经验
     * @param workExperience
     * @return
     */
    @RequestMapping(value = "/addWorkExperience",method = {RequestMethod.POST})
    public ResponseVo addWorkExperience(@RequestBody @Validated WorkExperience workExperience){
        return workExperienceService.addWorkExperience(workExperience, AuthenticationUtil.getUserId());
    }


    /**
     * 更新工作经验
     * @param workExperience
     * @return
     */
    @RequestMapping(value = "/updateWorkExperience",method = {RequestMethod.POST})
    public ResponseVo updateWorkExperience(@RequestBody @Validated WorkExperience workExperience){
        return workExperienceService.updateWorkExperience(workExperience);
    }

    /**
     * 通过id获取对象
     * @param workExperience
     * @return
     */
    @RequestMapping(value = "/selectWorkExperienceById",method = {RequestMethod.POST})
    public ResponseVo selectWorkExperienceById(@RequestBody @Validated WorkExperience workExperience){
        return workExperienceService.selectWorkExperienceById(workExperience);
    }
    /**
     * 删除工作经历
     * @param workExperience
     * @return
     */
    @RequestMapping(value = "/deleteWorkExperience",method = {RequestMethod.POST})
    public ResponseVo deleteWorkExperience(@RequestBody @Validated WorkExperience workExperience){
        return workExperienceService.deleteWorkExperience(workExperience);
    }





}
