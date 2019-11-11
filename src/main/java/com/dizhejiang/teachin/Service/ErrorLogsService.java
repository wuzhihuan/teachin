package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.ErrorLogs;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
public interface ErrorLogsService {
    ResponseVo SaveerrorLogs(ErrorLogs errorLogs,Exception e);
}
