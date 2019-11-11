package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.TeachinService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.AreaDto;
import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.model.Teachin;
import com.dizhejiang.teachin.vo.PageModel;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeachinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.text.ParseException;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping(value = "/api/teachin")
public class TeachinController {
    @Autowired
    private TeachinService teachinService;
    /**
     * 宣讲会列表
     * @return
     */
    @RequestMapping(value = "/getToastmastersList",method = {RequestMethod.POST})
    public ResponseVo getToastmastersList(@RequestBody @Validated TeachinDto dto, ServletRequest request) throws ParseException {
        return teachinService.getToastmastersList(dto,request);
    }

    /**
     * 详情
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getToastmastersDetail",method = {RequestMethod.POST})
    public ResponseVo getToastmastersDetail(@RequestBody @Validated TeachinDto dto){
        return teachinService.getToastmastersDetail(dto.getId(),AuthenticationUtil.getUserId());
    }

    /**
     * 获取我的所有宣讲会
     * @return
     */
    @RequestMapping(value = "/getMyToastmastersList",method = {RequestMethod.POST})
    public ResponseVo getMyToastmastersList(@RequestBody @Validated TeachinDto dto){
        return teachinService.getMyToastmastersList(dto,AuthenticationUtil.getUserId());
    }

    /**
     * 发布宣讲会
     * @return
     */
    @RequestMapping(value = "/releaseToastmasters",method = {RequestMethod.POST})
    public ResponseVo releaseToastmasters(@RequestBody @Validated Teachin teachin){
        return teachinService.releaseToastmasters(teachin,AuthenticationUtil.getUserId());
    }

    /**
     * 保存草稿
     * @param teachin
     * @return
     */
    @RequestMapping(value = "/saveToastmastersDrafts",method = {RequestMethod.POST})
    public ResponseVo saveToastmastersDrafts(@RequestBody @Validated Teachin teachin){
        return teachinService.saveToastmastersDrafts(teachin,AuthenticationUtil.getUserId());
    }

    /**
     * 修改
     * @param teachin
     * @return
     */
    @RequestMapping(value = "/updateToastmasters",method = {RequestMethod.POST})
    public ResponseVo updateToastmasters(@RequestBody @Validated Teachin teachin){
        return teachinService.updateToastmasters(teachin,AuthenticationUtil.getUserId());
    }

    /**
     * 删除
     * @param teachin
     * @return
     */
    @RequestMapping(value = "/deleteToastmastersDrafts",method = {RequestMethod.POST})
    public ResponseVo deleteToastmastersDrafts(@RequestBody @Validated Teachin teachin){
        return teachinService.deleteToastmastersDrafts(teachin,AuthenticationUtil.getUserId());
    }

    /**
     * 获取我的草稿
     * @return
     */
    @RequestMapping(value = "/getToastmastersDraftsList",method = {RequestMethod.POST})
    public ResponseVo getToastmastersDraftsList(@RequestBody @Validated PageModel pageModel){
        return teachinService.getToastmastersDraftsList(pageModel,AuthenticationUtil.getUserId());
    }

    /**
     * 取消宣讲会
     * @return
     */
    @RequestMapping(value = "/cancelToastmasters",method = {RequestMethod.POST})
    public ResponseVo cancelToastmasters(@RequestBody @Validated Teachin teachin){
        return teachinService.cancelToastmasters(teachin.getId(),AuthenticationUtil.getUserId());
    }

    /**
     * 获取我的宣讲会详情
     * @param teachin
     * @return
     */
    @RequestMapping(value = "/getMyToastmastersDetail",method = {RequestMethod.POST})
    public ResponseVo getMyToastmastersDetail(@RequestBody @Validated Teachin teachin){
        return teachinService.getMyToastmastersDetail(teachin.getId(),AuthenticationUtil.getUserId());
    }

    /**
     * 获取指定月份的宣讲会（参数传2019-10）
     * @param teachinDto
     * @return
     */
    @RequestMapping(value = "/getMyMonthTeachin",method = {RequestMethod.POST})
    public ResponseVo getMyMonthTeachin(@RequestBody @Validated TeachinDto teachinDto){
        teachinDto.setUserId(AuthenticationUtil.getUserId());
        return teachinService.getMyMonthTeachin(teachinDto);
    }



}
