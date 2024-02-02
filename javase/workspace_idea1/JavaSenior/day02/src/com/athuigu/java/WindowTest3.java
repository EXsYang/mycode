package com.athuigu.java;

/**
 * @author yangda
 * @create 2021-11-25-23:03
 */
public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();

        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("窗口一：");
        w2.setName("窗口二：");
        w3.setName("窗口三：");

        w1.start();
        w2.start();
        w3.start();

    }
}

class Window3 implements Runnable {
    private int ticket = 100;
    Object obj = new Object();//方式一
    Dog dog = new Dog();//方式一

    public void run() {//不可以直接在这里加synchronized，进去一个线程在循环里执行到票卖完
        for (; ; ) {
            show();
        }
    }

    public synchronized void show() {//同步监视器this

        if (ticket > 0) {
            try {
                Thread.sleep(10);//这里只可以通过类名.方法名调用sleep,因为Runnable实现类
                //中没有继承Thread类中的sleep()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "票号为：" + ticket);
            ticket--;
        }//这里不加break，for循环会继续判断，死循环
        //System.out.println( ticket);

    }
}
