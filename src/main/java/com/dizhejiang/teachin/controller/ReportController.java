package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.ReportService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.model.ItemExperience;
import com.dizhejiang.teachin.model.Report;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 举报
     * @return
     */
    @RequestMapping(value = "/myReport",method = {RequestMethod.POST})
    public ResponseVo myReport(@RequestBody @Validated Report report){
        report.setUserId(AuthenticationUtil.getUserId());
        return reportService.myReport(report);
    }
}
