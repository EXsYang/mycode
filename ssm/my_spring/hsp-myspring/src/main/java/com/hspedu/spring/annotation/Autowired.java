package com.hspedu.spring.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @description:
 * @create 2023-09-14-16:16
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    // 如果要装配的对象 不存在就会报错
    //boolean required() default true;
}

