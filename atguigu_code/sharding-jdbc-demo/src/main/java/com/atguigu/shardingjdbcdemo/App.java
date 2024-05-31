package com.atguigu.shardingjdbcdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.atguigu.shardingjdbcdemo.maper")
public class App {

    public static void main(String[] args) {


        SpringApplication.run(App.class);


    }



}
