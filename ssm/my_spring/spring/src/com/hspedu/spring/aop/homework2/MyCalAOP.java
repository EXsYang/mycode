package com.hspedu.spring.aop.homework2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description: 切面类
 * @create 2023-09-09-22:18
 */
@Order(value = 3)
@Aspect  // 表示MyCalAOP 是一个切面类
@Component//MyCalAOP/对象 作为组件注入到spring容器
public class MyCalAOP {

     //在执行cal1 前打印开始执行的时间，再执行完后打印时间

    // 使用注解进行配置 与beans10.xml 是一套
    // 配置切入点
    @Pointcut(value = "execution(public int MyCal.*(..))")
    public void myPointCut(){

    }

    //@Before(value = "execution(public int *(int))")
    @Before(value = "myPointCut()")
    //前置通知
    //这里注意,如果目标类和切面类，在同一个包，可以省略包名
    //因为cal1和cal2方法,都要去输出开始执行时间,因此使用MyCal.*
    //@Before(value = "execution(public int MyCal.*(int))")
    public void showBeforeLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();

        System.out.println(signature.getName() + "方法开始执行计算时间 使用切入点myPointCut " + System.currentTimeMillis());
    }

    //@AfterReturning(value = "execution(public int *(int))")
    @AfterReturning(value = "myPointCut()")
    //返回通知
    //这里注意,如果目标类和切面类，在同一个包，可以省略包名
    //因为cal1和cal2方法,都要去输出开始执行时间,因此使用MyCal.*
    //@AfterReturning(value = "execution(public int MyCal.*(int))")
    public void showSuccessEndLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();

        System.out.println(signature.getName() + "方法结束执行计算时间 使用切入点myPointCut " + System.currentTimeMillis());
    }






}
