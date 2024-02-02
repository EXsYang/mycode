package com.hsp.exer;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-21:47
 */
public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {

        V v = new V();
        Thread thread = new Thread(v);



        for (int i = 0; i < 10; i++) {

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "hi~" + (i + 1));

            if (i == 4){

                thread.start();
                thread.join();
            }

        }
    }
}
class V implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello" + i);

        }
    }
}