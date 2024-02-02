package com.hspedu1.hspspringmvc1.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-07-20:09
 * @description: RequestMapping 标识目标方法 注解用于指定控制器-方法的映射路径
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
