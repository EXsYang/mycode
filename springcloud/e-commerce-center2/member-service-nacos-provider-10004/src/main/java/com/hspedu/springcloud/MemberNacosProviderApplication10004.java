package com.hspedu.springcloud;

import com.hspedu.springcloud.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangda
 * @create 2024-01-04-21:45
 * @description:
 */
// @EnableDiscoveryClient 引入的是Nacos发现注解
@EnableDiscoveryClient
@SpringBootApplication
public class MemberNacosProviderApplication10004 {
    public static void main(String[] args) {
        SpringApplication.run(MemberNacosProviderApplication10004.class,args);
        System.out.println("MemberNacosProviderApplication10004 starting...");
    }
}
