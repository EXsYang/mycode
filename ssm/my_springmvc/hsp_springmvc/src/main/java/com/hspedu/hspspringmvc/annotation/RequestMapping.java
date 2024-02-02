package com.hspedu.hspspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-09-30-22:50
 * @description: 注解用于指定控制器-方法的映射路径
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//@Inherited
public @interface RequestMapping {
    String value() default "";
}
