package com.hspedu1.hspspringmvc1.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-06-13:15
 * @description: 自己的自定义注解 用于标识一个类是Controller
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
