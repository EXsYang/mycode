package com.hsp.thread;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-17:27
 */
public class Thread01 {
    public static void main(String[] args) {

        Cat cat = new Cat();
//        cat.run();//普通方法

        cat.start();
//        public synchronized void start() {
//            start0();
//        private native void start0();     //本地方法，由JVM调用，真正实现多线程的效果

    }
}
class Cat extends Thread{
    @Override
    public void run() {
        int times = 0;
        for (; ; ) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("喵喵~~我是第" + ++times + "只小猫咪");

            if (times == 8){
                break;
            }

        }

    }
}