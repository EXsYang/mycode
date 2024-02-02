package com.hspedu2.spring.component;

import com.hspedu2.spring.annotation.Component;
import com.hspedu2.spring.processor.BeanPostProcessor;

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

        // 针对SmartDog 的 getSum()方法 使用AOP
        // 如果对象名为smartDog 就返回代理对象
        if ("smartDog".equals(beanName)){

            Object proxyInstance = Proxy.newProxyInstance(HspBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = null;
                    if ("getSum".equals(method.getName())){
                        // 前置通知
                        SmartAnimalAspect.showBeginLog();
                        result = method.invoke(bean, args);
                        // 返回通知
                        SmartAnimalAspect.showSuccessLog();

                    }else {
                        // 调用的smartDog 的其他方法 直接放行 执行该方法
                        result = method.invoke(bean, args);
                    }
                    return result;
                }
            });

            return proxyInstance;
        }
        // 如果beanName不是smartDog就返回原生bean
        return bean;
    }
}
