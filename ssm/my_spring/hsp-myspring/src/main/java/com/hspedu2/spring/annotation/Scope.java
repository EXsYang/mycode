package com.hspedu2.spring.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangda
 * @description: 可以指定Bean的作用范围[singleton,prototype]
 * @create 2023-09-16-19:47
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    //通过value可以指定singleton/prototype
    String value() default "";
}

