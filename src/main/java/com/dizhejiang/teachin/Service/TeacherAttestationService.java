package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.TeacherAttestation;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeacherAttestationVo;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface TeacherAttestationService {
    /**
     * 老师认证
     * @param teacherAttestationVo
     * @return
     */
    ResponseVo teacherCertification(TeacherAttestationVo teacherAttestationVo, Integer userId);
}
