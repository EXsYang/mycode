package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangda
 * @create 2024-01-04-22:34
 * @description: http://localhost/member/nacos/consumer/get/5
 */
@SpringBootApplication
@EnableDiscoveryClient //引入的是启动 nacos发现注解
@EnableFeignClients //启用openfeign , 否则openfeign不生效 启动报错
public class MemberNacosConsumerApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(MemberNacosConsumerApplication80.class,args);
        System.out.println("MemberNacosConsumerApplication80 starting...");
    }
}
