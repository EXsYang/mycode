package com.athuigu.java;

/**
 * @author yangda
 * @create 2021-11-25-23:28
 */
public class WindowTest4 {
    public static void main(String[] args) {

        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.setName("窗口一：");
        w2.setName("窗口二：");
        w3.setName("窗口三：");

        w1.start();
        w2.start();
        w3.start();

    }
}
class Window4 extends Thread {//存在线程安全问题
    private static int ticket = 100;//继承的方法这里需要加上static，三个Window2对象共用一份类变量
    static Object obj = new Object();//方式一,唯一的实现类Window1对象
    public void run() {
        for (; ; ) {
                //synchronized(Window2.class){//方式二,类Window1也是对象，类只加载一次
              show();
            }
        }

    private static synchronized void show(){//静态同步方法的同步监视器是类本身,Window4.class
    //private synchronized void show(){//同步监视器w1,w2,w3。这种是错误的
        if (ticket > 0) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"票号为：" + ticket);
            ticket--;
        }//这里不加break，for循环会继续判断，死循环
        //System.out.println( ticket);
    }
}
