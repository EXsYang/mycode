package com.hspedu.hspspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-09-30-22:46
 * @description: 注解用于标识一个控制器组件
 * 这里涉及到注解的知识，在java基础时讲过
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Inherited
public @interface Controller {
    String value() default "";
}
