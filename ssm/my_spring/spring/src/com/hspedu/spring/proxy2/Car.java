package com.hspedu.spring.proxy2;

/**
 * @author yangda
 * @description:
 * @create 2023-09-06-18:24
 */
public class Car implements Vehicle {
    @Override
    public void run(){
        //System.out.println("交通工具开始运行了...");
        System.out.println("小汽车在公路上 running...");
        //System.out.println("交通工具停止运行了...");
    }

    @Override
    public String fly(int height) {
        return "小汽车的飞行高度是：" + height;
    }
}
