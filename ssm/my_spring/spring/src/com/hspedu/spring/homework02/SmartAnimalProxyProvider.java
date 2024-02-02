package com.hspedu.spring.homework02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangda
 * @description: 返回代理对象 ProxyProvider:代理提供者
 * @create 2023-09-07-16:06
 */
public class SmartAnimalProxyProvider {

    private SmartAnimal target_smartAnimal;

    public SmartAnimalProxyProvider(SmartAnimal target_smartAnimal) {
        this.target_smartAnimal = target_smartAnimal;
    }

    // 返回代理对象
    public SmartAnimal getProxy() {

        //1. 先到的类加载器/对象
        ClassLoader classLoader = target_smartAnimal.getClass().getClassLoader();

        //2. 得到要执行的目标对象的接口信息
        Class<?>[] interfaces = target_smartAnimal.getClass().getInterfaces();

        //3. 创建InvocationHandler
        InvocationHandler invocationHandler = new InvocationHandler() {
            //@Override
            //public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //
            //    System.out.println("日志-方法名-"+method.getName()+"-参数 " + args[0] +" " + args[1]);
            //    Object result = null;
            //        result = method.invoke(target_smartAnimal, args);
            //    System.out.println("日志-方法名-"+method.getName()+"-结果result= " + result);
            //    return result;
            //}
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //System.out.println("方法执行开始-日志-方法名-" + method.getName() + "-参数 [" + args[0] + "," + args[1] + "]");
                // 下面这个位置 是横切关注点 => 前置通知
                //System.out.println("方法执行开始-日志-方法名-" + method.getName() + "-参数 " + Arrays.asList(args));

                /*
                public static <T> List<T> asList(T... a) {
                    return new ArrayList<>(a);
                }
                ArrayList 重写了toString() 所以输出的是 [10,2]
                */
                //List<Object> objects = Arrays.asList(args);
                //System.out.println("objects= "+ objects);

                Object result = null;
                try {
                    System.out.println("方法执行开始-日志-方法名-" + method.getName()
                            + "-参数 " + Arrays.asList(args)); //这里从AOP看，就是一个横切关注点-前置通知

                    //使用反射调用方法
                    result = method.invoke(target_smartAnimal, args);

                    System.out.println("方法执行正常结束-日志-方法名-" + method.getName()
                            + "-结果result= " + result);//从AOP看, 也是一个横切关注点-返回通知
                } catch (Exception e) {
                    e.printStackTrace();
                    //如果反射执行方法时，出现异常,就会进入到catch{}
                    System.out.println("方法执行异常-日志-方法名-" + method.getName()
                            + "-异常类型=" + e.getClass().getName());//从AOP看, 也是一个横切关注点-异常通知
                } finally {//不管你是否出现异常,最终都会执行到finally{}
                    //从AOP的角度看, 也是一个横切关注点-最终通知
                    System.out.println("方法最终结束-日志-方法名-" + method.getName());
                }

                return result;
            }
        };
        //创建代理对象
        SmartAnimal proxy =
                (SmartAnimal) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        return proxy;
    }
}
