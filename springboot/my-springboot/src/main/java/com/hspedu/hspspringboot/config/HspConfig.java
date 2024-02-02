package com.hspedu.hspspringboot.config;

import com.hspedu.hspspringboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangda
 * @create 2023-12-01-19:01
 * @description: HspConfig:配置类-作为Spring的配置文件
 * 该配置类是如何生效的 在Spring容器创建时，注册一下配置类才可以生效
 * 这里有一个问题，容器怎么知道要扫描哪些包? =>一会代码会体现
 * <p>
 * 在配置类可以指定要扫描包: @ComponentScan("com.hspedu.hspspringboot")
 */
@Configuration
@ComponentScan("com.hspedu.hspspringboot")
public class HspConfig {

    //注入Bean - monster 对象 到Spring容器
    @Bean
    public Monster monster_01() {
        return new Monster();
    }

}
