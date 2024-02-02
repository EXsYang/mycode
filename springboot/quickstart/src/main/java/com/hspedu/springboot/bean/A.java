package com.hspedu.springboot.bean;

import org.springframework.stereotype.Repository;

/**
 * @author yangda
 * @create 2023-11-25-21:12
 * @description: 测试原来的spring注解依然有效
 * Spring 注入组件的注解
 * @Component、@Controller、 @Service、@Repository
 * 说明: 这些在 Spring 中的传统注解仍然有效，通过这些注解可以给容器注入组件
 */
@Repository
public class A {
}
