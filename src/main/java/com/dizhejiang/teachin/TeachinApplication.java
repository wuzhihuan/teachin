package com.dizhejiang.teachin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@ComponentScan("com.dizhejiang")
@ServletComponentScan
@MapperScan(value = "com.dizhejiang.teachin.mapper")
@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
public class TeachinApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeachinApplication.class, args);
	}
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		// 设置允许跨域请求的域名
		config.addAllowedOrigin("*");
		// 是否允许证书 不再默认开启
		// config.setAllowCredentials(true);
		// 设置允许的方法
		config.addAllowedMethod("*");
		// 允许任何头
		config.addAllowedHeader("*");
		config.addExposedHeader("token");
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);
		return new CorsFilter(configSource);
	}

}
