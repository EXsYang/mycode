package com.hspedu1.spring.processor;

/**
 * @author yangda
 * @description:
 * 自定义的后置处理器接口 只要容器中有实现了该接口的类 就是配置了后置处理器
 * 后置处理器中的代码作用于容器中所有的bean对象
 *
 * @create 2023-09-16-22:46
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }

}
