package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.IndustryService;
import com.dizhejiang.teachin.model.Industry;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/industry")
public class IndustryController {
    @Autowired
    private  IndustryService industryService;

    /**
     * 获取行业列表
     * @return
     */
    @RequestMapping(value = "/getIndustryList",method = {RequestMethod.POST})
    public ResponseVo getIndustryList(@RequestBody @Validated Industry industry){
        return industryService.getIndustryList(industry);
    }
}
