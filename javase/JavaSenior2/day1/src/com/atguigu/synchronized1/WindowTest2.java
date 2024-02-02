
package com.atguigu.synchronized1;
/**
 * @author yangda
 * @description:
 * @create 2022-11-07-16:13
 */

public class WindowTest2 {
    public static void main(String[] args) {
        //三个窗口卖票问题，100张票
        Window2 w2 = new Window2();

        Thread t1 = new Thread(w2);
        Thread t2 = new Thread(w2);
        Thread t3 = new Thread(w2);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();

    }


}

class Window2 implements Runnable{
    private int ticket = 100;
    Dog dog = new Dog();
    @Override
    public void run() {
        while (true){
            synchronized (this){//实现子类的对象，只有一个  方式一：synchronized(this) 方式二；synchronized(dog)
                if (ticket > 0){
                    System.out.println(Thread.currentThread().getName() + "卖票，票号:" +ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
//            System.out.println("run");//不加break 一直跑
    }
}

