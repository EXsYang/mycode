package com.atguigu.java;

/**
 * @author yangda
 * @create 2021-11-24-17:49
 */


public class MThreadTest {
    public static void main(String[] args) {
//        Mthread1 m1 = new Mthread1();
//        Mthread2 m2 = new Mthread2();

        //  m1.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();
        // m2.start();
//        for (int i = 0; i < 100; i++) {
//            if(i % 2 != 0){
//                System.out.println(Thread.currentThread().getName()+":"+i);
//            }
    }
}

class Mthread1 extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

            //System.out.println(i);
        }
    }
}

class Mthread2 extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

            //System.out.println(i);
        }
    }
}