package com.dizhejiang.teachin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class BaseController {

    private HttpServletRequest request;

    private HttpServletResponse response;

    /**
     * 服务是否启动检查
     * @return
     */
//    @GetMapping("/")
//    public ResponseVo<String> startUp(){
//        if (request.getCookies()==null)
//            throw new ResponseException(ResponseEnum.ERROR);
//        log.info(JSON.toJSONString(Arrays.asList(request.getCookies())));
//        for (Cookie cookie: Arrays.asList(request.getCookies())){
//            log.info(cookie.getName()+"="+cookie.getValue());
//        }
//        log.info(JSON.toJSONString(request.getHeader("test1")));
//        log.info(JSON.toJSONString(request.getHeader("test2")));
//        return ResponseVo.success("application start up ...");
//    }

    public String getUserIp(HttpServletRequest request){
        //String ip = "127.0.0.1";
        String ip ="101.37.245.98";
        return ip;
    }

    public Integer getAppType(HttpServletRequest request){
        Integer appType = 1;
        return appType;
    }
}
