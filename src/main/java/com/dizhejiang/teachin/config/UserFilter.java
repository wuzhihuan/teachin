

package com.dizhejiang.teachin.config;

import com.alibaba.fastjson.JSON;
import com.dizhejiang.teachin.common.AuthenticationUtil;
import com.dizhejiang.teachin.common.RedisUtil;
import com.dizhejiang.teachin.common.StringToUtil;
import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.vo.LoginCacheVo;
import com.dizhejiang.teachin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * 拦截器
 */

@Order(1)
@WebFilter(filterName = "userFilter", urlPatterns = "/api/*")
public class UserFilter implements Filter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init...");
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter...");
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if(token == null || !redisUtil.exists(token)){
            throw new ResponseException(ResponseEnum.JWT_INVALID_CLAIM);
        }


        /*Object json = redisUtil.get(token);
        String ss = (String) json;
        String str="";
        List<String> stringList= StringToUtil.getFullNumFromString(ss);
        for(int i = 0;i<stringList.size();i++){
            str=str+stringList.get(i);
        }
        Integer userId =Integer.parseInt(str);*/
        Object json = redisUtil.get(token);
        String ss = (String) json;
        String str="";
        try {
            str =   URLDecoder.decode(ss, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Integer userId =Integer.parseInt(str);

        AuthenticationUtil.setCurrentAccount(userId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy...");
    }
}


