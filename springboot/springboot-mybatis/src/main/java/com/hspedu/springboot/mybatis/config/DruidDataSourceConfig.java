package com.hspedu.springboot.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author yangda
 * @create 2023-12-16-20:45
 * @description: DruidDataSourceConfig: 是一个配置类 该配置类的作用：切换数据源为 druid
 * 最好先把这个类创建好，再把需要的代码拷贝过来，因为有时文件的编码不同/引入的包不同，
 * 会出问题，这样也知道是为什么写这个类，只把需要的代码拿过来，这样是最安全的
 * 如果复制多了，不需要的代码，有可能会出现新的问题
 */
@Configuration
public class DruidDataSourceConfig {

    //老韩还有说明一下为什么我们注入自己的DataSource , 默认的HiKariDatasource失效?
    //1. 默认的数据源是如配置? 在数据源自动配置类 DataSourceAutoConfiguration.java中进行的自动配置
    //   这个类中频繁的使用到注解 @ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
    //   解读通过@ConditionalOnMissingBean({ DataSource.class}) 判断如果容器有DataSource Bean 就不注入默认的HiKariDatasource
    //2. debug源码.
    //3. 测试/证明 可以将本类全部注销，此时debug,查看主程序Application.java 的main方法中的ioc容器中
    //   是否自动注入了"dataSource"=HikariDataSource , 再将本类打开后debug,注入的为"dataSource"=DruidDataSource
    //   因此可以证明如果配置了其他的数据源(ioc中会注入DruidDataSource数据源)
    //   ， HikariDataSource数据源就不注入了，ioc中会注入DruidDataSource数据源


    //编写方法，注入DruidDataSource
    //注意返回的是 package javax.sql; 包下的DataSource,不要引错了
    /**
     * 如果使用 DruidDataSource 必须标注
     *
     * @ConfigurationProperties("spring.datasource") 否则不好使
     * 加了该注解后就会到application.yml文件中读取，配置好的参数信息
     * 读取到后就会设置到DruidDataSource的父类DruidAbstractDataSource.java 中的相关的属性上
     * 通过setUrl(),setUsername()...等方法，这是Druid就可以正常连接到数据库了
     */
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        /**
         * DruidDataSource 实现了javax.sql.DataSource接口
         * 所以可以按照接口的形式返回一个对象
         *
         * 1.配置了@ConfigurationProperties("spring.datasource")
         *   就可以读取到application.yml的配置
         * 2.就不需要调用DruidDataSource对象的setXxx()方法，一个一个的进行设置了
         *   当然也可以进行设置但没必要
         *   ，因为配置了@ConfigurationProperties("spring.datasource")
         *   后在底层会自动的进行关联
         */
        DruidDataSource druidDataSource = new DruidDataSource();
        // druidDataSource.setUrl();
        // druidDataSource.setUsername();
        // druidDataSource.setPassword();

        // stat:统计
        // wall:墙
        // 加入sql监控功能,
        // wall 加入sql防火墙监控,如果什么都没有配置,所有的sql语句都允许执行 可以配置黑名单白名单
        // druidDataSource.setFilters("stat,wall");

        return druidDataSource;

    }

}
