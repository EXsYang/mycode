package com.hspedu1.spring.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-16:25
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default "";
}
