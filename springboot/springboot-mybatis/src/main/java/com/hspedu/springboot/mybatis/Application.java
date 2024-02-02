package com.hspedu.springboot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangda
 * @create 2023-12-16-15:33
 * @description: 注意这里必须配置好数据源后才可以正常启动
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc =
                SpringApplication.run(Application.class, args);
        System.out.println("springboot starting...");
    }
}
