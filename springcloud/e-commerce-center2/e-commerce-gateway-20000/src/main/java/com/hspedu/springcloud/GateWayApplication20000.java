package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yangda
 * @create 2024-01-02-21:57
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWayApplication20000 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication20000.class,args);
        System.out.println("GateWayApplication20000 starting...");
    }
}
