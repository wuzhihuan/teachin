package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/actionLog")
public class ActionLogController {
   /*   @RequestMapping(value = "/actionLog",method = {RequestMethod.POST})
    public ResponseVo getProvinceList(){
        return areaService.getProvinceList();
    }*/
}
