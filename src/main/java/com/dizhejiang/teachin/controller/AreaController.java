package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.AreaService;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 获取省份列表
     * @return
     */
    /*@RequestMapping(value = "/getProvinceList",method = {RequestMethod.POST})
    public ResponseVo getProvinceList(){
        return areaService.getProvinceList();
    }*/

    @RequestMapping(value = "/getProvinceList",method = {RequestMethod.GET})
    public ResponseVo getProvinceList(){
        return areaService.getProvinceList();
    }
}
