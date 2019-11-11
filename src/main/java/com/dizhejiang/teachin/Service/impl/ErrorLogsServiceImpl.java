package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.ErrorLogsService;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.mapper.ErrorLogsMapper;
import com.dizhejiang.teachin.model.ErrorLogs;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
@Service
public class ErrorLogsServiceImpl implements ErrorLogsService {
    @Autowired
   private ErrorLogsMapper errorLogsMapper;
    @Override
    public ResponseVo SaveerrorLogs(ErrorLogs errorLogs,Exception e) {
        if(AuthenticationUtil.getUserId()==null){
            errorLogs.setUserId(0);
        }else{
            errorLogs.setUserId(AuthenticationUtil.getUserId());
        }
        errorLogsMapper.save(errorLogs);
        throw new ResponseException("服务器异常");
        //return ResponseVo.success("成功");
    }
}
