package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.Collect;
import com.dizhejiang.teachin.vo.TeachinVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface CollectMapper {
    /**
     * 通过宣讲会id和用户id查询
     * @param teachId
     * @param userId
     * @return
     */
     Collect selectCollectByTeachIdAndUserId(@Param("teachId") Integer teachId,@Param("userId") Integer userId);

    /**
     * 保存
     * @param teachinId
     * @param userId
     * @return
     */
     int save(@Param("teachinId") Integer teachinId,@Param("userId") Integer userId);

    /**
     * 修改状态
     * @param status
     * @param teachId
     * @param userId
     * @return
     */
     int update(@Param("status") String status, @Param("teachId") Integer teachId, @Param("userId") Integer userId);

    /**
     * 我的收藏列表
     * @param userId
     * @return
     */
    List<TeachinVo> selectCollectListByUserId(@Param("userId") Integer userId);
}
