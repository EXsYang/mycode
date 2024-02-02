package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-24-21:32
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread2 t = new Thread2();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Thread2 implements Runnable {//存在线程安全问题
    private int ticket = 100;


    public void run() {
        for (; ; ) {
            if (ticket > 0) {
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
