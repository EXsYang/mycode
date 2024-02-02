package com.hspedu.springboot.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangda
 * @create 2023-12-17-21:08
 * @description:
 * 1. 使用@MapperScan 可以指定要扫描的Mapper接口 ,即统一处理
 * 2. 属性 basePackages 可以指定多个包,这里指定的是 com.hspedu.springboot.mybatisplus.mapper
 */
@MapperScan(basePackages = {"com.hspedu.springboot.mybatisplus.mapper"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
         ApplicationContext ioc =
                 SpringApplication.run(Application.class, args);

        System.out.println("springboot starting...");
    }
}
