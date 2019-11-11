package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.ItemExperience;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface ItemExperienceService {
    /**
     * 添加项目经验
     * @param itemExperience
     * @return
     */
    ResponseVo addProjectExperience(ItemExperience itemExperience,Integer userId);

    /**
     * 修改项目经验
     * @param itemExperience
     * @return
     */
    ResponseVo updateProjectExperience(ItemExperience itemExperience);

    /**
     * 删除项目经验
     * @param itemExperience
     * @return
     */
    ResponseVo deleteProjectExperience(ItemExperience itemExperience);

    ResponseVo selectProjectExperienceById(ItemExperience itemExperience);
}
