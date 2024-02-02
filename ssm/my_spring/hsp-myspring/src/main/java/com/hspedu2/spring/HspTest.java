package com.hspedu2.spring;

import com.hspedu2.spring.annotation.After;
import com.hspedu2.spring.annotation.AfterReturning;
import com.hspedu2.spring.annotation.Aspect;
import com.hspedu2.spring.annotation.Before;
import com.hspedu2.spring.component.SmartAnimalAspect;
import com.hspedu2.spring.ioc.HspSpringApplicationContext;
import com.hspedu2.spring.ioc.HspSpringConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangda
 * @description:
 * @create 2023-09-17-14:32
 */
public class HspTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        // 拿到SmartAnimalAspect上的注解信息
        Class<SmartAnimalAspect> smartAnimalAspectClass = SmartAnimalAspect.class;

        if(smartAnimalAspectClass.isAnnotationPresent(Aspect.class)){
            System.out.println("SmartAnimalAspect类是一个切面类");
            //拿到切面方法
            Method[] declaredMethods = smartAnimalAspectClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                //拿到其中的方法 看是否实现了注解@Before 或是 @After
                if(declaredMethod.isAnnotationPresent(Before.class)){
                    System.out.println("该方法"+declaredMethod.getName()+"是作为前置通知");
                    // 得到Before注解
                    Before before = declaredMethod.getDeclaredAnnotation(Before.class);
                    // 得到before上的value
                    String value = before.value();
                    System.out.println("before value= " + value);
                    // 通过方法名 可以得到该方法
                    Method declaredMethod1 = smartAnimalAspectClass.getDeclaredMethod(declaredMethod.getName());
                    // 反射调用该方法
                    declaredMethod1.invoke(smartAnimalAspectClass.newInstance(),null);

                }else if (declaredMethod.isAnnotationPresent(AfterReturning.class)){
                    System.out.println("该方法"+declaredMethod.getName()+"是作为返回通知");
                    // 得到Before注解
                    AfterReturning afterReturning = declaredMethod.getDeclaredAnnotation(AfterReturning.class);
                    // 得到before上的value
                    String value = afterReturning.value();
                    System.out.println("afterReturning value= " + value);

                    // 通过方法名 可以得到该方法
                    Method declaredMethod1 = smartAnimalAspectClass.getDeclaredMethod(declaredMethod.getName());
                    // 反射调用该方法
                    declaredMethod1.invoke(smartAnimalAspectClass.newInstance(),null);
                }

            }


        }




    }
}
