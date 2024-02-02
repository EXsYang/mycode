package com.hspedu.springboot.config;

import com.hspedu.springboot.servlet.Filter_;
import com.hspedu.springboot.servlet.Listener_;
import com.hspedu.springboot.servlet.Servlet_;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author yangda
 * @create 2023-12-12-22:17
 * @description:
 * RegisterConfig_:是一个配置类-通过@Bean注解来注入相关的bean
 * proxyBeanMethods = true: 默认是单实例的[保证每个@Bean 方法被调用多少次返回的组件都是
 * 单实例的, 是代理方式]
 *
 *
 */
@Configuration(proxyBeanMethods = true)
public class RegisterConfig_ {

    //使用RegistrationBean方式注入
    //注入Servlet @Bean注解如果没有指定bean名称，默认使用方法名servlet_作为key
    @Bean
    public ServletRegistrationBean servlet_(){
        //创建原生的Servlet对象
        Servlet_ servlet_ = new Servlet_();
        //把 servlet_对象关联到ServletRegistrationBean对象
        //"/servlet01","/servlet02" 就是注入的Servlet的url-pattern
        return new ServletRegistrationBean(servlet_,"/servlet01","/servlet02");

    }


    //注入Filter
    @Bean
    public FilterRegistrationBean filter_(){
        //创建原生的Filter,过滤器的业务逻辑代码还是在自定义的Filter_中写
        Filter_ filter_ = new Filter_();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter_);
        //设置filter的url-pattern
        //Arrays.asList("/css/*","/images/*") 将字符串转为集合放入到方法形参
        // 这里会拦截匹配上url映射
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/css/*","/images/*"));

        //测试，如果过滤器也对"/servlet01" 进行拦截，还会不会进入到servlet01对应的Servlet呢?
        //过滤器被触发 --Filter_ doFilter-- 过滤器处理的uri=/servlet01
        //但是因为是放行，因此可以访问到"/servlet01"对应的Servlet_
        //即过滤器也会拦截到地址栏中对servlet映射地址的请求
        // filterRegistrationBean.setUrlPatterns(Arrays.asList("/css/*","/images/*","/servlet01"));
        return filterRegistrationBean;

    }

    //注入Listener
    @Bean
    public ServletListenerRegistrationBean listener_(){

        //创建原生的Listener对象
        Listener_ listener_ = new Listener_();

        return new ServletListenerRegistrationBean(listener_);



    }


}
