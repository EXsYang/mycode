package com.hsp.method;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-22:25
 */
public class ThreadMethod03 {
    public static void main(String[] args) throws InterruptedException {

        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //设置为守护线程
        myDaemonThread.setDaemon(true);


        //先设置为守护线程再启动，不然会抛异常
        myDaemonThread.start();

//        System.out.println(myDaemonThread.isDaemon());

        for (int i = 1; i < 11; i++) {
            System.out.println("宝强在工作、、、" + i);
            Thread.sleep(1000);//打印完后休息一秒和先休息在打印，显示的效果不同，这一行代码执行完，很快就退出了
        }
        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
    }
}
class MyDaemonThread extends Thread{
    @Override
    public void run() {
        for (;;){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("马蓉和宋喆聊天，哈哈哈哈哈");
        }


    }
}