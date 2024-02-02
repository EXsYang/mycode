package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yangda
 * @create 2023-12-31-18:46
 * @description:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication9002.class,args);
        System.out.println("EurekaApplication9002 starting...");
    }
}
