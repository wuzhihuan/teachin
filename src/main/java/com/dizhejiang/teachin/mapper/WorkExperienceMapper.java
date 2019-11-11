package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.WorkExperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface WorkExperienceMapper {
    /**
     * 查询实际的工作经历
     * @param userId
     * @return
     */
    List<WorkExperience> selectWorkExperienceListByUserId(@Param("userId") Integer userId);

    WorkExperience selectWorkExperienceById(@Param("id") Integer id);

    /**
     * 保存工作经历
     * @param workExperience
     * @return
     */
    int save(@Param("workExperience") WorkExperience workExperience);

    /**
     * 更新
     * @param workExperience
     * @return
     */
    int update(@Param("workExperience") WorkExperience workExperience);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);
}
