package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangda
 * @create 2024-01-10-0:57
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@EnableDiscoveryClient
public class SeataAccountMicroServiceApplication10012 {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountMicroServiceApplication10012.class,args);
        System.out.println("SeataAccountMicroServiceApplication10012 starting...");
    }
}
