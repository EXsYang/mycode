package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yangda
 * @create 2023-12-30-21:22
 * @description:
 * 注解@EnableEurekaServer: 表示该程序作为EurekaServer
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication9001 {
    public static void main(String[] args) {

        // localhost:9001
        SpringApplication.run(EurekaApplication9001.class,args);

        System.out.println("EurekaApplication9001 starting...");
    }
}
