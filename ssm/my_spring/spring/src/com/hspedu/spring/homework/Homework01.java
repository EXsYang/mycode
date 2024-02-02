package com.hspedu.spring.homework;

import com.hspedu.spring.bean.Monster;
import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-08-31-19:36
 */
public class Homework01 {

    public static void main(String[] args) {

        /*
            1. 在 beans.xml 中，我们注入 2 个 Monster 对象, 但是不指定 id,如下
            2. 问题 1：运行会不会报错
            答：不会报错，会正常运行
            3. 问题 2：如果不报错, 你能否找到分配的 id, 并获得到该对象.
            答：系统会默认分配 id ,分配 id 的规则是 全类名#0 , 全类名#1 这样的规则来分配 id
            , 我们可以通过 debug 方式来查看.
             3. 问题 2：如果不报错, 你能否找到分配的 id, 并获得到该对象
        */

        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

        Monster monster01 = ioc.getBean("com.hspedu.spring.bean.Monster#0", Monster.class);

        System.out.println("monster01=" + monster01);
        System.out.println("monster01.monsterId=" + monster01.getMonsterId());
        System.out.println("monster01.monsterName=" + monster01.getName());


        Monster monster02 = ioc.getBean("com.hspedu.spring.bean.Monster#1", Monster.class);

        System.out.println("monster02=" + monster02);
        System.out.println("monster02.monsterId=" + monster02.getMonsterId());
        System.out.println("monster02.monsterName=" + monster02.getName());
    }
}
