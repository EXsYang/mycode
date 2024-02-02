package com.hspedu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangda
 * @create 2024-01-04-22:38
 * @description: CustomizationBean 配置类
 * 配置注入RestTemplate bean/对象
 */
@Configuration
public class CustomizationBean {

    //配置注入RestTemplate bean/对象
    //这里的@LoadBalanced 就是赋予 RestTemplate 负载均衡的能力
    //默认是使用轮询算法来访问远程调用接口/地址
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}

