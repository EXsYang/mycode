package com.hspedu.spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 * 编写的一个后置处理器
 */

// 这样配置一个后置处理器 也可以,就是把后置处理器放入到ioc容器中就会生效了
// 注意：使用注解进行配置的 一定要扫描(context:component-scan)进容器中才会生效！！！
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在Bean的 init初始化方法前调用-> 这个知识点，在前面讲解后置处理器时讲过的
     * 注意：容器中的bean 即使没有配置init()初始化方法 这里的后置处理器中的这两个
     * 方法也会被调用！！
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessBeforeInitialization 被 调 用 " + beanName + " bean= " + bean.getClass());
        return bean;
    }

    /**
     * 在Bean的 init初始化方法后调用-> 这个知识点，在前面讲解后置处理器时讲过的
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization 被 调 用 " + beanName + " bean= " + bean.getClass());

        //return null; // 如果返回一个null 并没有把传过来形参的bean置空，在接收的时候还是走的
        // 以前没有变化的bean 即上面的bean

        return bean;
    }
}
