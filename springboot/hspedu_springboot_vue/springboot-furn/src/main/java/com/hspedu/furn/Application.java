package com.hspedu.furn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangda
 * @create 2023-12-19-20:28
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.hspedu.furn.mapper"})
public class Application {
    public static void main(String[] args) {
        ApplicationContext ioc =
                SpringApplication.run(Application.class, args);

        System.out.println("springboot-furn starting...");
    }
}
