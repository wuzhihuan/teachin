package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.WorkExperience;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface WorkExperienceService {
    /**
     * 新增
     * @param workExperience
     * @return
     */
    ResponseVo addWorkExperience(WorkExperience workExperience,Integer userId);

    /**
     * 修改
     * @param workExperience
     * @return
     */
    ResponseVo updateWorkExperience(WorkExperience workExperience);

    /**
     * 删除
     * @param workExperience
     * @return
     */
    ResponseVo deleteWorkExperience(WorkExperience workExperience);
    ResponseVo selectWorkExperienceById(WorkExperience workExperience);
}
