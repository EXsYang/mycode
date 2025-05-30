package com.hspedu.spring.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangda
 * @description:
 * @create 2023-09-11-16:35
 */
/** 组件扫描注解 可以在value指定要扫描的包
 * @author 韩顺平
 * @version 1.0
 * 老韩解读
 * 1. @Target(ElementType.TYPE)指定我们的ComponentScan注解可以修饰 Type程序元素
 * 2. @Retention(RetentionPolicy.RUNTIME) 指定ComponentScan注解 保留范围
 * 3. String value() default ""; 表示ComponentScan 可以传入 value
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    //通过value可以指定要扫描的包
    String value() default "";
}

