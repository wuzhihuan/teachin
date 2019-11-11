package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.model.Teachin;
import com.dizhejiang.teachin.vo.PageModel;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeachinVo;

import javax.servlet.ServletRequest;
import java.text.ParseException;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface TeachinService {
    /**
     * 宣讲会列表
     * @param dto
     * @return
     */
    ResponseVo getToastmastersList(TeachinDto dto, ServletRequest request) throws ParseException;

    /**
     * 详情
     * @param id
     * @return
     */
    ResponseVo getToastmastersDetail(Integer id,Integer userId);

    /**
     * 获取我的所有宣讲会
     * @param userId
     * @return
     */
    ResponseVo getMyToastmastersList(TeachinDto dto,Integer userId);

    /**
     * 发布宣讲会
     * @param teachin
     * @param userId
     * @return
     */
    ResponseVo releaseToastmasters(Teachin teachin,Integer userId);

    /**
     * 保存宣讲会草稿
     * @param teachin
     * @param userId
     * @return
     */
    ResponseVo saveToastmastersDrafts(Teachin teachin,Integer userId);

    /**
     * 更新
     * @param teachin
     * @return
     */
    ResponseVo updateToastmasters(Teachin teachin,Integer userId);

    /**
     * 删除
     * @param teachin
     * @param userId
     * @return
     */
    ResponseVo deleteToastmastersDrafts(Teachin teachin,Integer userId);

    /**
     * 获取我的草稿
     * @param userId
     * @return
     */
    ResponseVo getToastmastersDraftsList(PageModel pageModel, Integer userId);

    /**
     * 取消宣讲会
     * @param id
     * @param userId
     * @return
     */
    ResponseVo cancelToastmasters(Integer id,Integer userId);

    /**
     * 我发布的宣讲会详情
     * @param id
     * @param userId
     * @return
     */
    ResponseVo getMyToastmastersDetail(Integer id,Integer userId);

    /**
     * 获取月份的我的宣讲会
     * @param teachinDto
     * @return
     */
    ResponseVo getMyMonthTeachin(TeachinDto teachinDto);
}
