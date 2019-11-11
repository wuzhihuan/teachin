package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.ReportService;
import com.dizhejiang.teachin.mapper.ReportMapper;
import com.dizhejiang.teachin.model.Report;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;
    @Override
    public ResponseVo myReport(Report report) {
        if(report.getTeachinId()==null){
            return ResponseVo.error("宣讲会id不能为空");
        }
        if(StringUtils.isEmpty( report.getType())){
            return ResponseVo.error("举报类型不能为空");
        }
        reportMapper.save(report);
        return ResponseVo.success("举报成功，等待审核！");
    }
}
