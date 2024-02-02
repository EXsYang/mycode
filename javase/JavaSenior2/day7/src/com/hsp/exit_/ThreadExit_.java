package com.hsp.exit_;

/**
 * @author yangda
 * @description: 终止线程，通知方式
 * @create 2022-11-21-20:31
 */
public class ThreadExit_ {
    public static void main(String[] args) throws InterruptedException {

        T t = new T();
        t.start();

        //如果希望main去控制t线程的终止，必须可以修改loop
        //让t 退出run方法，从而终止线程  通知方式

        //让主线程休眠十秒，再通知t线程退出
        System.out.println("主线程休眠10s");
        Thread.sleep(10000);
        t.setLoop(false);


    }
}
class T extends Thread{

    private boolean loop = true;
    @Override
    public void run() {
        int i = 0;
        while (loop){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i++);
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}