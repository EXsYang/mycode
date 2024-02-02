package com.atguigu.java;

/**
 * @author yangda
 * @create 2021-11-24-20:12
 */
public class JoinTest {
    public static void main(String[] args)  {

        JoinThread j = new JoinThread();
        j.start();



        for (int i = 0; i < 400; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            if (i / 21 == 1) {
                try {
                    j.join();//谁调的谁接着走，直到走完
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
class JoinThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}