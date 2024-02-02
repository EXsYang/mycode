package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangda
 * @create 2024-01-01-19:08
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
//启用OpenFeignClient
@EnableFeignClients
public class MemberConsumerOpenfeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberConsumerOpenfeignApplication.class,args);
        System.out.println("MemberConsumerOpenfeignApplication-80 starting...");
    }
}
