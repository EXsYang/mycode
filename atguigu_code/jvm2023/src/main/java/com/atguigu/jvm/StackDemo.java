package com.atguigu.jvm;

/**
 * 栈溢出：java.lang.StackOverflowError
 */
public class StackDemo {

    public void f1(){
        f2();
    }

    public void f2(){
        f1();
    }

    public static void main(String[] args) {
        StackDemo demo = new StackDemo();
        demo.f1();

//        Exception in thread "main" java.lang.StackOverflowError
//        at com.atguigu.jvm.StackDemo.f2(StackDemo.java:10)
    }
}
//Exception in thread "main" java.lang.StackOverflowError
