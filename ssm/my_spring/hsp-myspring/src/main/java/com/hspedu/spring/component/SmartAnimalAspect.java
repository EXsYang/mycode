package com.hspedu.spring.component;

import com.hspedu.spring.annotation.AfterReturning;
import com.hspedu.spring.annotation.Aspect;
import com.hspedu.spring.annotation.Before;
import com.hspedu.spring.annotation.Component;

/**
 * @author yangda
 * @description:
 * 老师说明：SmartAnimalAspect当作一个切面类来使用
 * ，后面老韩再分析如何做的更加灵活
 * @create 2023-09-16-12:43
 */
@Aspect //我们的注解
@Component  //这个是实现了
public class SmartAnimalAspect {

    @Before(value = "execution com.hspedu.spring.component.SmartDog getSum")
    public static void showBeginLog(){
        System.out.println("前置通知...");
    }
    @AfterReturning(value = "execution com.hspedu.spring.component.SmartDog getSum")
    public static void showSuccessLog(){
        System.out.println("返回通知...");
    }


}
