package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.CollectPositionService;
import com.dizhejiang.teachin.Service.PositionService;
import com.dizhejiang.teachin.common.ClassCastUtil;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.common.SaveActionLog;
import com.dizhejiang.teachin.mapper.*;
import com.dizhejiang.teachin.model.*;
import com.dizhejiang.teachin.vo.PositionVo;
import com.dizhejiang.teachin.vo.ResponseVo;
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
public class PositionServiceImpl implements PositionService {
    @Autowired
    private   PositionMapper positionMapper;
    @Autowired
    private TbPositionMapper tbPositionMapper;
    @Autowired
    private DeliverMapper deliverMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollectPositionMapper collectPositionMapper;
    @Autowired
    private ActionLogMapper actionLogMapper;

    /**
     * 获取职位详情
     * @param id
     * @return
     */
    @Override
    public ResponseVo getJobDetail(Integer id ,Integer userId) {
        List<PositionVo> list = deliverMapper.selectDeliverListByUserId(userId);
        Position position1 = positionMapper.selectPositionById(id);
        //更新浏览数
        position1.setViews(position1.getViews()+1);
        positionMapper.update(position1);
        Position position = positionMapper.selectPositionById(id);
        CollectPosition collectPosition =collectPositionMapper.selectModelByPosition(id);
        //未收藏
        if(collectPosition==null){
            position.setIsCollect("02");
        }else {
            position.setIsCollect("01");
        }

        if(list.size()>0){
            position.setIsDeliver("01");
        }else{
            position.setIsDeliver("02");
        }
        return ResponseVo.success(position);
    }

    /**
     * 所属职位
     * @param position
     * @return
     */
    @Override
    public ResponseVo searchJobs(String position) {
        List<Position> list =tbPositionMapper.searchJobs(position);
        return ResponseVo.success(list);
    }

    /**
     * 保存职位
     * @param position
     * @return
     */
    @Override
    public ResponseVo releaseJobs(Position position,Integer userId) {
        if(StringUtils.isEmpty(position.getPosition())){
            return ResponseVo.error("职务不能为空");
        }
        if(StringUtils.isEmpty(position.getWorkType())){
            return ResponseVo.error("工作性质不能为空");
        }
        if(StringUtils.isEmpty(position.getSalaryMin())){
            return ResponseVo.error("薪资不能为空");
        }
        if(StringUtils.isEmpty(position.getSalaryMax())){
            return ResponseVo.error("薪资不能为空");
        }
       /* if(StringUtils.isEmpty(position.getPart())){
            return ResponseVo.error("部门不能为空");
        }*/
        if(StringUtils.isEmpty(position.getNum())){
            return ResponseVo.error("招聘人数不能为空");
        }
        if(StringUtils.isEmpty(position.getWorkPlace())){
            return ResponseVo.error("工作地点不能为空");
        }
       /* if(StringUtils.isEmpty(position.getCompanyId())){
            return ResponseVo.error("公司id不能为空");
        }*/
        if(StringUtils.isEmpty(position.getDescription())){
            return ResponseVo.error("描述不能为空");
        }
        /*if(StringUtils.isEmpty(position.getCompanyName())){
            return ResponseVo.error("公司名称不能为空");
        }*/

        User user = userMapper.selectUserById(userId);
        if(!"03".equals(user.getUserType())){
            return ResponseVo.error("您不是企业用户，不能发布职位");
        }
        position.setCredit(user.getCredit());
        position.setCompanyName(user.getCompanyName());
        position.setStatus("01");//不开放
        position.setUserId(userId);
        position.setViews(0);
        position.setDeliverNum(0);
        position.setCreateTime(DateUtil.DateToString(new Date()));
        int f =positionMapper.save(position);
        List<Position> positionList =positionMapper.selectModelMax();
        //Integer userId,String logType,Integer source,String operate
        ActionLog actoinlog =  SaveActionLog.SaveActionLog(userId,"50",positionList.get(0).getId(),"发布职位");
        if(f>0){
            actionLogMapper.save(actoinlog);
            return ResponseVo.success("保存成功");
        }else{
            return ResponseVo.error("保存失败");
        }

    }

    /**
     * 修改
     * @param position
     * @return
     */
    @Override
    public ResponseVo updateJobs(Position position) {
        User user = userMapper.selectUserById(position.getUserId());
        if("03".equals(user.getUserType())){
            return ResponseVo.error("您不是企业用户，不能发布职位");
        }
        //更新浏览数，投递数
        List<Deliver> listDeliver =deliverMapper.selectDeliverListByPositionId(position.getId());
        position.setDeliverNum(listDeliver.size());
        int f = positionMapper.update(position);
        if(f>0){
            return ResponseVo.success("修改成功");
        }else{
            return ResponseVo.error("修改失败");
        }
    }

    /**
     * 我发布的职位
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getMyReleasedJobsList(PositionVo positionVo ,Integer userId) {
        PageHelper.startPage(positionVo.getPageNum(),positionVo.getPageSize());
        List<Position> list =positionMapper.selectPositionByUserId(userId);
        PageInfo pageInfo = new PageInfo<>(list);
        return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
    }
}
