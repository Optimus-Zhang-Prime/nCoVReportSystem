package com.wizz.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @descrip：跨域设置
 * @author: 李佳
 * @create： 2020-03-02-09:19
 **/
@Configuration
public class CorsConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置要暴露的header，因为非同源的情况下，不能获取全部头部信息
        String [] headers = {"set-cookie","Access-Control-Request-Method",
                "Access-Control-Request-Headers","Origin","accept","Access-Control-Allow-Origin","Content-Disposition","Content-Type","X-Requested-With"};
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .exposedHeaders(headers);
    }
}
