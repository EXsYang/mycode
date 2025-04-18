package com.hspedu.spring;

import com.hspedu.spring.aop.SmartAnimalable;
import com.hspedu.spring.component.UserAction;
import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.component.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-10-21:35
 */
public class AppMain {
    public static void main(String[] args) {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

        UserAction userAction = (UserAction)ioc.getBean("userAction");
        UserAction userAction2 = (UserAction)ioc.getBean("userAction");

        System.out.println("userAction= " + userAction);
        System.out.println("userAction2= " + userAction2);


        UserDao userDao = (UserDao)ioc.getBean("userDao");
        System.out.println("userDao= " + userDao);

        UserService userService = (UserService)ioc.getBean("userService");
        System.out.println("userService= " + userService);

        // 测试一下当前的依赖注入
        userService.m1();

        // 测试一下AOP
        SmartAnimalable smartDog = ioc.getBean(SmartAnimalable.class);

        smartDog.getSum(10,2);

    }
}
