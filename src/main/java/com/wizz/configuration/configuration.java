package com.wizz.configuration;

import com.wizz.interceptors.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

////配置用户登录拦截器
//@Configuration
//public class configuration implements WebMvcConfigurer {
//    @Autowired
//    PassportInterceptor passportInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(passportInterceptor).addPathPatterns("/user/*/");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//}
