package com.hspedu.spring.aop.homework2.xml;

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
public class MyCalAOP {

     //在执行cal1 前打印开始执行的时间，再执行完后打印时间

    // 使用xml进行配置 与beans11.xml 是一套
    //前置通知
    public void showBeforeLog(JoinPoint JoinPoint){
        Signature signature = JoinPoint.getSignature();
        System.out.println(signature.getName() + "方法开始执行计算时间 使用xml " + System.currentTimeMillis());
    }
    //返回通知
    public void showSuccessEndLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName() + "方法结束执行计算时间 使用xml " + System.currentTimeMillis());
    }






}
