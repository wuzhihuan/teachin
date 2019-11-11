package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.CompanyAttestationService;
import com.dizhejiang.teachin.mapper.AppendixMapper;
import com.dizhejiang.teachin.mapper.CompanyAttestationMapper;
import com.dizhejiang.teachin.mapper.UserMapper;
import com.dizhejiang.teachin.model.Appendix;
import com.dizhejiang.teachin.model.CompanyAttestation;
import com.dizhejiang.teachin.model.TeacherAttestation;
import com.dizhejiang.teachin.vo.CompanyAttestationVo;
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
public class CompanyAttestationServiceImpl implements CompanyAttestationService {
    @Autowired
    private CompanyAttestationMapper companyAttestationMapper;
    @Autowired
    private AppendixMapper appendixMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo enterpriseCertification(CompanyAttestationVo companyAttestationVo, Integer userId) {
        //毕传字段验证
        if(StringUtils.isEmpty( companyAttestationVo.getCompanyName())){
            return ResponseVo.error("企业名称必填");
        }
        if(StringUtils.isEmpty( companyAttestationVo.getCredit())){
            return ResponseVo.error("信用代码必填");
        }
        if(StringUtils.isEmpty( companyAttestationVo.getName())){
            return ResponseVo.error("姓名必填");
        }
        if(StringUtils.isEmpty( companyAttestationVo.getIdNo())){
            return ResponseVo.error("身份证号码必填");
        }
        if(StringUtils.isEmpty( companyAttestationVo.getPosition())){
            return ResponseVo.error("职务必填");
        }
        if(companyAttestationVo.getMaterialUrl().size()<0){
            return ResponseVo.error("文件必填");
        }
        if(StringUtils.isEmpty( companyAttestationVo.getIndustry())){
            return ResponseVo.error("行业必填");
        }
        if(userId==null){
            return ResponseVo.error("未登录，或者登录超时");
        }
        CompanyAttestation companyAttestation1 =  companyAttestationMapper.selectModelByUserId(userId);
        if(companyAttestation1==null){
            CompanyAttestation companyAttestation =  new CompanyAttestation();
            companyAttestation.setCompanyName(companyAttestationVo.getCompanyName());
            companyAttestation.setCredit(companyAttestationVo.getCredit());
            companyAttestation.setIdNo(companyAttestationVo.getIdNo());
            companyAttestation.setIndustry(companyAttestationVo.getIndustry());
            companyAttestation.setName(companyAttestationVo.getName());
            companyAttestation.setPosition(companyAttestationVo.getPosition());
            companyAttestation.setUserId(userId);
            companyAttestation.setStatus("01");
            companyAttestationMapper.save(companyAttestation);
            List<CompanyAttestation> list =  companyAttestationMapper.selectMaxModel();
            for(int i =0;i<companyAttestationVo.getMaterialUrl().size();i++){
                Appendix appendix = new Appendix();
                appendix.setUrl(companyAttestationVo.getMaterialUrl().get(i));
                appendix.setSourceType("10");
                appendix.setSource(list.get(0).getId());
                appendixMapper.save(appendix);

            }
            userMapper.updateStatus("02",userId);
            return ResponseVo.success("企业人员认证提交成功，等待审核");
        }else{
            //CompanyAttestation1 companyAttestation =  new CompanyAttestation();
            companyAttestation1.setCompanyName(companyAttestationVo.getCompanyName());
            companyAttestation1.setCredit(companyAttestationVo.getCredit());
            companyAttestation1.setIdNo(companyAttestationVo.getIdNo());
            companyAttestation1.setIndustry(companyAttestationVo.getIndustry());
            companyAttestation1.setName(companyAttestationVo.getName());
            companyAttestation1.setPosition(companyAttestationVo.getPosition());
            companyAttestation1.setUserId(userId);
            companyAttestation1.setStatus("01");
            companyAttestationMapper.update(companyAttestation1);
           // List<CompanyAttestation> list =  companyAttestationMapper.selectMaxModel();
            for(int i =0;i<companyAttestationVo.getMaterialUrl().size();i++){
                Appendix appendix = new Appendix();
                appendix.setUrl(companyAttestationVo.getMaterialUrl().get(i));
                appendix.setSourceType("10");
                appendix.setSource(companyAttestation1.getId());
                appendixMapper.save(appendix);

            }
            userMapper.updateStatus("02",userId);
            return ResponseVo.success("企业人员认证提交成功，等待审核");
        }

    }
}
