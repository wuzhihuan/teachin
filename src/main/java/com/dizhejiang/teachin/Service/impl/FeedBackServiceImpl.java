package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.FeedBackService;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.mapper.AppendixMapper;
import com.dizhejiang.teachin.mapper.FeedBackMapper;
import com.dizhejiang.teachin.model.Appendix;
import com.dizhejiang.teachin.model.FeedBack;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/11/4
 */
@Service
public class FeedBackServiceImpl  implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;
    @Autowired
    private AppendixMapper appendixMapper;
    @Override
    public ResponseVo saveFeedBack(FeedBack feedBack) {
        feedBack.setStatus("01");//待处理
        feedBack.setCreateTime(DateUtil.DateToString(new Date()));
        feedBackMapper.save(feedBack);
        List<FeedBack> feedBack1  = feedBackMapper.selectMax();
        for(int i =0;i<feedBack.getUrl().size();i++){
            Appendix appendix = new Appendix();
            appendix.setUrl(feedBack.getUrl().get(i));
            appendix.setSourceType("40");
            appendix.setSource(feedBack1.get(0).getId());//获取来源
            appendixMapper.save(appendix);

        }
        return ResponseVo.success("反馈成功");
    }
}
