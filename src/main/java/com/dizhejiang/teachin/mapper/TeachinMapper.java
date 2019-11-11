package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.model.Teachin;
import com.dizhejiang.teachin.vo.TeachinVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface TeachinMapper {
    /**
     * 宣讲会列表
     * @param dto
     * @return
     */
    List<Teachin> getToastmastersList(@Param("dto") TeachinDto dto ,@Param("selectDate") String selectDate);

    List<Teachin> getToastmastersListOrderByEed(@Param("dto") TeachinDto dto ,@Param("selectDate") String selectDate);

    /**
     * 详情
     * @param id
     * @return
     */
    Teachin getTeachinById(@Param("id") Integer id);

    /**
     * 其他宣讲会id-本公司（最近3场）
     * @param id
     * @param credit
     * @return
     */
    List<Teachin> selectTeachinByOther(@Param("id") Integer id,@Param("credit") String  credit);

    /**
     * 通过学校id来查询
     */
    List<TeachinVo> getTeachinBySchoolId(@Param("schoolId") Integer schoolId ,@Param("dto") TeachinDto dto);

    /**
     * 获取我的草稿
     * @param status
     * @param userId
     * @return
     */
    List<Teachin> selectModelByStatus(@Param("status") String status,@Param("userId") Integer userId);

    /**
     * 通过公司id查询
     * @param companyId
     * @return
     */
    List<TeachinVo> getTeachinByCompanyId(@Param("companyId") Integer companyId , @Param("dto") TeachinDto dto);

    /**
     * 通过id查询,日历
     * @param idList
     * @return
     */
    List<TeachinVo> selectModelByIdList(@Param("idList")List<Integer> idList);

    /**
     * 我的宣讲会
     * @param idList
     * @return
     */
    List<TeachinVo> selectMyModelByIdList(@Param("idList")List<Integer> idList,@Param("userId") Integer userId);

    /**
     * 通过社群id查询
     * @param groupId
     * @return
     */
    List<TeachinVo> getTeachinByGroupId(@Param("groupId") Integer groupId,@Param("happenTime") String happenTime);

    /**
     * 日历获取宣讲会
     * @param dto
     * @return
     */
    List<TeachinVo> getTeachinByGroupIdAndTime(@Param("dto") TeachinDto dto);

    /**
     * 查询我发布的宣讲会
     * @param userId
     * @return
     */
    List<TeachinVo> selectModelByUserId(@Param("userId") Integer userId);

    /**
     * 保存
     * @param teachin
     * @return
     */
    int save(@Param("teachin") Teachin teachin);

    /**
     * 更新
     * @param teachin
     * @return
     */
    int update(@Param("teachin") Teachin teachin);

    /**
     *
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);


    /**
     * 时间地点查询
     * @param teachin
     * @return
     */
    List<Teachin> selectModelByTimeAndPlace(@Param("teachin") Teachin teachin);

    /**
     * 取消,更新状态
     * @param status
     * @param id
     * @return
     */
   int  updateStatus(@Param("status") String status,@Param("id") Integer id);
    List<Teachin> selectModelByMax();


}
