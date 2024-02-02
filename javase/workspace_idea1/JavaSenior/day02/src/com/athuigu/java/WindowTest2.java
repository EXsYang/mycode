package com.athuigu.java;

/**
 * @author yangda
 * @create 2021-11-25-22:49
 */
public class WindowTest2 {
    public static void main(String[] args) {

        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口一：");
        w2.setName("窗口二：");
        w3.setName("窗口三：");

        w1.start();
        w2.start();
        w3.start();

    }
}
class Window2 extends Thread {//存在线程安全问题
    private static int ticket = 100;//继承的方法这里需要加上static，三个Window2对象共用一份类变量
    static Object obj = new Object();//方式一,唯一的实现类Window1对象
    public void run() {
        for (; ; ) {
            synchronized(obj){//方式一,唯一的实现类Window1对象
            //synchronized(Window2.class){//方式二,类Window1也是对象，类只加载一次
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
                else {
                    break;
                }
            }
        }
    }
}