package com.hspedu.springboot.mybatisplus.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author yangda
 * @create 2023-12-17-21:03
 * @description: DruidDataSourceConfig: 是一个配置类
 * 该配置类的作用：切换数据源为 druid
 * * 最好先把这个类创建好，再把需要的代码拷贝过来，因为有时文件的编码不同/引入的包不同，
 * * 会出问题，这样也知道是为什么写这个类，只把需要的代码拿过来，这样是最安全的
 * * 如果复制多了，不需要的代码，有可能会出现新的问题
 */
@Slf4j
@Configuration
public class DruidDataSourceConfig {

    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {

        DruidDataSource druidDataSource =
                new DruidDataSource();
        log.info("数据源={}", druidDataSource.getClass());

        return druidDataSource;

    }

}
