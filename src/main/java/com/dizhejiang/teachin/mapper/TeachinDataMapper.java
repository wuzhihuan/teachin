package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.TeachinData;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface TeachinDataMapper {
    /**
     * 获取宣讲会的数据
     * @param teachinId
     * @return
     */
    TeachinData selectModelByTeachinId(@Param("teachinId") Integer teachinId);

    /**
     * 保存
     * @param teachinData
     * @return
     */
    int save(@Param("teachinData") TeachinData teachinData);

    /**
     * 更新
     * @param teachinData
     * @return
     */
    int update(@Param("teachinData") TeachinData teachinData);

}
