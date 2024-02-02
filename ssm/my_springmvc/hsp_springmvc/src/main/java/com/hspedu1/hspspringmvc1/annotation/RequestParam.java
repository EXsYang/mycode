package com.hspedu1.hspspringmvc1.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-08-15:39
 * @description: @RequestParam 获取到超链接传递的数据
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
