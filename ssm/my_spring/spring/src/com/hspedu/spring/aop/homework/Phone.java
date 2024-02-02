package com.hspedu.spring.aop.homework;

import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description:
 * @create 2023-09-08-22:51
 */

@Component //把Phone对象当做一个组件注入容器
public class Phone implements UsbInterface{
    @Override
    public void work() {
        System.out.println(" Phone类 work() 方法执行");
    }
}
