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
