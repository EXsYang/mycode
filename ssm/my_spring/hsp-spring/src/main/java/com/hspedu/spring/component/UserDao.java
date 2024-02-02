package com.hspedu.spring.component;

import org.springframework.stereotype.Component;

/**
 * @author yangda
 * @description:
 * @create 2023-09-10-21:25
 */
@Component //可以使用@Repository
public class UserDao {

    public void hi(){
        System.out.println("UserDao hi()被调用...");
    }
}
