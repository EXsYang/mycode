package com.atguigu.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-11:24
 */
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();




        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();

    }
}
class Window implements Runnable{
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();
//    private ReentrantLock lock1 = new ReentrantLock(true);
    @Override
    public void run() {
        while (true){
            try {//把要操作的代码放在try{}当中
                //调用锁定的方法 lock()
                lock.lock();//在try里面，一上来先调用一下lock()方法

                if (ticket > 0){
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票：票号是：" + ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                //调用解锁的方法
                lock.unlock();
            }

        }
    }
}