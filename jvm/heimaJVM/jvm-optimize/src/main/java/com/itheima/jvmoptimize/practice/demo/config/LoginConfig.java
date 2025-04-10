package com.itheima.jvmoptimize.practice.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         //注册拦截器
         registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/threadlocal/**");

    }
}