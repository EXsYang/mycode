package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020 上午 11:43
 */
public class HelloApp2 {

    //只有下面这行代码时会生成clinit函数
    // private static int a = 1;//prepare：a = 0 ---> initial : a = 1

    private static final int a = 1;//只有这行代码时不会生成clinit函数


    // private static int b = 2;//只有这行代码时会生成clinit函数


    // static{
    //
    // }

    public static void main(String[] args) {
        // System.out.println(a);
        System.out.println("aaaa");
    }
}
