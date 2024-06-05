package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020 下午 6:01
 */
public class ClassInitTest {
   private static int num = 1;

   static{
       num = 2;
       number = 20;
       System.out.println(num);
       //System.out.println(number);//报错：非法的前向引用。可以在前面这里的代码块中赋值，但是不可以去调用它
   }

   private static int number = 10;  //linking之prepare: number = 0 --> initial: 20 --> 10

    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);//2
        System.out.println(ClassInitTest.number);//10
    }
}
