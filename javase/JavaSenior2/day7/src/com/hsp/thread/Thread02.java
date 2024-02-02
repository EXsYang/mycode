package com.hsp.thread;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-19:01
 */
public class Thread02 {
    public static void main(String[] args) {
        T2 t2 = new T2();
        Thread thread = new Thread(t2);
        thread.start();

    }
}
class T2 implements Runnable{

    @Override
    public void run() {

        int times = 0;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("hi~" + (++times));
            if (times == 10){
                break;
            }
        }
        
        
        
    }
}