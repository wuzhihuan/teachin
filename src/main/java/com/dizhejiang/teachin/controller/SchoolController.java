package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.SchoolService;
import com.dizhejiang.teachin.dto.AreaDto;
import com.dizhejiang.teachin.dto.UserDto;
import com.dizhejiang.teachin.model.School;
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
@RequestMapping(value = "/api/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 获取学校列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getSchoolList",method = {RequestMethod.POST})
    public ResponseVo getSchoolList(@RequestBody @Validated AreaDto dto){
        return schoolService.getSchoolList(dto.getProvince());
    }

    /**
     * 所属学校
     * @param dto
     * @return
     */
    @RequestMapping(value = "/searchSchool",method = {RequestMethod.POST})
    public ResponseVo searchSchool(@RequestBody @Validated School dto){
        return schoolService.searchSchool(dto.getSchoolName());
    }



}
