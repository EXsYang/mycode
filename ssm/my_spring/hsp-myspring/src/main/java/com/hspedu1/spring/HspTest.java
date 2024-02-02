package com.hspedu1.spring;

import com.hspedu1.spring.annotation.AfterReturning;
import com.hspedu1.spring.annotation.Aspect;
import com.hspedu1.spring.annotation.Before;
import com.hspedu1.spring.ioc.HspSpringApplicationContext;
import com.hspedu1.spring.ioc.HspSpringConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangda
 * @description:
 * @create 2023-09-17-14:32
 */
public class HspTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        // 拿到切面类SmartAnimalAspect中注解上的值
        HspSpringApplicationContext ioc = new HspSpringApplicationContext(HspSpringConfig.class);

        Object smartAnimalAspect = ioc.getBean("smartAnimalAspect");

        System.out.println("smartAnimalAspect= " + smartAnimalAspect);
        Class<?> aspectClass = smartAnimalAspect.getClass();

        if(aspectClass.isAnnotationPresent(Aspect.class)){
            // 说明该类被@Aspect注解修饰
            System.out.println("该类是切面类");
            // 拿到切面类中所有的方法
            Method[] declaredMethods = aspectClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                // 拿到该方法上的注解
                if(declaredMethod.isAnnotationPresent(Before.class)){
                    System.out.println("该方法上存在@Before注解");
                    // 通过注解拿到 注解上的value
                    // 拿到该注解
                    Before before = declaredMethod.getAnnotation(Before.class);
                    String value = before.value();
                    System.out.println("注解上的value= " + value);
                    // 拿到切入表达式的值 全类名 方法名
                    String[] execution = value.split(" ");
                    //for (String executionValue : execution) {
                    //    System.out.println("executionValue= " + executionValue);
                    //}
                    String methodName = execution[execution.length - 1];
                    System.out.println("methodName= "+ methodName);

                    // 要切入的方法的方法名
                    String name = declaredMethod.getName();
                    System.out.println("要切入的方法的方法名= " + name);
                    // 通过方法名获取该方法
                    // 下面这个可以找到 注意小细节 第二个参数在该方法无参的情况下
                    // 不可以带类型【(Class<?>) null】 不可以强转后传入 而是应该直接传一个null !!!!
                    //Method declaredMethod1 = aspectClass.getDeclaredMethod(name, (Class<?>) null);
                    Method declaredMethod1 = aspectClass.getDeclaredMethod(name,  null);
                    //Method declaredMethod1 = aspectClass.getDeclaredMethod("showBeginLog",  null);
                    //进行反射调用
                    //declaredMethod1.invoke(aspectClass.newInstance(),null);
                    declaredMethod1.invoke(null,null);

                }else if (declaredMethod.isAnnotationPresent(AfterReturning.class)){
                    System.out.println("该方法上存在@AfterReturning注解");
                    // 通过注解拿到 注解上的value
                    // 拿到该注解
                    AfterReturning afterReturning = declaredMethod.getAnnotation(AfterReturning.class);
                    String value = afterReturning.value();
                    System.out.println("注解上的value= " + value);
                    // 拿到切入表达式的值 全类名 方法名
                    String[] execution = value.split(" ");
                    for (String executionValue : execution) {
                        System.out.println("executionValue= " + executionValue);
                    }
                    String methodName = execution[execution.length - 1];
                    System.out.println("methodName= "+ methodName);
                    // 要切入的方法的方法名
                    String name = declaredMethod.getName();
                    System.out.println("要切入的方法的方法名= " + name);
                    // 通过方法名获取该方法
                    Method declaredMethod1 = aspectClass.getDeclaredMethod(name, null);
                    //进行反射调用
                    //declaredMethod1.invoke(aspectClass.newInstance(),null);
                    declaredMethod1.invoke(null,null);

                }

            }


        }


    }
}
