package com.hspedu.springboot.config;

import com.hspedu.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangda
 * @create 2023-11-25-23:08
 * @description: 当配置类标识了 @Configuration  @Bean 注解后
 * BeanConfig2中配置的bean monster02 默认就会注入到 springboot 的ioc容器中[单例池]
 */
@Configuration
public class BeanConfig2 {


    // @Scope("prototype") //多实例 每次返回都是新的对象
    //monster02 不要和BeanConfig.java中配置的monster01重复 id唯一 否则运行报错
    @Bean // 这个注解不能少，少了就不会把monster02()当作一个bean注入到容器中，没写@Bean注解
    // monster02()只是一个普通的方法
    public Monster monster02() {
        return new Monster(300, "蚂蚁精", 30, "吃小昆虫@");
    }
}
