package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.CollectPositionService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.model.CollectPosition;
import com.dizhejiang.teachin.vo.CompanyAttestationVo;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/collectPosition")
public class CollectPositionController {
    @Autowired
    private CollectPositionService collectPositionService;

    /**
     * 收藏职位或者取消收藏职位
     * @return
     */
    @RequestMapping(value = "/cancelOrCollectPosition",method = {RequestMethod.POST})
    public ResponseVo getProvinceList(@RequestBody @Validated CollectPosition collectPosition){
        collectPosition.setUserId(AuthenticationUtil.getUserId());
        return collectPositionService.cancelOrCollectPosition(collectPosition);
    }

    /**
     * 我的收藏类表
     */
    @RequestMapping(value = "/collectPositionList",method = {RequestMethod.POST})
    public ResponseVo collectPositionList(@RequestBody @Validated CollectPosition collectPosition){
        collectPosition.setUserId(AuthenticationUtil.getUserId());
        return collectPositionService.collectPositionList(collectPosition);
    }
}
