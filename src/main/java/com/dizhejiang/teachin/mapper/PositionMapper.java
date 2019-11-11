package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface PositionMapper {
    /**
     * 通过公司id，查询职位
     * @param credit
     * @return
     */
    List<Position> selectPositionByCompanyId(@Param("credit") String  credit);
    List<Position> selectModelMax();

    /**
     * 获取职位详情
     * @param id
     * @return
     */
    Position selectPositionById(@Param("id") Integer id);

    /**
     * 所属职位
     * @param position
     * @return
     */
    List<Position> searchJobs(@Param("position") String position);

    /**
     * 保存
     * @param position
     * @return
     */
    int save(@Param("position") Position position);

    /**
     * 修改
     * @param position
     * @return
     */
    int  update(@Param("position") Position position);

    /**
     * 我发布的诸位
     * @param userId
     * @return
     */
    List<Position> selectPositionByUserId(@Param("userId") Integer userId);


}
