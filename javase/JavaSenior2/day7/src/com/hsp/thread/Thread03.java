package com.hsp.thread;

import java.sql.SQLOutput;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-19:19
 */
public class Thread03 {
    public static void main(String[] args) {

        T3 t3 = new T3();
        T4 t4 = new T4();
        Thread thread = new Thread(t3);
        Thread thread2 = new Thread(t4);

        thread.start();
        thread2.start();

        System.out.println("主线程结束");
    }
}
class T3 implements Runnable{

    @Override
    public void run() {


        for (int i = 1; i <= 50; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello world" + i);

        }


    }
}
class T4 implements Runnable{

    @Override
    public void run() {


        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hi~~" + i);

        }


    }
}