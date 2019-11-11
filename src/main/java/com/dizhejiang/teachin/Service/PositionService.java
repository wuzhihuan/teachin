package com.dizhejiang.teachin.Service;

import com.dizhejiang.teachin.model.Position;
import com.dizhejiang.teachin.vo.PositionVo;
import com.dizhejiang.teachin.vo.ResponseVo;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
public interface PositionService {
    /**
     * 获取职位详情
     * @param id
     * @return
     */
    ResponseVo getJobDetail(Integer id,Integer userId);

    /**
     * 所属职位
     * @param position
     * @return
     */
    ResponseVo searchJobs(String position);

    /**
     * 保存
     * @param position
     * @return
     */
    ResponseVo releaseJobs(Position position,Integer userId);

    /**
     * 修改
     * @param position
     * @return
     */
    ResponseVo updateJobs(Position position);

    /**
     * 我发布的职位
     * @param userId
     * @return
     */
    ResponseVo getMyReleasedJobsList(PositionVo positionVo, Integer userId);
}
