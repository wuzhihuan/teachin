package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.IndustryService;
import com.dizhejiang.teachin.mapper.IndustryMapper;
import com.dizhejiang.teachin.model.Industry;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Service
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    private IndustryMapper industryMapper;

    /**
     * 获取行业列表
     * @return
     */
    @Override
    public ResponseVo getIndustryList(Industry industry) {
        if(industry==null){
            List<Industry> list  = industryMapper.getIndustryList();
            return ResponseVo.success(list);
        }else{
            List<Industry> list  = industryMapper.getIndustryListByName(industry.getIndustryName());
            return ResponseVo.success(list);
        }


    }

    @Override
    public ResponseVo getIndustryList1() {
        List<Industry> list  = industryMapper.getIndustryList();
        return ResponseVo.success(list);
    }
}
