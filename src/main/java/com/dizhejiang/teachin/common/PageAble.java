package com.dizhejiang.teachin.common;

import com.dizhejiang.teachin.model.Teachin;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
public class PageAble {
    private String code;
    private int pageno;
    private int totalPage;
    private int sum;
    private List<Teachin> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<Teachin> getData() {
        return data;
    }

    public void setData(List<Teachin> data) {
        this.data = data;
    }

}
