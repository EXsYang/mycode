package com.hspedu.springboot.config;

import com.hspedu.springboot.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangda
 * @create 2023-11-29-17:00
 * @description: 注解 @Configuration 标识一个配置类，充当Spring配置文件/容器
 * 如果该配置类，如果在springboot扫描的包/子包下，会被注入到Spring容器
 * 在该类中，可以通过@Bean 来注入其他的组件
 */
@Configuration
public class Config {

    /**
     * 1. 通过@Bean 的方式, 将 new 出来的 Bean 对象, 放入到 Spring 容器
     * 2. 该 bean 在 Spring 容器的 name/id 默认就是 方法名
     * 3. 通过方法名, 可以得到 注入到spring容器中的dog对象
     */
    @Bean
    public Dog dog() {
        return new Dog();
    }
}
