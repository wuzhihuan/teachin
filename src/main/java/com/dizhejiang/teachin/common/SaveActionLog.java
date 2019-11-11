package com.dizhejiang.teachin.common;

import com.dizhejiang.teachin.mapper.ActionLogMapper;
import com.dizhejiang.teachin.model.ActionLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
public class SaveActionLog {

    public static ActionLog SaveActionLog(Integer userId,String logType,Integer source,String operate) {
        ActionLog actionLog = new ActionLog();
        actionLog.setUserId(userId);
        actionLog.setLogType(logType);
        actionLog.setCreateTime(DateUtil.DateToString(new Date()));
        actionLog.setSource(source);
        actionLog.setOperate(operate);
       return actionLog;
    }
}
