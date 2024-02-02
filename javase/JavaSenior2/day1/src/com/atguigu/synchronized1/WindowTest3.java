
package com.atguigu.synchronized1;
/**
 * @author yangda
 * @description:
 * @create 2022-11-07-16:13
 */

public class WindowTest3 {
    public static void main(String[] args) {
        //三个窗口卖票问题，100张票
        //使用同步方法的方式实现

        Window3 t1 = new Window3();
        Window3 t2 = new Window3();
        Window3 t3 = new Window3();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();



    }


}

class Window3 extends Thread{
    private static int ticket = 100;
    private static Dog dog = new Dog();//可以用private static修饰，类似于枚举类
    @Override
    public void run() {
        while (true){
            show();
//            System.out.println("run");//不加break 一直跑
            if (ticket == 0){
                break;
            }
        }
    }
//    private synchronized void show(){//this: t1,t2,t3
    private static synchronized void show(){//Window3.class
        if (ticket > 0){
            System.out.println(Thread.currentThread().getName() + "卖票，票号:" +ticket);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket--;
        }
    }
}

