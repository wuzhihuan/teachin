package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.TeacherAttestationService;
import com.dizhejiang.teachin.mapper.AppendixMapper;
import com.dizhejiang.teachin.mapper.TeacherAttestationMapper;
import com.dizhejiang.teachin.mapper.UserMapper;
import com.dizhejiang.teachin.model.Appendix;
import com.dizhejiang.teachin.model.TeacherAttestation;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeacherAttestationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
@Service
public class TeacherAttestationServiceImpl implements TeacherAttestationService {
    @Autowired
    private TeacherAttestationMapper teacherAttestationMapper;
    @Autowired
    private AppendixMapper appendixMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 老师认证
     * @param teacherAttestationVo
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo teacherCertification(TeacherAttestationVo teacherAttestationVo, Integer userId) {
        //验证非空
        if(teacherAttestationVo.getSchoolId()==null){
            return ResponseVo.error("学校id不能为空");
        }
        if(StringUtils.isEmpty(teacherAttestationVo.getSchoolName())){
            return ResponseVo.error("学校名称不能为空");
        }
        if(StringUtils.isEmpty(teacherAttestationVo.getIdNo())){
            return ResponseVo.error("身份证号码不能为空");
        }
        if(StringUtils.isEmpty(teacherAttestationVo.getPosition())){
            return ResponseVo.error("职务不能为空");
        }
      /*  if(StringUtils.isEmpty(teacherAttestationVo.getMaterialUrl())){
            return ResponseVo.error("文件不能为空");
        }*/
          if(teacherAttestationVo.getMaterialUrl().size()<0){
              return ResponseVo.error("文件不能为空");
          }

        if(StringUtils.isEmpty(teacherAttestationVo.getName())){
            return ResponseVo.error("姓名不能为空");
        }
        if(userId==null){
            return ResponseVo.error("未登录，或者登录超时");
        }
        TeacherAttestation teacherAttestation1 = teacherAttestationMapper.selectModelByUserId(userId);
        if(teacherAttestation1==null){
            TeacherAttestation teacherAttestation = new TeacherAttestation();
            teacherAttestation.setSchoolId(teacherAttestationVo.getSchoolId());
            teacherAttestation.setIdNo(teacherAttestationVo.getIdNo());
            teacherAttestation.setJobNo(teacherAttestationVo.getJobNo());
            teacherAttestation.setName(teacherAttestationVo.getName());
            teacherAttestation.setPosition(teacherAttestationVo.getPosition());
            teacherAttestation.setSchoolName(teacherAttestationVo.getSchoolName());
            teacherAttestation.setDepartment(teacherAttestationVo.getDepartment());
            teacherAttestation.setStatus("01");
            teacherAttestation.setUserId(userId);
            teacherAttestationMapper.save(teacherAttestation);
            List<TeacherAttestation> list1=    teacherAttestationMapper.selectMaxModel();
            for(int i =0;i<teacherAttestationVo.getMaterialUrl().size();i++){
                Appendix appendix = new Appendix();
                appendix.setUrl(teacherAttestationVo.getMaterialUrl().get(i));
                appendix.setSourceType("10");
                appendix.setSource(list1.get(0).getId());
                appendixMapper.save(appendix);

            }
            userMapper.updateStatus("01",userId);
            return ResponseVo.success("学校老师认证提交成功，等待审核");
        }else{
            teacherAttestation1.setSchoolId(teacherAttestationVo.getSchoolId());
            teacherAttestation1.setIdNo(teacherAttestationVo.getIdNo());
            teacherAttestation1.setJobNo(teacherAttestationVo.getJobNo());
            teacherAttestation1.setName(teacherAttestationVo.getName());
            teacherAttestation1.setPosition(teacherAttestationVo.getPosition());
            teacherAttestation1.setSchoolName(teacherAttestationVo.getSchoolName());
            teacherAttestation1.setDepartment(teacherAttestationVo.getDepartment());
            teacherAttestation1.setStatus("01");
            teacherAttestation1.setUserId(userId);
            teacherAttestationMapper.update(teacherAttestation1);
            List<TeacherAttestation> list1=    teacherAttestationMapper.selectMaxModel();
            for(int i =0;i<teacherAttestationVo.getMaterialUrl().size();i++){
                Appendix appendix = new Appendix();
                appendix.setUrl(teacherAttestationVo.getMaterialUrl().get(i));
                appendix.setSourceType("10");
                appendix.setSource(list1.get(0).getId());
                appendixMapper.save(appendix);

            }
            userMapper.updateStatus("01",userId);
            return ResponseVo.success("学校老师认证提交成功，等待审核");
        }

    }
}
