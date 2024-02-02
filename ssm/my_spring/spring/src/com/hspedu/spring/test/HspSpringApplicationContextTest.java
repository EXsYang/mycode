package com.hspedu.spring.test;

import com.hspedu.spring.annotation.HspSpringApplicationContext;
import com.hspedu.spring.annotation.HspSpringConfig;
import com.hspedu.spring.component.MyComponent;
import com.hspedu.spring.component.UserAction;
import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.component.UserService;

/**
 * @author yangda
 * @description:
 * @create 2023-09-03-19:38
 */
public class HspSpringApplicationContextTest {
    public static void main(String[] args) {



        HspSpringApplicationContext ioc =
                new HspSpringApplicationContext(HspSpringConfig.class);

        UserAction userAction = (UserAction) ioc.getBean("userAction");
        System.out.println("userAction= " + userAction);

        //MyComponent myComponent = (MyComponent) ioc.getBean("myComponent");
        MyComponent myComponent = (MyComponent) ioc.getBean("hsp1");
        System.out.println("myComponent= " + myComponent);

        UserService userService = (UserService) ioc.getBean("userService");
        System.out.println("userService= " + userService);

        UserDao userDao = (UserDao) ioc.getBean("userDao");
        System.out.println("userDao= " + userDao);
        System.out.println("ok");
    }
}
