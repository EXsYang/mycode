package com.hspedu.springcloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author yangda
 * @create 2024-01-09-22:48
 * @description: 这里很重要 ： 配置数据源的代理是 seata, 也就是使用 seata 对数据源进行代理
 * 大家看引入的包: io.seata.rm.datasource.DataSourceProxy
 *
 * 注意！： @Bean注解标注的方法是可以有参数的
 * 在Spring框架中，`@Bean`注解的方法可以有参数，这些参数表示该Bean的依赖。Spring容器会自动地为这些参数寻找合适的实例来注入。这是Spring的依赖注入（Dependency Injection, DI）功能的一个关键方面。具体来说：
 *
 * 1. **寻找合适的Bean进行注入**：
 *    - 当Spring容器创建一个由`@Bean`注解的方法定义的Bean时，它会检查这个方法的参数。
 *    - 对于每个参数，Spring容器会在其当前管理的Bean中寻找与参数类型相匹配的Bean。
 *    - 如果找到了合适的Bean，Spring容器就会将这个Bean注入作为参数。
 * 2. **处理多个相同类型的Bean**：
 *    - 如果有多个相同类型的Bean可用，Spring容器需要额外的信息来确定使用哪一个。这可以通过指定Bean的名称或使用其他限定符（如`@Qualifier`注解）来实现。
 * 3. **无法找到合适的Bean时的行为**：
 *    - 如果Spring容器无法找到匹配的Bean来注入，它通常会抛出异常，因为这通常表示配置错误或者所需的Bean尚未定义。
 * 4. **使用`@ConfigurationProperties`进行细粒度控制**：
 *    - 有时，您可能会看到与`@Bean`一起使用`@ConfigurationProperties`注解。这用于将外部配置（如属性文件中的属性）绑定到Bean的属性上，进一步控制Bean的配置。
 *
 * 总的来说，`@Bean`注解的依赖注入机制使得在Spring中管理和配置Bean更加灵活和强大。
 */
@Configuration
public class DataSourceProxyConfig {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

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
    //配置 druidDataSource
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource() {
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
        return new DruidDataSource();

        // 下面测试的是这里创建的DruidDataSource被下面的dataSourceProxy(DataSource dataSource)方法使用到了
        // DruidDataSource druidDataSource = new DruidDataSource();
        // System.out.println("druidDataSource.hashCode=" + druidDataSource.hashCode());
        // return druidDataSource;
    }

    //配置DataSourceProxy- 使用seata代理数据源
    //注意引入的是io.seata.rm.datasource.DataSourceProxy
    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        // System.out.println("dataSourceProxy() 中的 dataSource.hashcode=" + dataSource.hashCode());

        return new DataSourceProxy(dataSource);

        // 下面测试的是这里创建的DataSourceProxy被下面的sqlSessionFactoryBean(DataSourceProxy dataSourceProxy)方法使用到了
        // DataSourceProxy dataSourceProxy = new DataSourceProxy(dataSource);
        // System.out.println("dataSourceProxy.hashcode=" + dataSourceProxy.hashCode());
        // return dataSourceProxy;
    }

    //配置SqlSessionFactory-常规写法
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy)
            throws Exception {

        // System.out.println("sqlSessionFactoryBean()方法中的dataSourceProxy.hashcode=" + dataSourceProxy.hashCode());

        SqlSessionFactoryBean sqlSessionFactoryBean =
                new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations
                (new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setTransactionFactory
                (new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
}
