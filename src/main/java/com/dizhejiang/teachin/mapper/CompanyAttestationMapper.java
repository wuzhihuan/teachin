package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.CompanyAttestation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface CompanyAttestationMapper {
    /**
     * 保存
     * @param companyAttestation
     * @return
     */
    int save(@Param("companyAttestation") CompanyAttestation companyAttestation);
    int update(@Param("companyAttestation") CompanyAttestation companyAttestation);

    /**
     * 查询最大的
     * @return
     */
    List<CompanyAttestation> selectMaxModel();

    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    CompanyAttestation selectModelByUserId(@Param("userId") Integer userId);
}
