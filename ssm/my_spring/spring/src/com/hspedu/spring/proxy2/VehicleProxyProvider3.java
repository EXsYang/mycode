package com.hspedu.spring.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangda
 * @description: 该类可以返回一个代理对象
 * @create 2023-09-07-12:22
 */
public class VehicleProxyProvider3 {

    // 定义一个属性 target_vehicle 真正被执行的对象
    private Vehicle target_vehicle;

    public VehicleProxyProvider3(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }

    // 定义一个方法 返回代理对象
    public Vehicle getProxy() {

        ClassLoader classLoader = target_vehicle.getClass().getClassLoader();

        Class<?>[] interfaces = target_vehicle.getClass().getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("启动...");
                Object result = method.invoke(target_vehicle, args);
                System.out.println("停止...");

                return result;
            }
        };


        Vehicle proxy = (Vehicle) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        return proxy;


    }
}
