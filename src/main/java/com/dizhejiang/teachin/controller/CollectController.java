package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.CollectService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.CollectDto;
import com.dizhejiang.teachin.dto.EnrollDto;
import com.dizhejiang.teachin.vo.PageModel;
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
@RequestMapping(value = "/api/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;
    /**
     * 收藏，取消收藏
     * @return
     */
    @RequestMapping(value = "/toastmastersCollection",method = {RequestMethod.POST})
    public ResponseVo toastmastersCollection(@RequestBody @Validated CollectDto dto){
        return collectService.toastmastersCollection(dto, AuthenticationUtil.getUserId());
    }

    /**
     * 获取我的收藏
     * @return
     */
    @RequestMapping(value = "/getToastmastersCollectionList",method = {RequestMethod.POST})
    public ResponseVo getToastmastersCollectionList(@RequestBody @Validated PageModel pageModel){
        return collectService.getToastmastersCollectionList(pageModel,AuthenticationUtil.getUserId());
    }

}
