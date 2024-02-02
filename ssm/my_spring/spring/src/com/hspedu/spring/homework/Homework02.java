package com.hspedu.spring.homework;

import com.hspedu.spring.bean.Car;
import com.hspedu.spring.bean.Monster;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-08-31-19:36
 */
public class Homework02 {

    public static void main(String[] args) {

        /*
           ● 课堂练习 (10-15min): 创建一个 Car 类(id , name , price ), 具体要求如下:
            1. 创建 ioc 容器文件(配置文件)，并配置一个 Car 对象(bean) .
            2. 通过 java 程序到 ioc 容器获取该 bean 对象，输出
        */

        ApplicationContext ioc = new ClassPathXmlApplicationContext("car_beans.xml");

        Car car01 = ioc.getBean("car01", Car.class);

        System.out.println("car01=" + car01);
        System.out.println("car01.id="+car01.getId());
        System.out.println("car01.name="+car01.getName());
        System.out.println("car01.price="+car01.getPrice());



    }
}
