package com.hspedu.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangda
 * @description:
 * @create 2023-09-11-16:36
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    //通过value可以给注入的bean/对象指定名字
    String value() default "";
}