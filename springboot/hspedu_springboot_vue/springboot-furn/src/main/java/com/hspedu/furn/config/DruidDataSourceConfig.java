package com.hspedu.furn.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yangda
 * @create 2023-12-24-20:11
 * @description:
 */
@Configuration
@Slf4j
public class DruidDataSourceConfig {

    //配置/注入DruidDataSource
    //为什么我们配置/注入指定的数据源,就替换了默认的数据源,
    //在讲解springboot-usersys项目中的 config/DruidDataSourceConfig.java中讲的
    //注意返回的是 package javax.sql; 包下的DataSource,不要引错了
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        log.info("数据源={}" , druidDataSource.getClass());
        return druidDataSource;
    }


}
