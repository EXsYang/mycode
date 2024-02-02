package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-25-8:07
 */
public class ImplementsExtendsTest {
    public static void main(String[] args) {
        MyExtends t = new MyExtends();
        t.start();
    }
}
class MyExtends extends Thread implements Runnable{
    @Override
    public void run() {
        System.out.println("调用子类");//调用的都是子类重写的方法，API只读，没有具体的方法体

    }
}
class MyImplements implements Runnable{
    @Override
    public void run() {
        System.out.println("调用接口子类");

    }
}