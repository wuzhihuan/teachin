package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.SchoolService;
import com.dizhejiang.teachin.mapper.SchoolMapper;
import com.dizhejiang.teachin.model.School;
import com.dizhejiang.teachin.model.SchoolZd;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * 获取学校列表
     * @param province
     * @return
     */
    @Override
    public ResponseVo getSchoolList(String province) {
        List<School> list = schoolMapper.getSchoolList(province);
        return ResponseVo.success(list);
    }

    /**
     * 所属学校
     * @param schoolName
     * @return
     */
    @Override
    public ResponseVo searchSchool(String schoolName) {
        List<School> list = schoolMapper.searchSchool(schoolName);
        return ResponseVo.success(list);
    }

    @Override
    public ResponseVo getData() {
        List<SchoolZd> schoolZdList = schoolMapper.getData();

        for(int i=1000;i<schoolZdList.size();i++){
            School school = new School();
            school.setProvince(schoolZdList.get(i).getProvince());
            school.setSchoolName(schoolZdList.get(i).getSchoolName());
            school.setSchoolCode( schoolZdList.get(i).getTarget());
            school.setCity(schoolZdList.get(i).getCity());
            schoolMapper.save(school);

        }
        return ResponseVo.success("成功");
    }
}
