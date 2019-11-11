package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.TeacherAttestation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface TeacherAttestationMapper {
    /**
     * 保存
     * @param teacherAttestation
     * @return
     */
    int save(@Param("teacherAttestation") TeacherAttestation teacherAttestation);
    int update(@Param("teacherAttestation") TeacherAttestation teacherAttestation);

    List<TeacherAttestation> selectMaxModel();

    TeacherAttestation selectModelByUserId(@Param("userId") Integer userId);
}
