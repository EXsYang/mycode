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
 *
 * 3. @MapperScan(basePackages = {"com.hspedu.furn.mapper"}) 这行代码告诉
 * Spring Boot，去 com.hspedu.furn.mapper 包路径下查找接口，并为所有找到
 * 的接口自动生成 MyBatis 的 Mapper 实现。这样，所有在该包下的接口都会被
 * 自动注册为 Mapper，无需手动添加 @Mapper 注解。
 *
 * 总结来说，@MapperScan 提供了一种更为集中和便捷的方式来注册 MyBatis Mapper，特别是当你有多个 Mapper 接口时，这种方式可以避免在每个接口上重复添加注解，从而使代码更为整洁。
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
