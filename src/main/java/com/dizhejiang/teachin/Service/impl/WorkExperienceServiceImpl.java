package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.WorkExperienceService;
import com.dizhejiang.teachin.mapper.WorkExperienceMapper;
import com.dizhejiang.teachin.model.WorkExperience;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
    @Autowired
    private WorkExperienceMapper workExperienceMapper;

    /**
     * 新增
     * @param workExperience
     * @return
     */
    @Override
    public ResponseVo addWorkExperience(WorkExperience workExperience,Integer userId) {
        //非空验证
        if(userId==null){
            return ResponseVo.error("登录超时或者未登录");
        }
        if(StringUtils.isEmpty(workExperience.getCompanyName())){
            return ResponseVo.error("公司名称不能为空");
        }
       /* if(StringUtils.isEmpty(workExperience.getCompanyType())){
            return ResponseVo.error("公司性质不能为空");
        }*/
      /*  if(StringUtils.isEmpty(workExperience.getScope())){
            return ResponseVo.error("企业规模不能为空");
        }*/
        /*if(StringUtils.isEmpty(workExperience.getPart())){
            return ResponseVo.error("部门不能为空");
        }*/
        if(StringUtils.isEmpty(workExperience.getPosition())){
            return ResponseVo.error("职位不能为空");
        }
        if(StringUtils.isEmpty(workExperience.getEnterDate())){
            return ResponseVo.error("开始时间不能为空");
        }
        if(StringUtils.isEmpty(workExperience.getLeaveDate())){
            return ResponseVo.error("离开时间不能为空");
        }
        if(StringUtils.isEmpty(workExperience.getDetail())){
            return ResponseVo.error("描述不能为空");
        }
        /*if(StringUtils.isEmpty(workExperience.getSalary())){
            return ResponseVo.error("薪资不能为空");
        }*/
       /* if(StringUtils.isEmpty(workExperience.getDuty())){
            return ResponseVo.error("职责不能为空");
        }*/
        Integer i=workExperience.getEnterDate().compareTo(workExperience.getLeaveDate());
        if(i>0){
            return ResponseVo.error("开始时间不能大于结束时间");
        }
        workExperience.setUserId(userId);
        workExperience.setStatus("01");
        workExperienceMapper.save(workExperience);
        return ResponseVo.success("新增成功");
    }

    /**
     * 更新
     * @param workExperience
     * @return
     */
    @Override
    public ResponseVo updateWorkExperience(WorkExperience workExperience) {
        workExperienceMapper.update(workExperience);
        return ResponseVo.success("修改成功");
    }

    /**
     * 删除
     * @param workExperience
     * @return
     */
    @Override
    public ResponseVo deleteWorkExperience(WorkExperience workExperience) {
        workExperienceMapper.delete(workExperience.getId());
        return ResponseVo.success("删除成功");
    }

    @Override
    public ResponseVo selectWorkExperienceById(WorkExperience workExperience) {
        if(workExperience.getId()==null){
            return ResponseVo.error("没有传对象");
        }
        if(workExperience.getId()==null){

            return ResponseVo.error("id不能为空对象");
        }
        WorkExperience workExperience1= workExperienceMapper.selectWorkExperienceById(workExperience.getId());
        return ResponseVo.success(workExperience1);
    }
}
