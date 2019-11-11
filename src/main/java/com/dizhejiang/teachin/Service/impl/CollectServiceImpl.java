package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.CollectService;
import com.dizhejiang.teachin.common.ClassCastUtil;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.dto.CollectDto;
import com.dizhejiang.teachin.mapper.CollectMapper;
import com.dizhejiang.teachin.mapper.TeachinDataMapper;
import com.dizhejiang.teachin.mapper.TeachinMapper;
import com.dizhejiang.teachin.model.Collect;
import com.dizhejiang.teachin.model.Enroll;
import com.dizhejiang.teachin.model.Teachin;
import com.dizhejiang.teachin.model.TeachinData;
import com.dizhejiang.teachin.vo.PageModel;
import com.dizhejiang.teachin.vo.PositionVo;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeachinVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private TeachinDataMapper teachinDataMapper;
    @Autowired
    private TeachinMapper teachinMapper;

    /**
     * 收藏，取消
     * @param dto
     * @param userId
     * @return
     */
    @Override
    public ResponseVo toastmastersCollection(CollectDto dto, Integer userId) {
        if(StringUtils.isEmpty(dto.getTeachinId())){
            return ResponseVo.error("宣讲会id必填");
        }
        Collect collect = collectMapper.selectCollectByTeachIdAndUserId(dto.getTeachinId(),userId);
        TeachinData teachinData = teachinDataMapper.selectModelByTeachinId(dto.getTeachinId());
        int flage =0;
        if(collect==null){  //收藏
            if(teachinData==null){
                teachinData.setFoocusNum(1);
                teachinData.setLookNum(0);
                teachinData.setEnrollNum(0);
                teachinData.setTeachinId(dto.getTeachinId());
                teachinData.setLocalEnrollNum(0);
                teachinData.setOtherEnrollNum(0);
                teachinData.setStatus("01");
                teachinData.setUpdateTime(DateUtil.DateToString(new Date()));
                int f= teachinDataMapper.save(teachinData);
                if(f<1){
                    return ResponseVo.success("保存宣讲会数据失败");
                }
                Teachin teachin = new Teachin();
                teachin.setIsCollect("01");
                teachin.setId(dto.getTeachinId());
                teachinMapper.update(teachin);
            }else{
                teachinData.setFoocusNum(teachinData.getFoocusNum()+1);
                int f= teachinDataMapper.update(teachinData);
                if(f<1){
                    return ResponseVo.success("保存宣讲会数据失败");
                }
            }
            flage= collectMapper.save(dto.getTeachinId(),userId);
        }else{
            if("01".equals(dto.getStatus())){//收藏状态
                teachinData.setFoocusNum(teachinData.getFoocusNum()+1);
                Teachin teachin = new Teachin();
                teachin.setIsCollect("01");
                teachin.setId(dto.getTeachinId());
                teachinMapper.update(teachin);
            }else{//取消收藏
                teachinData.setFoocusNum(teachinData.getFoocusNum()-1);
                Teachin teachin = new Teachin();
                teachin.setIsCollect("02");
                teachin.setId(dto.getTeachinId());
                teachinMapper.update(teachin);
            }
            flage=  collectMapper.update(dto.getStatus(),dto.getTeachinId(),userId);
        }
        Collect  collectSta = collectMapper.selectCollectByTeachIdAndUserId(dto.getTeachinId(),userId);
        if(flage==1){
            return ResponseVo.success(collectSta);
        }else{
            return ResponseVo.success("失败");
        }
    }

    /**
     * 我的收藏列表
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getToastmastersCollectionList(PageModel pageModel, Integer userId) {
        PageHelper.startPage(pageModel.getPageNum(),pageModel.getPageSize());
        List<TeachinVo> teachinVoList = collectMapper.selectCollectListByUserId(userId);
        PageInfo pageInfo = new PageInfo<>(teachinVoList);
        return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
    }
}
