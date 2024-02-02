package com.atguigu.java;

/**
 * @author yangda
 * @create 2021-11-24-20:29
 */

public class SleepTest {
    public static void main(String[] args) {

        Sleep s = new Sleep();
        s.start();

        for (int i = 0; i < 40000; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
//            if (i / 21 == 1) {
//                try {
//                    s.join();//谁调的谁接着走，直到走完
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
class Sleep extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            if (i % 2 == 0) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}