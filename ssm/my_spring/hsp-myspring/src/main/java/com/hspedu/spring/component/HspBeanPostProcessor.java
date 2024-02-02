package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.processor.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangda
 * @description:
 * 1. 这里是我们自己的一个后置处理器
 * 2. 实现了BeanPostProcessor
 * 3. 我们可以重写before和after方法
 * 4. 在Spring容器中，仍然把HspBeanPostProcessor当作一个Bean对象，
 *    为了让该后置处理器能够工作仍然要注入到容器 以为初始化等业务代码都是在容器中进行的
 * 5. 使用@Component注解标识 注意：只是注入进去没有用 没有底层代码支撑 只是一个普通的类！
 * 6. 我们要让HspBeanPostProcessor成为真正的后置处理器需要在容器里添加支撑的业务代码
 * 7. 还要考虑多个后置处理器对象注入到容器的问题 ，(即有多个类实现了BeanPostProcessor接口)
 * @create 2023-09-15-19:20
 */
@Component
public class HspBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        //这里请小伙伴一定要体会到，后置处理器是会对容器的创建的bean生效
        // 相当于是可以对多个对象编程，切面编程
        // 日志，权限，身份，事务....

        if (bean instanceof Car){
            System.out.println("这是一个Car对象 我可以处理");
        }

        System.out.println("后置处理器 HspBeanPostProcessor Before方法被调用 bean运行类型= " + bean.getClass()
        + " beanName= " + beanName);

        // 在容器中对后置处理器返回null 也进行处理 这里即使返回null
        // 后面的代码也会正常执行 使用的是原来的bean 而不是这里返回的null！！
        //return null;
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("后置处理器 HspBeanPostProcessor After方法被调用 bean运行类型= " + bean.getClass()
                + " beanName= " + beanName);

        // 实现AOP，返回代理对象，即对Bean进行包装
        // 1.先死后活-> 后面我们可以通过注解就可以更加灵活
        if("smartDog".equals(beanName)){
            //使用Jdk的动态代理，返回Bean的代理对象
            //如果没有印象的小伙伴，回去看老师讲过的动态代理
            Object proxyInstance = Proxy.newProxyInstance(HspBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("method= " + method.getName());
                    //假如我们进行前置通知+返回通知处理的方法是getSum
                    //后面可以通过注解做的更加灵活
                    Object result = null;
                    if ("getSum".equals(method.getName())) {
                        //进行前置通知的处理
                        SmartAnimalAspect.showBeginLog();
                        result = method.invoke(bean, args);//执行目标方法
                        //进行返回通知的处理
                        SmartAnimalAspect.showSuccessLog();
                    } else {
                        result = method.invoke(bean, args);
                    }

                    return result;
                }
            });
            //如果bean是需要返回代理对象的 直接返回代理对象，后面的return不会走了
            return proxyInstance;
        }

        //如果不需要AOP，就返回原生对象
        return bean;
    }
}
