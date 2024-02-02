package com.hspedu.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author yangda
 * @description:
 * @create 2023-09-10-21:26
 */

@Component // 也可以使用@Service
public class UserService {

    //定义属性
    //老师思考：加入 @Autowired , Spring容器时如何实现依赖注入?
    //也可以使用@Resource
    @Autowired
    private UserDao userDao;

    public void m1(){
        userDao.hi();
    }

    //这里我们需要指定init() 是初始化方法 类似于基于xml配置初始化方法
    // 即: init-method="init"
    @PostConstruct
    public void init(){
        System.out.println("UserService-init()");
    }
}
