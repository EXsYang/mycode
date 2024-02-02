package com.hsp.method;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-21:39
 */
public class Method2 {
    public static void main(String[] args) throws InterruptedException {

        A a = new A();
        a.setName("子线程，老大");
        a.start();

        Thread.currentThread().setName("main线程，小弟");

        for (int i = 1; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "吃包子     " + i);
            Thread.sleep(400);
            if (i == 5){
//                System.out.println("老大插队");
//                a.join();
                System.out.println("小弟礼让");
                Thread.yield();
            }
        }
    }
}
class A extends Thread{
    @Override
    public void run() {
        for (int i = 1; i < 20; i++) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName() + "吃包子     " + i);
        }


    }
}