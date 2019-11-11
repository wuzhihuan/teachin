package com.dizhejiang.teachin.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Data
public class
UserVo {
    //token
    private String token;
    //用户类型
    private String userType;
    private String userName;
    private Integer id;
    private String head;

    /**
     * 转换成jwt所需要的map
     */
    public Map<String, String> toJwtMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId",id.toString());
        //map.put("userName", userName);
       // map.put("head",head);
        return map;
    }
}
