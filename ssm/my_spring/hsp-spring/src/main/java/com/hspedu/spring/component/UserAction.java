package com.hspedu.spring.component;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description: 就是一个Controller
 * @create 2023-09-10-21:26
 */


//在默认情况下 我们配置@Component @Controller @Service @Repository 是单例
//@Scope(value = "prototype") 表示以多实例形式，返回UserAction bean
//老师思考:Spring容器底层如何实现
//@Scope(value = "prototype")
@Component //也可以使用@Controller
//public class UserAction implements BeanPostProcessor {
public class UserAction{



}
