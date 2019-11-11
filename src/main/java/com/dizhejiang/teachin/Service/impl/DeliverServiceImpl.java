package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.DeliverService;
import com.dizhejiang.teachin.common.ClassCastUtil;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.mapper.ActionLogMapper;
import com.dizhejiang.teachin.mapper.DeliverMapper;
import com.dizhejiang.teachin.mapper.VitaeMapper;
import com.dizhejiang.teachin.model.ActionLog;
import com.dizhejiang.teachin.model.Deliver;
import com.dizhejiang.teachin.model.Vitae;
import com.dizhejiang.teachin.vo.PositionVo;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Service
public class DeliverServiceImpl implements DeliverService {
    @Autowired
    private  DeliverMapper deliverMapper;
    @Autowired
    private VitaeMapper vitaeMapper;
    @Autowired
    private ActionLogMapper actionLogMapper;

    /**
     * 保存投递
     * @param positionId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo jobDelivery(Integer positionId, Integer userId) {
        //Vitae vitae = new Vitae();
        Vitae vitae = vitaeMapper.selectVitaeByUserId(userId);
        if(vitae==null){
            return ResponseVo.error("没有简历，请先创建简历在投递");
        }
        Deliver deliver =new Deliver();
        deliver.setDeliveryTime(DateUtil.DateToString(new Date()));
        deliver.setPositionId(positionId);
        deliver.setStatus("01");
        deliver.setUserId(userId);
        int falge = deliverMapper.save(deliver);
        List<Deliver>  deliverList =  deliverMapper.selectModelMax();
        ActionLog actionLog = new ActionLog();
        actionLog.setUserId(userId);
        actionLog.setCreateTime(DateUtil.DateToString(new Date()));
        actionLog.setLogType("10");
        actionLog.setOperate("投递职位");
        actionLog.setSource(deliverList.get(0).getId());
        if(falge>0){
            actionLogMapper.save(actionLog);
            return ResponseVo.success("成功");
        }else{
            return ResponseVo.error("失败");
        }

    }

    /**
     * 我的投递类表
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getMyJobDeliveryList(Integer userId) {
        PageHelper.startPage(1,10);
        List<PositionVo> deliverList =  deliverMapper.selectDeliverListByUserId(userId);
        PageInfo pageInfo = new PageInfo<>(deliverList);
        return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
    }
}
