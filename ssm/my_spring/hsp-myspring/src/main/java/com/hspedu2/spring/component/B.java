package com.hspedu2.spring.component;

import com.hspedu2.spring.annotation.Component;
import com.hspedu2.spring.processor.InitializingBean;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-19:42
 */
@Component
public class B implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("B 的初始化方法");
    }
}
