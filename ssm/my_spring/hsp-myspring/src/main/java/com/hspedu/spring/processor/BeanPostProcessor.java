package com.hspedu.spring.processor;

/**
 * @author yangda
 * @description:
 * 老师解读
 * 1. 参考原生Spring容器定义一个接口BeanPostProcessor
 * 2. 该接口有两个方法postProcessBeforeInitialization 和 postProcessAfterInitialization
 * 3. 这两个方法，会对Spring容器的所有Bean生效,已经是切面编程的概念。
 * @create 2023-09-15-19:13
 */
public interface BeanPostProcessor {

    /**
     * 1.postProcessBeforeInitialization 在Bean初始化方法前调用
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName){

        return bean;
    }

    /**
     * 1.postProcessAfterInitialization 在Bean初始化方法后调用
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }

}
