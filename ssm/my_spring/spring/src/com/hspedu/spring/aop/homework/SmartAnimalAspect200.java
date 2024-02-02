package com.hspedu.spring.aop.homework;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description: 切面类
 * @create 2023-09-08-22:52
 */

//@Aspect
//@Component
public class SmartAnimalAspect200 {

    // 切入方法
    //下面这种方式使用通配符 也生效
    //@Before(value = "execution(public void com.hspedu.spring.aop.homework.*.work())")
    //public void showBeforeLog(JoinPoint joinPoint){
    //    Signature signature = joinPoint.getSignature();
    //
    //    System.out.println("SmartAnimalAspect-切面类showBeginLog()-方法执行前-日志-方法名-"+signature.getName());
    //}

//新的前置通知
    //@Before(value = "execution(public void com.hspedu.spring.aop.aspectj.Phone.work()) || execution(public void com.hspedu.spring.aop.aspectj.Camera.work())")
    //public void hi(JoinPoint joinPoint) {
    //    Signature signature = joinPoint.getSignature();
    //    System.out.println("切面类的hi()-执行的目标方法-" + signature.getName());
    //}
}
