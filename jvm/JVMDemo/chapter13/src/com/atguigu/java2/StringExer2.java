package com.atguigu.java2;

/**
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  20:26
 */
public class StringExer2 {
    public static void main(String[] args) {


        String s1 = new String("ab");//执行完以后，会在字符串常量池中会生成"ab"

//        String s1 = new String("a") + new String("b");// 执行完以后，不会在字符串常量池中生成"ab"
          // s1变量记录的地址为：new String("ab"),
          // 即通过StringBuilder的toString()方法创建的new String("ab")

        //------
        //关键就看在执行intern();方法之前常量池中有没有"ab"
        //在jdk7/8时，
        // 如果在执行intern();方法之前常量池中有"ab"
        // 则s2就是指向的常量池中的"ab"

        // 如果在执行intern();方法之前常量池中没有"ab",
        // 则会将`String s1 = new String("a") + new String("b");`在堆中的
        // new String("ab")的引用地址放入到常量池中
        // 则下面的s2就是指向的就是一个指向堆中的引用地址 new String("ab")。
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);
    }
}
