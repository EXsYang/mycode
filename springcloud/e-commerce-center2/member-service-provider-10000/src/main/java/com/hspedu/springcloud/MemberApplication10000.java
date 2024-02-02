package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

/**
 * @author yangda
 * @create 2023-12-28-18:34
 * @description:
 * 注解@EnableEurekaClient:将该程序标识为EurekaClient
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class MemberApplication10000 {
    public static void main(String[] args) {
        ApplicationContext ioc =
                SpringApplication.run(MemberApplication10000.class, args);

        System.out.println("MemberApplication10000 springboot starting...");
    }
}
