package com.athuigu.java;

/**
 * @author yangda
 * @create 2021-11-25-22:23
 */
public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();

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
class Window1 implements Runnable {//存在线程安全问题
    private int ticket = 100;
    Object obj = new Object();//方式一
    Dog dog = new Dog();//方式一
    public void run() {
        for (; ; ) {
           // synchronized(obj){//方式一
            // synchronized(dog){//方式一
            synchronized(this){//方式二,唯一的实现类Window1对象
           // synchronized(Window1.class){//方式三,类Window1也是对象，类只加载一次
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);//这里只可以通过类名.方法名调用sleep,因为Runnable实现类
                        //中没有继承Thread类中的sleep()
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
class Dog{

}