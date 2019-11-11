package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Appendix;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
public interface AppendixMapper {
    int save(@Param("appendix") Appendix appendix);
}
