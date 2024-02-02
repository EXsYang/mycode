package com.hspedu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author yangda
 * @create 2023-12-14-22:54
 * @description: DruidDataSourceConfig: 是一个配置类
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
     * @ConfigurationProperties("spring.datasource") 否则不好使
     * 加了该注解后就会到application.yml文件中读取，配置好的参数信息
     * 读取到后就会设置到DruidDataSource的父类DruidAbstractDataSource.java 中的相关的属性上
     * 通过setUrl(),setUsername()...等方法，这是Druid就可以正常连接到数据库了
     *
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
        druidDataSource.setFilters("stat,wall");

        return druidDataSource;

    }

    //配置druid的监控页功能 (配置 Druid 监控信息显示页面)
    /**
     * 当访问http://localhost:10000/druid/xxx 会被重定向到
     * http://localhost:10000/druid/login.html 这实际上是Druid监控页面的正常行为，它说明Druid的安全机制正在起作用。重定向到登录页面
     * 输入正确的用户名和密码(下面配置的)后,会被重定向到
     * ,Druid 监控信息显示页面 http://localhost:10000/druid/index.html
     *
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建StatViewServlet
        StatViewServlet statViewServlet = new StatViewServlet();

        //将statViewServlet关联到ServletRegistrationBean,并配置好url-mapping
        ServletRegistrationBean<StatViewServlet> statViewServletRegistrationBean =
                new ServletRegistrationBean<>(statViewServlet,"/druid/*");

        //设置init-param , 设置用户和密码

        statViewServletRegistrationBean.addInitParameter("loginUsername","hsp");
        statViewServletRegistrationBean.addInitParameter("loginPassword","666666");
        // 设置允许清空统计数据
        statViewServletRegistrationBean.addInitParameter("resetEnable","true");

        return statViewServletRegistrationBean;

    }

    // Session监控功能不用配置，默认就可以使用，注意
    // 查看监控页需要输入用户名和密码, 点击 Session 监控，可以看到相关信息
    // 注意登录到Druid Monitor也会产生一个Session 但不是用户登录的session
    // 而且Druid Monitor Session监控功能也不会监控这一个session
    // 可以使用两个不同的浏览器分别登录这两个系统进行测试 Druid Monitor系统 和 用户系统
    // (注意要登录用户系统,才能看到 Session 监控信息,注意监控的不是Druid Monitor的Session)


    /**
     * 配置WebStatFilter,用于采集web-jdbc关联的监控数据
     * 主要是监控 请求的 uri
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        // 创建 WebStatFilter
        WebStatFilter webStatFilter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(webStatFilter);

        //默认对所有url请求进行监控
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        //排除指定url
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;

    }
}
