package com.atguigu.java1;

import org.junit.Test;

import java.util.Random;

/**
 * @author shkstart
 * @create 2020-09-14 16:49
 *
 * 3. 当使用类、接口的静态字段时(final修饰特殊考虑)，比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）
 *
 */
public class ActiveUse2 {
    @Test
    public void test1(){
       // System.out.println(User.num); //User类的初始化过程
       // System.out.println(User.num1); //
        System.out.println(User.num2); // User类的初始化过程
        // 看 主动使用中 是否会执行类或接口的<clinit>()方法，
        // 和调用的数据的初始化/赋值的阶段有关。即是看其静态属性显示赋值的位置是在哪。
        // 如果在<clinit>()方法中完成的显示赋值，则需要调用<clinit>()方法。
        // 否则，不需要类的初始化，即不会调用<clinit>()方法。
    }

    @Test
    public void test2(){
//        System.out.println(CompareA.NUM1);
        System.out.println(CompareA.NUM2);
    }
}

class User{
    static{
        System.out.println("User类的初始化过程");
    }

    public static int num = 1; // 是在<clinit>中显示赋值的
    public static final int num1 = 1; // 是在准备阶段显示赋值的
    public static final int num2 = new Random().nextInt(10); // 是在<clinit>中显示赋值的

}

interface CompareA{
    public static final Thread t = new Thread(){
        {
            System.out.println("CompareA的初始化");
        }
    };

    public static final int NUM1 = 1;
    public static final int NUM2 = new Random().nextInt(10);

}