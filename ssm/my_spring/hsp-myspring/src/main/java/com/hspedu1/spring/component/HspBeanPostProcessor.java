package com.hspedu1.spring.component;

import com.hspedu1.spring.annotation.Component;
import com.hspedu1.spring.processor.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangda
 * @description: 这是一个自定义的后置处理器
 * 因为该后置处理器要在容器中发挥作用 因此需要将该类也注入到容器中
 *
 * @create 2023-09-16-22:49
 */
@Component
public class HspBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("后置处理器 Before 方法被调用.. " + "当前的bean运行类型= "
                + bean.getClass() + " beanName= " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("后置处理器 After 方法被调用.. " + "当前的bean运行类型= "
                + bean.getClass() + " beanName= " + beanName);

        // 在这里实现AOP
        // 如果该bean 是SmartDog 就进行替换 替换成代理对象并返回
        // 同时走代理对象的getSum方法
        if("smartDog".equals(beanName)){
            // 如果方法名为getSum 进行切入

            Object proxyInstance = Proxy.newProxyInstance(HspBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = null;
                    if("getSum".equals(method.getName())){
                        // 这里切入前置通知
                        SmartAnimalAspect.showBeginLog();
                        // 反射调用方法
                        result = method.invoke(bean, args);
                        // 这里切入返回通知
                        SmartAnimalAspect.showSuccessLog();
                    }else{
                        // 反射调用方法
                        result = method.invoke(bean, args);
                    }

                    return result;
                }
            });
            // 直接返回代理对象
            return proxyInstance;
        }




        return bean;
    }
}
