package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.ItemExperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface ItemExperienceMapper {
    /**
     * 获取我的项目经历
     * @param userId
     * @return
     */
    List<ItemExperience> selectItemExperienceListByUserId(@Param("userId") Integer userId);
    ItemExperience selectProjectExperienceById(@Param("id") Integer id);

    /**
     * 保存
     * @param itemExperience
     * @return
     */
    int save(@Param("itemExperience") ItemExperience itemExperience);

    /**
     * 更新
     * @param itemExperience
     * @return
     */
    int update(@Param("itemExperience") ItemExperience itemExperience);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);
}
