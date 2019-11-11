package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.Report;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
public interface ReportService {
    /**
     * 举报
     * @param report
     * @return
     */
    ResponseVo myReport(Report report);
}
