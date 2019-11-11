package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.DeliverService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.DeliverDto;
import com.dizhejiang.teachin.dto.EnrollDto;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/deliver")
public class DeliverController {
    @Autowired
    private DeliverService deliverService;
    /**
     * 投递
     * @return
     */
    @RequestMapping(value = "/jobDelivery",method = {RequestMethod.POST})
    public ResponseVo jobDelivery(@RequestBody @Validated DeliverDto dto){
        if(dto.getPositionId()==null){
            return ResponseVo.error("职务id不能为空");
        }
        return deliverService.jobDelivery(dto.getPositionId(), AuthenticationUtil.getUserId());
    }

    /**
     * 获取我的投递列表
     * @return
     */
    @RequestMapping(value = "/getMyJobDeliveryList",method = {RequestMethod.POST})
    public ResponseVo getMyJobDeliveryList(){
        return deliverService.getMyJobDeliveryList(AuthenticationUtil.getUserId());
    }

}
