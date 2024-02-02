
package com.atguigu.synchronized1;
/**
 * @author yangda
 * @description:
 * @create 2022-11-07-16:13
 */

public class WindowTest4 {
    public static void main(String[] args) {
        //三个窗口卖票问题，100张票
        Window4 w4 = new Window4();

        Thread t1 = new Thread(w4);
        Thread t2 = new Thread(w4);
        Thread t3 = new Thread(w4);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();

    }


}

class Window4 implements Runnable{
    private static int ticket = 100;
    private static Dog dog = new Dog();//可以用private static修饰，类似于枚举类
    @Override
    public void run() {
        while (true){
               show();
            if (ticket == 0){
                break;
            }
            }
//            System.out.println("run");//不加break 一直跑
        }
    private synchronized void show(){//this
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName() + "卖票，票号:" +ticket);
                ticket--;
            }
    }
}

