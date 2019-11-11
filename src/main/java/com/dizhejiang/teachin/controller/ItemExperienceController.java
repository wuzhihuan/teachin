package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.ItemExperienceService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.model.ItemExperience;
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
@RequestMapping(value = "/api/itemExperience")
public class ItemExperienceController {
    @Autowired
    private  ItemExperienceService itemExperienceService;

    /**
     * 添加项目经验
     * @return
     */
    @RequestMapping(value = "/addProjectExperience",method = {RequestMethod.POST})
    public ResponseVo addProjectExperience(@RequestBody @Validated ItemExperience itemExperience){
        return itemExperienceService.addProjectExperience(itemExperience, AuthenticationUtil.getUserId());
    }

    /**
     * 更新项目经验
     * @param itemExperience
     * @return
     */
    @RequestMapping(value = "/updateProjectExperience",method = {RequestMethod.POST})
    public ResponseVo updateProjectExperience(@RequestBody @Validated ItemExperience itemExperience){
        return itemExperienceService.updateProjectExperience(itemExperience);
    }

    /**
     * 删除项目经验
     * @param itemExperience
     * @return
     */
    @RequestMapping(value = "/deleteProjectExperience",method = {RequestMethod.POST})
    public ResponseVo deleteProjectExperience(@RequestBody @Validated ItemExperience itemExperience){
        return itemExperienceService.deleteProjectExperience(itemExperience);
    }

    @RequestMapping(value = "/selectProjectExperienceById",method = {RequestMethod.POST})
    public ResponseVo selectProjectExperienceById(@RequestBody @Validated ItemExperience itemExperience){
        return itemExperienceService.selectProjectExperienceById(itemExperience);
    }



}
