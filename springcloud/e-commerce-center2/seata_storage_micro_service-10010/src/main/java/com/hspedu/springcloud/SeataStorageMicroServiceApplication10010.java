package com.hspedu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangda
 * @create 2024-01-09-23:09
 * @description:
 */
//exclude = DataSourceAutoConfiguration.class => 需要取消数据源的自动配置
//而是使用seata代理数据源，DataSourceProxy
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient //启用服务发现
@EnableFeignClients
public class SeataStorageMicroServiceApplication10010 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMicroServiceApplication10010.class,args);
        System.out.println("SeataStorageMicroServiceApplication10010 starting...");
    }
}
