package com.dizhejiang.teachin.model;

import javafx.scene.control.IndexRange;
import lombok.Data;

/**
 * @Author wuqi
 * @Date 2019/10/29
 */
@Data
public class Report {
    private Integer id;
    private Integer teachinId;
    //01重复信息，02不实信息，03咋骗信息，04垃圾营销05,违法信息，06其他原因
    private String type;
    private Integer userId;
    //内容
    private String content;
}
