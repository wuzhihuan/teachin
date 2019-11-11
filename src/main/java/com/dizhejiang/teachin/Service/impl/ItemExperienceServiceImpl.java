package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.ItemExperienceService;
import com.dizhejiang.teachin.mapper.ItemExperienceMapper;
import com.dizhejiang.teachin.model.ItemExperience;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Service
public class ItemExperienceServiceImpl implements ItemExperienceService {
    @Autowired
    private ItemExperienceMapper itemExperienceMapper;

    /**
     * 添加
     * @param itemExperience
     * @return
     */
    @Override
    public ResponseVo addProjectExperience(ItemExperience itemExperience, Integer userId) {
        //非空验证
        if(userId==null){
            return ResponseVo.error("未登录，或登录超时");
        }
      /*  if(StringUtils.isEmpty(itemExperience.getCompanyName())){
            return ResponseVo.error("公司名称不能为空");
        }*/
        if(StringUtils.isEmpty(itemExperience.getProjectName())){
            return ResponseVo.error("项目名称不能为空");
        }
        if(StringUtils.isEmpty(itemExperience.getDescription())){
            return ResponseVo.error("项目描述不能为空");
        }
        if(StringUtils.isEmpty(itemExperience.getDuty())){
            return ResponseVo.error("职责不能为空");
        }
        /*if(StringUtils.isEmpty(itemExperience.getAchievement())){
            return ResponseVo.error("成果不能为空");
        }*/
        if(StringUtils.isEmpty(itemExperience.getStartTime())){
            return ResponseVo.error("开始时间不能为空");
        }
        if(StringUtils.isEmpty(itemExperience.getEndTime())){
            return ResponseVo.error("结束时间不能为空");
        }
        Integer i=itemExperience.getStartTime().compareTo(itemExperience.getEndTime());
        if(i>0){
            return ResponseVo.error("开始时间不能大于结束时间");
        }
        itemExperience.setUserId(userId);
        itemExperience.setStatus("01");
        itemExperienceMapper.save(itemExperience);
        return ResponseVo.success("新增成功");
    }

    /**
     * 修改
     * @param itemExperience
     * @return
     */
    @Override
    public ResponseVo updateProjectExperience(ItemExperience itemExperience) {
        itemExperienceMapper.update(itemExperience);
        return ResponseVo.success("更新成功");
    }

    /**
     * 删除
     * @param itemExperience
     * @return
     */
    @Override
    public ResponseVo deleteProjectExperience(ItemExperience itemExperience) {
        itemExperienceMapper.delete(itemExperience.getId());
        return ResponseVo.success("删除成功");
    }

    @Override
    public ResponseVo selectProjectExperienceById(ItemExperience itemExperience) {
        if(itemExperience==null){
            return ResponseVo.error("对象不能为空");
        }
        if(itemExperience.getId()==null){
            return ResponseVo.error("id不能为空");
        }
        ItemExperience itemExperience1=  itemExperienceMapper.selectProjectExperienceById(itemExperience.getId());

        return ResponseVo.success(itemExperience1);
    }
}
