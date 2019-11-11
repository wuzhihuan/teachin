package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.Industry;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface IndustryService {
    /**
     * 获取行业列表
     * @return
     */
    ResponseVo getIndustryList(Industry industry);
    ResponseVo getIndustryList1();
}
