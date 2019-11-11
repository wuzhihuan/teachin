package com.dizhejiang.teachin.common;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
public class PageableList {
    private int pageno;
    private int pageSize=10;
    private int sum;
    private int totalPage=0;

    public PageableList(int pageno) {
        this.pageno = pageno;
    }

    public PageAble page(List list){
        PageAble page=new PageAble();
        //分页
        Integer totalNum = list.size();
        //默认从零分页，这里要考虑这种情况，下面要计算。
        int pageNum = pageno + 1;
        if (totalNum > 0) {
            totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        }
        if (pageNum > totalPage) {
            pageNum = totalPage;
        }
        int startPoint = (pageNum - 1) * pageSize;
        int endPoint = startPoint + pageSize;
        if (totalNum <= endPoint) {
            endPoint = totalNum;
        }
        list = list.subList(startPoint, endPoint);
        page.setCode("200");
        page.setPageno(pageno);
        page.setSum(pageNum);
        page.setTotalPage(totalPage);
        page.setData(list);

        return page;
    }

}
