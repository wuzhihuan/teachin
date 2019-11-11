package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.CollectPositionService;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.mapper.CollectPositionMapper;
import com.dizhejiang.teachin.model.CollectPosition;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
@Service
public class CollectPositionServiceImpl implements CollectPositionService {
    @Autowired
    private CollectPositionMapper collectPositionMapper;
    /**
     * 收藏或者取消收藏
     * @param collectPosition
     * @return
     */
    @Override
    public ResponseVo cancelOrCollectPosition(CollectPosition collectPosition) {
        if(collectPosition.getId()==null){
            collectPosition.setStatus("01");
            collectPosition.setCreateTime(DateUtil.DateToString(new Date()));
            collectPositionMapper.save(collectPosition);
            return ResponseVo.success("收藏成功");
        }else{
            CollectPosition collectPosition1 =   collectPositionMapper.selectModelById(collectPosition.getId());
            collectPosition1.setCreateTime(DateUtil.DateToString(new Date()));
            collectPosition1.setStatus(collectPosition.getStatus());
            collectPositionMapper.update(collectPosition1);
            if("01".equals(collectPosition.getStatus())){
                return ResponseVo.success("收藏成功");
            }else{
                return ResponseVo.success("取消收藏成功");
            }

        }

    }

    @Override
    public ResponseVo collectPositionList(CollectPosition collectPosition) {
        collectPosition.setStatus("01");
        collectPositionMapper.selectModelByUserId(collectPosition);
        return null;
    }
}
