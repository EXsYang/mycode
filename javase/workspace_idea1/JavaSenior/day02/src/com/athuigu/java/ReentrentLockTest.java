package com.athuigu.java;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangda
 * @create 2021-11-26-11:10
 */
public class ReentrentLockTest {
    public static void main(String[] args) {

        Window w = new Window();
        Thread w1 = new Thread(w);
        w1.start();
    }
}

class Window implements Runnable {
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    public void run() {//不可以直接在这里加synchronized，进去一个线程在循环里执行到票卖完
        for (; ; ) {
            try {
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);//这里只可以通过类名.方法名调用sleep,因为Runnable实现类
                        //中没有继承Thread类中的sleep()
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "票号为：" + ticket);
                    ticket--;
                }//这里不加break，for循环会继续判断，死循环
                //System.out.println( ticket);
            } finally {
                lock.unlock();
            }
        }

    }
}