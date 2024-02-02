package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangda
 * @create 2024-01-10-1:59
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient //启用服务发现
@EnableFeignClients
public class SeataOrderMicroServiceApplication10008 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMicroServiceApplication10008.class,args);
        System.out.println("SeataOrderMicroServiceApplication10008 starting...");
    }
}
