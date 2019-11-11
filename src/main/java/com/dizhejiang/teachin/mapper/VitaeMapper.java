package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Vitae;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface VitaeMapper {
    /**
     * 保存我的简历
     * @param vitae
     * @param userId
     * @return
     */
    int save(@Param("vitae") Vitae vitae, @Param("userId") Integer userId);
    int update(@Param("vitae") Vitae vitae, @Param("userId") Integer userId);

    /**
     * 我的简历
     * @param userId
     * @return
     */
    Vitae selectVitaeByUserId( @Param("userId") Integer userId);
}
