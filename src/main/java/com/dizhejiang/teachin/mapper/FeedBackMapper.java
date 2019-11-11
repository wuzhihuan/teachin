package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.model.FeedBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/11/4
 */
public interface FeedBackMapper {
   int save(@Param("feedBack")FeedBack feedBack);
    List<FeedBack> selectMax();
}
