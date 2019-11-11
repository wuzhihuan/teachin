package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.School;
import com.dizhejiang.teachin.model.SchoolZd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface SchoolMapper {
    /**
     * 获取学校列表by省份
     * @param province
     * @return
     */
    List<School> getSchoolList(@Param("province") String province);

    /**
     * 查询学校
     * @param schoolName
     * @return
     */
    List<School> searchSchool(@Param("schoolName") String schoolName);

    /**
     * 导入数据
     * @return
     */
    List<SchoolZd> getData();

    int save(@Param("school") School school);
}
