package com.hspedu.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yangda
 * @create 2023-11-27-13:49
 * @description: 测试 @ImportResource 原生配置文件引入, 也就是可以直接导入 Spring 传统的 beans.xml
 * ，可以认为是 SpringBoot 对 Spring 容器文件的兼容.
 * 属性可以不写 可以写value也可以写locations 可以传入一个字符串或字符串数组
 */
@Configuration
// @ImportResource("classpath:beans.xml")
// @ImportResource(value = "classpath:beans.xml")
// @ImportResource(locations = "classpath:beans.xml")
@ImportResource(locations = {"classpath:beans.xml", "classpath:beans02.xml"})
public class BeanConfig3 {
}
