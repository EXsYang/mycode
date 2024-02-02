package com.hspedu.hspspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-05-21:35
 * @description: ResponseBody 标识该注解的方法 返回json格式的数据
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
    //扩展可以指定要返回的类型 json/xml/text/html...
    //String value() default "";
}
