package com.dizhejiang.teachin.config;

import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.vo.ResponseVo;
import lombok.Data;

/**
 * @Auther: Tt
 * @Date: 2019/3/30 17:49
 * @Description:
 */
@Data
public class ResponseException extends RuntimeException{

    private int code;

    private String msg;

    public ResponseException(ResponseEnum responseEnum) {
        super(responseEnum.getDesc());
        this.msg = responseEnum.getDesc();
        this.code = responseEnum.getCode();
    }

    public ResponseException(ResponseVo responseVo){
        super(responseVo.getData().toString());
        this.code = responseVo.getStatus();
        this.msg = responseVo.getData().toString();
    }

    public ResponseException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ResponseException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ResponseException(String msg) {
        super();
        this.msg = msg;
    }

    public ResponseException() {
        super();
    }

}
