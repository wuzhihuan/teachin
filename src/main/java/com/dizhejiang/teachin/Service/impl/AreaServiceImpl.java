package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.AreaService;
import com.dizhejiang.teachin.mapper.AreaMapper;
import com.dizhejiang.teachin.model.Area;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public ResponseVo getProvinceList() {
        List<Area> list = areaMapper.getProvinceList();
        return ResponseVo.success(list);
    }
}
