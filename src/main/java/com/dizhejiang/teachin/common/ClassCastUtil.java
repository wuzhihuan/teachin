package com.dizhejiang.teachin.common;

import com.dizhejiang.teachin.dto.PageOutputDto;
import com.github.pagehelper.PageInfo;


public class ClassCastUtil {

    public static <T> PageOutputDto<T> pageInfoToPageOutputDto(PageInfo<T> pageInfo) {

        PageOutputDto dto = new PageOutputDto();
        dto.setHasNext(pageInfo.isHasNextPage());
        dto.setList(pageInfo.getList());
        dto.setPage(pageInfo.getPages());
        dto.setTotal(pageInfo.getTotal());
        dto.setHasPre(pageInfo.isHasPreviousPage());

        return dto;
    }

}
