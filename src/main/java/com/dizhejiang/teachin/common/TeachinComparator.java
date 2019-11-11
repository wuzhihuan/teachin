package com.dizhejiang.teachin.common;

import com.dizhejiang.teachin.model.Teachin;

import java.util.Comparator;

/**
 * @Author wuqi
 * @Date 2019/11/5
 */
public class TeachinComparator implements Comparator<Teachin> {
    /**
     * 比较器方法
     */
    @Override
    public int compare(Teachin o1, Teachin o2) {
        if(o1.getOrderEnd()>o2.getOrderEnd()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
