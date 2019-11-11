package com.dizhejiang.teachin.controller;

import com.dizhejiang.teachin.Service.ErrorLogsService;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.common.ErrorLogsUtil;
import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.model.ErrorLogs;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;


/**
 * @Author wuqi
 * @Date 2019/11/6
 */

@ControllerAdvice
public class ExceptionController {
    @Autowired
    private ErrorLogsService errorLogsService;
    Logger logger = LoggerFactory.getLogger(ErrorLogs.class);
    @ExceptionHandler(value = Exception.class)
    public ResponseVo handle(Exception e) {
        StackTraceElement stackTraceElement= e.getStackTrace()[0];
        String errorInfo = e.toString()+",errorMassage:"+stackTraceElement+","+"errorLine:"+stackTraceElement.getLineNumber();
        ErrorLogs errorLogs = new ErrorLogs();
        errorLogs.setErrorInfo(errorInfo);
        errorLogs.setCreateTime(DateUtil.DateToString(new Date()));
        logger.info(e.toString());
        return errorLogsService.SaveerrorLogs(errorLogs,e);
    }

}
