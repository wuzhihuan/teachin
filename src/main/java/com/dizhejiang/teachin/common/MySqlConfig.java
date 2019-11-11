package com.dizhejiang.teachin.common;


import org.springframework.beans.factory.annotation.Value;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
public class MySqlConfig {
    static final String PACKAGE = "com.dizhejiang";

    @Value("${spring.datasource.url}")
    public static String url;

    @Value("${spring.datasource.username}")
    public static String user;

    @Value("${spring.datasource.password}")
    public static String password;

    @Value("${spring.datasource.driverClassName}")
    public static String driverClass;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.publicKey}")
    private String publicKey;
}
