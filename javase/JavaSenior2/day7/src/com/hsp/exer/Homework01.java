package com.hsp.exer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author yangda
 * @description:
 * @create 2022-11-22-12:16
 */
public class Homework01 extends JFrame {
    static MyThread myThread = null;

    public Homework01() {
        myThread = new MyThread();
        this.addKeyListener(myThread);
        myThread.setName("子线程");
        myThread.setDaemon(true);
        myThread.start();

        System.out.println(myThread.getName() +myThread.isDaemon());//true  myThread是守护线程

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    static boolean loop = true;

    public static void main(String[] args) throws InterruptedException {

        new Homework01();
        Thread.currentThread().setName("主线程");
//        Thread thread = new Thread();

        System.out.println("   " + Thread.currentThread().getName() + Thread.currentThread().isDaemon());

        while (loop) {
            Thread.sleep(1000);
            System.out.print((int) (Math.random() * 100) + 1);
            System.out.println("   " + Thread.currentThread().getName() + Thread.currentThread().getState());
            System.out.println("myThread.key:" + myThread.key);
            if (myThread.key == false) {
                loop = false;
            }
        }
        //这里主线程没有结束
        System.out.println("****" + Thread.currentThread().getName() + Thread.currentThread().getState());//****主线程RUNNABLE
    }


}

class MyThread extends Thread implements Runnable, KeyListener {
    boolean key = true;
    int count = 0;

    @Override
    public void run() {

        for (; ; ) {//key 没用上 通知方式
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在跑" + ++count);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.out.println("键盘读取了“Q”");
            key = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}