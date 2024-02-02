package com.hspedu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangda
 * @create 2024-01-09-22:35
 * @description: 常规配置 mybatis 和 dao 关联
 */
@Configuration
@MapperScan({"com.hspedu.springcloud.dao"})
public class MyBatisConfig {
}
