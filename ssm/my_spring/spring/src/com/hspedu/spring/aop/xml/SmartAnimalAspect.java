package com.hspedu.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author yangda
 * @description:  切面类 , 类似于我们以前自己写的MyProxyProvider,但是功能强大很多
 * @create 2023-09-08-17:09
 */
public class SmartAnimalAspect {


    public void showBeginLog(JoinPoint joinPoint){
        //通过连接点对象joinPoint 可以获取方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showBeginLog()-XML配置-方法执行前-日志-方法名-" + signature.getName() + "-参数 "
                + Arrays.asList(joinPoint.getArgs())); //这里从AOP看，就是一个横切关注点-前置通知
    }

    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showSuccessEndLog()-XML配置-方法执行正常结束-日志-方法名-" + signature.getName() + " 返回的结果是=" + res);
    }

    public void showExceptionLog(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showExceptionLog()-XML配置-方法执行异常-日志-方法名-" + signature.getName() + " 异常信息=" + throwable);
    }

    public void showFinallyEndLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("SmartAnimalAspect-切面类showFinallyEndLog()-XML配置-方法最终执行完毕-日志-方法名-" + signature.getName());
    }


}
