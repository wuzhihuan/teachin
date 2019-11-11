package com.dizhejiang.teachin.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Data
public class TeachinDto {
    //id
    private Integer id;
    private Integer pageNum ;
    private Integer pageSize;

    //学校id
    private List<Integer> listSchool;
    //行业id
    private List<Integer>  industryId;
    //宣讲会日期类型（7种类型）
    //全部：0
    //今天：1
    //明天：2
    //三天内：3
    //一周内：4
    //一个月内：5
    //三个月内：6
    //已结束：7
    //（传空为所有数据）
    private Integer datePhase;
    //模糊搜索关键字从以下字段判断
    //title // 标题
    //schoolName // 学校名称
    //province // 省
    //（传空为所有数据）
    private String keyword;
    private String happenTime;
//其实时间
    private String startTime;
//结束时间
    private String endTime;

    private String nowDate;
    private String city;

    private Integer userId;

    private String province;

    private String lastDate;
    private String nextDate;
    //0标识是我发布的和与我有关的，1标识是我发布的
    private int isMy;
}
