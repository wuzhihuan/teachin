package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.CollectService;
import com.dizhejiang.teachin.Service.PositionService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.CollectDto;
import com.dizhejiang.teachin.dto.PositionDto;
import com.dizhejiang.teachin.model.Position;
import com.dizhejiang.teachin.vo.PositionVo;
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
@RequestMapping(value = "/api/position")
public class PositionController {
    @Autowired
    private PositionService  positionService;

    /**
     * 获取职位详情
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getJobDetail",method = {RequestMethod.POST})
    public ResponseVo getJobDetail(@RequestBody @Validated PositionDto dto){
        return positionService.getJobDetail(dto.getId(),AuthenticationUtil.getUserId());
    }

    /**
     * 所属职位
     */
    @RequestMapping(value = "/searchJobs",method = {RequestMethod.POST})
    public ResponseVo searchJobs(@RequestBody @Validated PositionDto dto){
        return positionService.searchJobs(dto.getPosition());
    }

    /**
     * 添加职位
     * @return
     */
    @RequestMapping(value = "/releaseJobs",method = {RequestMethod.POST})
    public ResponseVo releaseJobs(@RequestBody @Validated Position position){
        return positionService.releaseJobs(position,AuthenticationUtil.getUserId());
    }

    /**
     *更新
     * @param position
     * @return
     */
    @RequestMapping(value = "/updateJobs",method = {RequestMethod.POST})
    public ResponseVo updateJobs(@RequestBody @Validated Position position){
        position.setUserId(AuthenticationUtil.getUserId());
        return positionService.updateJobs(position);
    }

    /**
     * 我发布的职位
     * @return
     */
    @RequestMapping(value = "/getMyReleasedJobsList",method = {RequestMethod.POST})
    public ResponseVo getMyReleasedJobsList(@RequestBody @Validated PositionVo positionVo){
        return positionService.getMyReleasedJobsList(positionVo,AuthenticationUtil.getUserId());
    }



}
