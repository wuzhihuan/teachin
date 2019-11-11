package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.GroupAttestation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/23
 */
public interface GroupAttestationMapper {
    /**
     * 保存
     * @param groupAttestation
     * @return
     */
   int save(@Param("groupAttestation") GroupAttestation groupAttestation);
   int update(@Param("groupAttestation") GroupAttestation groupAttestation);

   List<GroupAttestation> selectMaxModel();
    GroupAttestation selectByUserId(@Param("userId") Integer userId);
}
