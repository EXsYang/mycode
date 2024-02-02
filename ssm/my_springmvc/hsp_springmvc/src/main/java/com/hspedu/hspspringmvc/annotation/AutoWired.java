package com.hspedu.hspspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-03-22:24
 * @description: 该注解标识 完成自动装配
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoWired {
    String value() default "";
}
