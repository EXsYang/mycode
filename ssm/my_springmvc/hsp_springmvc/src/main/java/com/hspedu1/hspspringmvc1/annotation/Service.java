package com.hspedu1.hspspringmvc1.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-08-10:32
 * @description: 自定义注解 用于标识一个Service 将标识该注解的类注入到ioc容器
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
