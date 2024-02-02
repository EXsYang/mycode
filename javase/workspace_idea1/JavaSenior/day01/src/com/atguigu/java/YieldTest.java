package com.atguigu.java;

/**
 * @author yangda
 * @create 2021-11-24-19:56
 */
public class YieldTest {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 400; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName()+":"+i);
                    }
                    if (i % 20 == 0) {
                        yield();
                    }

                }
            }
        }.start();
        for (int i = 0; i < 400; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}