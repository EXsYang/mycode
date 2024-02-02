package com.atguigu.java;

/**
 * @author yangda
 * @create 2021-11-24-20:34
 */
public class IsAliveTest {
    public static void main(String[] args) {

        IsAlive Isa = new IsAlive();
        Isa.start();

        for (int i = 0; i < 400; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            if (i / 21 == 1) {
                try {
                    Isa.join();//谁调的谁接着走，直到走完
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Isa.isAlive());
        System.out.println(Thread.currentThread().isAlive());
    }

}
class IsAlive extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 400; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            if (i % 2 == 0) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
