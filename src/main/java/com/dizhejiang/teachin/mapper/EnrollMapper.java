package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.model.Enroll;
import com.dizhejiang.teachin.model.Teachin;
import com.dizhejiang.teachin.vo.TeachinVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface EnrollMapper {
    /**
     * 报名，取消报名
     * @param status
     * @param teachId
     * @param userId
     * @return
     */
    int update(@Param("status") String status, @Param("teachId") Integer teachId, @Param("userId") Integer userId,@Param("createTime") String createTime);

    /**
     * 根据宣讲会id和用户id,查询是否报名
     * @param teachId
     * @param userId
     * @return
     */
    Enroll selectEnrollByTeachIdAndUserId(@Param("teachId") Integer teachId, @Param("userId") Integer userId);

    /**
     * 保存报名
     * @param teachId
     * @param userId
     * @return
     */
    int save(@Param("teachId") Integer teachId, @Param("userId") Integer userId,@Param("status") String status,@Param("createTime") String createTime);

    /**
     * 我的宣讲会-01普通用户
     * @param userId
     * @return
     */
    List<TeachinVo> selectEnrollByUserId(@Param("userId") Integer userId,@Param("happenTime") String happenTime );

    /**
     * 获取日历中的有宣讲会的时间
     * @param dto
     * @return
     */
    List<TeachinVo> selectEnrollByUserIdAndTime(@Param("dto") TeachinDto dto);

    /**
     * 查到刚刚插入的
     * @return
     */
    List<Enroll> selectModelMax();
    List<Enroll> selectEnrollListByUserId(@Param("userId") Integer userId);
}
