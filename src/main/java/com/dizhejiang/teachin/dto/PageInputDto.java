package com.dizhejiang.teachin.dto;

import lombok.Data;

/**
 * @Auther:
 * @Date: 2019/3/30 17:19
 * @Description:
 */
@Data
public class PageInputDto {

    private int pageNum=1;

    private int pageSize=10;

    private Integer type;
    public PageInputDto() {
    }

    public PageInputDto(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public void initMaxPage(){
        this.setPageNum(1);
        this.setPageSize(Integer.MAX_VALUE);
    }

}
