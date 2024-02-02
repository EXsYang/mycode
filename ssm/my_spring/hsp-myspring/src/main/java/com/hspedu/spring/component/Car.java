package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.processor.InitializingBean;

/**
 * @author yangda
 * @description:
 * @create 2023-09-12-15:54
 */

@Component
public class Car implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Car 的初始化方法被调用...");
    }
}
