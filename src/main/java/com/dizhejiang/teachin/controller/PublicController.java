package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.*;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.AreaDto;
import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.dto.UserDto;
import com.dizhejiang.teachin.model.Industry;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.xml.ws.Action;
import java.text.ParseException;

/**
 * @Author wuqi
 * @Date 2019/10/25
 * 不拦截的接口
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/public")
public class PublicController {
    @Autowired
    private TeachinService teachinService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private VitaeService vitaeService;
    /**
     * 宣讲会列表
     * @return
     */
    @RequestMapping(value = "/getToastmastersList",method = {RequestMethod.POST})
    public ResponseVo getToastmastersList(@RequestBody @Validated TeachinDto dto, ServletRequest request) throws ParseException {
        return teachinService.getToastmastersList(dto,request);
    }

    /**
     * 登录接口
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public ResponseVo login(@RequestBody @Validated UserDto dto){
        return userService.login(dto);
    }

    /**
     * 获取学校
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getSchoolList",method = {RequestMethod.POST})
    public ResponseVo getSchoolList(@RequestBody @Validated AreaDto dto){
        return schoolService.getSchoolList(dto.getProvince());
    }

    /**
     * 获取行业列表
     * @return
     */
    @RequestMapping(value = "/getIndustryList",method = {RequestMethod.POST})
    public ResponseVo getIndustryList1(){
        return industryService.getIndustryList1();
    }

    /**
     * 导数据
     */
   /* @RequestMapping(value = "/getData",method = {RequestMethod.GET})
    public ResponseVo getData(){
        return schoolService.getData();
    }*/
    /**
     * 获取省份列表
     * @return
     */
    @RequestMapping(value = "/getProvinceList",method = {RequestMethod.POST})
    public ResponseVo getProvinceList(){
        return areaService.getProvinceList();
    }

    /**
     * 生成word
     * @return
     */
    @RequestMapping(value = "/getWord",method = {RequestMethod.GET})
    public ResponseVo getWord(){
        return vitaeService.getWord();
    }


    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    public String test(){
        return "123456";
    }

    @RequestMapping(value = "/testError",method = {RequestMethod.GET})
    public String testError(){
        String str = null;
        if(str.equals("11111")){
            return "异常";
        }else{
            return "123456";
        }

    }
    /**
     * 生成二维码
     */

    @RequestMapping(value = "/getBitMatrix",method = {RequestMethod.GET})
    public String getBitMatrix(){
        return "123456";
    }
    /**
     * 通过userId获取token
     */
    //@RequestMapping(value = "/getProvinceList",method = {RequestMethod.POST})
   /* public ResponseVo getProvinceList(){
        return areaService.getProvinceList();
    }*/
}
