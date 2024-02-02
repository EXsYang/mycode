package com.hspedu.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @create 2023-11-13-16:10
 * @description: 验证一下 没有被切入方法的普通的实现子类 是否返回的是代理对象
 */
@Component //使用@Component 当spring容器启动时，将 SmartDog注入到容器
public class SmartCat implements SmartAnimalable{
    @Override
    public float getSum(float i, float j) {
        return 0;
    }

    @Override
    public float getSub(float i, float j) {
        return 0;
    }
}
