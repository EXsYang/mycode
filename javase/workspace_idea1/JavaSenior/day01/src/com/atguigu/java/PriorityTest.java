package com.atguigu.java;

/**
 * @author yangda
 * @create 2021-11-24-20:49
 */
public class PriorityTest {
    public static void main(String[] args) {
        MyPriority pri = new MyPriority();
        pri.setPriority(Thread.MIN_PRIORITY);
        pri.start();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        for (int i = 0; i < 400; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
        System.out.println(pri.isAlive());
        System.out.println(Thread.currentThread().isAlive());
    }

}
class MyPriority extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
