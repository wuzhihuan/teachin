package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.EnrollService;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.dto.EnrollDto;
import com.dizhejiang.teachin.mapper.ActionLogMapper;
import com.dizhejiang.teachin.mapper.EnrollMapper;
import com.dizhejiang.teachin.mapper.TeachinDataMapper;
import com.dizhejiang.teachin.mapper.TeachinMapper;
import com.dizhejiang.teachin.model.ActionLog;
import com.dizhejiang.teachin.model.Enroll;
import com.dizhejiang.teachin.model.Teachin;
import com.dizhejiang.teachin.model.TeachinData;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Service
public class EnrollServiceImpl implements EnrollService {
    @Autowired
    private EnrollMapper enrollMapper;
    @Autowired
    private TeachinDataMapper teachinDataMapper;
    @Autowired
    private TeachinMapper teachinMapper;
    @Autowired
    private ActionLogMapper actionLogMapper;
    /**
     * 报名取消报名
     * @param dto
     * @param userId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo toastmastersSignup(EnrollDto dto, Integer userId) {
        if(dto.getTeachId()==null){
            return ResponseVo.error("宣讲会id不能为空");
        }
        Enroll  enroll = enrollMapper.selectEnrollByTeachIdAndUserId(dto.getTeachId(),userId);
        TeachinData teachinData = teachinDataMapper.selectModelByTeachinId(dto.getTeachId());
        int flage =0;
        ActionLog actionLog = new ActionLog();
        actionLog.setCreateTime(DateUtil.DateToString(new Date()));
        actionLog.setLogType("20");
        actionLog.setUserId(userId);

        if(enroll==null){  //保存默认报名
            //报名
            if(teachinData==null){  //保存数据
                TeachinData teachinData1 = new  TeachinData();
                teachinData1.setEnrollNum(1);
                teachinData1.setFoocusNum(0);
                teachinData1.setLookNum(0);
                teachinData1.setTeachinId(dto.getTeachId());
                teachinData1.setLocalEnrollNum(0);
                teachinData1.setOtherEnrollNum(0);
                teachinData1.setStatus("01");
                teachinData1.setUpdateTime(DateUtil.DateToString(new Date()));
                teachinDataMapper.save(teachinData1);

                Teachin teachin = new Teachin();
                teachin.setIsEnroll("01");
                teachin.setId(dto.getTeachId());
                teachinMapper.update(teachin);
            }else{
                teachinData.setEnrollNum(teachinData.getEnrollNum()+1);
                 teachinDataMapper.update(teachinData);
            }
            flage= enrollMapper.save(dto.getTeachId(),userId,"01",DateUtil.DateToString(new Date()));
            List<Enroll> enrollList =enrollMapper.selectModelMax();
            actionLog.setOperate("报名宣讲会");
            actionLog.setSource(enrollList.get(0).getId());
        }else{
            if(StringUtils.isEmpty(dto.getStatus())){
                return ResponseVo.error("报名状态不能为空");
            }
            //判断是取消报名还是报名
            if("01".equals(dto.getStatus())  ){  //报名
                teachinData.setEnrollNum(teachinData.getEnrollNum()+1);
                Teachin teachin = new Teachin();
                teachin.setIsEnroll("01");
                teachin.setId(dto.getTeachId());
                teachinMapper.update(teachin);
                actionLog.setOperate("报名宣讲会");
               // actionLog.setSource(dto.get());
            }else{   //取消报名
                teachinData.setEnrollNum(teachinData.getEnrollNum()-1);
                Teachin teachin = new Teachin();
                teachin.setIsEnroll("02");
                teachin.setId(dto.getTeachId());
                teachin.setOperateTime("2017-12-12 20:12:11");
                teachinMapper.update(teachin);
                actionLog.setOperate("取消报名宣讲会");
            }
             teachinDataMapper.update(teachinData);
            flage=  enrollMapper.update(dto.getStatus(),dto.getTeachId(),userId,DateUtil.DateToString(new Date()));
        }
        Enroll  enrollSta = enrollMapper.selectEnrollByTeachIdAndUserId(dto.getTeachId(),userId);
        actionLog.setSource(enrollSta.getId());
        if(flage>0){
            actionLogMapper.save(actionLog);
            return ResponseVo.success(enrollSta);
        }else{
            return ResponseVo.success("失败");
        }


    }
}
