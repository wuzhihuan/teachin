package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.GroupAttestationService;
import com.dizhejiang.teachin.mapper.AppendixMapper;
import com.dizhejiang.teachin.mapper.GroupAttestationMapper;
import com.dizhejiang.teachin.mapper.UserMapper;
import com.dizhejiang.teachin.model.Appendix;
import com.dizhejiang.teachin.model.CompanyAttestation;
import com.dizhejiang.teachin.model.GroupAttestation;
import com.dizhejiang.teachin.vo.GroupAttestationVo;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
@Service
public class GroupAttestationServiceImpl implements GroupAttestationService {
    @Autowired
    private GroupAttestationMapper groupAttestationMapper;
    @Autowired
    private AppendixMapper appendixMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 认证社群
     * @param groupAttestationVo
     * @param userId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo saveGroupAttestation(GroupAttestationVo groupAttestationVo, Integer userId) {
        //非空验证
        if(StringUtils.isEmpty(groupAttestationVo.getSchoolName())){
            return ResponseVo.error("学校名称不能为空");
        }
        if(groupAttestationVo.getSchoolId()==null){
            return ResponseVo.error("学校id不能为空");
        }
        if(StringUtils.isEmpty(groupAttestationVo.getIdNo())){
            return ResponseVo.error("身份证号不能为空");
        }
        if(StringUtils.isEmpty(groupAttestationVo.getSno())){
            return ResponseVo.error("学号不能为空");
        }
        if(groupAttestationVo.getProve().size()<0){
            return ResponseVo.error("文件不能为空");
        }
        if(userId==null){
            return ResponseVo.error("用户id不能为空");
        }
        GroupAttestation groupAttestation1 =groupAttestationMapper.selectByUserId(userId);
        if(groupAttestation1!=null){
            groupAttestation1.setSchoolId(groupAttestationVo.getSchoolId());
            groupAttestation1.setIdNo(groupAttestationVo.getIdNo());
            groupAttestation1.setName(groupAttestationVo.getName());
            groupAttestation1.setPhone(groupAttestationVo.getPhone());
            groupAttestation1.setSchoolName(groupAttestationVo.getSchoolName());
            groupAttestation1.setSno(groupAttestationVo.getSno());
            groupAttestation1.setUserId(userId);
            groupAttestation1.setDepartment(groupAttestationVo.getDepartment());
            groupAttestation1.setStatus("01");
            int f= groupAttestationMapper.update(groupAttestation1);
            for(int i =0;i<groupAttestationVo.getProve().size();i++){
                Appendix appendix = new Appendix();
                appendix.setUrl(groupAttestationVo.getProve().get(i));
                appendix.setSourceType("10");
                appendix.setSource(groupAttestation1.getId());
                appendixMapper.save(appendix);

            }
            userMapper.updateStatus("03",userId);
            if(f<1){
                return ResponseVo.error("保存失败");
            }else{
                return ResponseVo.success("保存成功");
            }
        }else {
            GroupAttestation groupAttestation =new GroupAttestation();
            groupAttestation.setSchoolId(groupAttestationVo.getSchoolId());
            groupAttestation.setIdNo(groupAttestationVo.getIdNo());
            groupAttestation.setName(groupAttestationVo.getName());
            groupAttestation.setPhone(groupAttestationVo.getPhone());
            groupAttestation.setSchoolName(groupAttestationVo.getSchoolName());
            groupAttestation.setSno(groupAttestationVo.getSno());
            groupAttestation.setUserId(userId);
            groupAttestation.setStatus("01");
            groupAttestation.setDepartment(groupAttestationVo.getDepartment());
            int f= groupAttestationMapper.save(groupAttestation);
            List<GroupAttestation> list1=    groupAttestationMapper.selectMaxModel();
            for(int i =0;i<groupAttestationVo.getProve().size();i++){
                Appendix appendix = new Appendix();
                appendix.setUrl(groupAttestationVo.getProve().get(i));
                appendix.setSourceType("10");
                appendix.setSource(list1.get(0).getId());
                appendixMapper.save(appendix);

            }
            userMapper.updateStatus("03",userId);
            if(f<1){
                return ResponseVo.error("保存失败");
            }else{
                return ResponseVo.success("保存成功");
            }
        }


    }
}
