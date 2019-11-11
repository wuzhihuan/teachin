package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.FeedBackService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.dto.EnrollDto;
import com.dizhejiang.teachin.model.FeedBack;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/11/4
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/feedBack")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    /**
     * 反馈意见
     * @param feedBack
     * @return
     */
    @RequestMapping(value = "/saveFeedBack",method = {RequestMethod.POST})
    public ResponseVo getProvinceList(@RequestBody @Validated FeedBack feedBack){
        feedBack.setUserId(AuthenticationUtil.getUserId());
        return feedBackService.saveFeedBack(feedBack);
    }
}
