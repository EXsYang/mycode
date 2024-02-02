package com.hspedu.hspspringmvc.annotation;

import java.lang.annotation.*;

/**
 * @author yangda
 * @create 2023-10-04-17:33
 * @description: RequestParam标注在目标方法的参数上，表示对应http请求的参数
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
