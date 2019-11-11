package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface SchoolService {
    /**
     * 获取学校列表
     * @param province
     * @return
     */
    ResponseVo getSchoolList(String province);

    /**
     * 所属学校
     * @param schoolName
     * @return
     */
    ResponseVo searchSchool(String schoolName);

    /**
     * 导指点的数据到表
     * @return
     */
    ResponseVo getData();
}
