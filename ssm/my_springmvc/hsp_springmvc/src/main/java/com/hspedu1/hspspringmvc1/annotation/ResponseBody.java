package com.hspedu1.hspspringmvc1.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-08-19:30
 * @description: 有该注解的目标方法 就返回Json 格式的数据
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
    String value() default "";
}
