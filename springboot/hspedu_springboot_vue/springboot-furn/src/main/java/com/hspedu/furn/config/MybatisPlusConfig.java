package com.hspedu.furn.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangda
 * @create 2023-12-24-18:39
 * @description:  配置类，引入 mybatis-plus 分页插件 PaginationInnerInterceptor
 * mybatis-plus 分页插件,引入参考 mybatis-plus 官网
 *
 * `QueryWrapper` 类是 MyBatis Plus 框架中的一个功能类，它并不是直接与分页插件 `PaginationInnerInterceptor` 关联的。`QueryWrapper` 主要用于构建数据库查询条件，它提供了一种链式的方式来构建查询语句，使得操作数据库时的代码更加简洁易读。
 *
 * `PaginationInnerInterceptor` 是 MyBatis Plus 中用于分页的拦截器。它的主要作用是拦截查询操作，自动解析分页参数，并对 SQL 语句进行修改，以实现分页功能。虽然 `QueryWrapper` 可以与 `PaginationInnerInterceptor` 一起使用来实现分页查询的需求，但它们属于 MyBatis Plus 中不同的功能模块。
 *
 * 简而言之，`QueryWrapper` 不是 `PaginationInnerInterceptor` 带的，而是作为构建查询条件的工具，它可以单独使用或与分页拦截器等其他组件一起使用以满足不同的业务需求。
 */
@Configuration
public class MybatisPlusConfig {


    /**
     * 1.注入MybatisPlusInterceptor 对象/bean
     * 2.在MybatisPlusInterceptor bean 加入分页插件 PaginationInnerInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // DbType.MYSQL: 这里根据你的实际情况来指定是什么 DbType
        // 这里分页需要指定数据库类型，因为不同的DB，分页SQL不同
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;
    }

}
