package com.hspedu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangda
 * @create 2023-12-02-20:22
 * @description:
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

        System.out.println("springboot start...");
    }
}
