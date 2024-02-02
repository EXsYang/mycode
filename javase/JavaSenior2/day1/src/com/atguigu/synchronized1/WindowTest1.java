
package com.atguigu.synchronized1;
/**
 * @author yangda
 * @description:
 * @create 2022-11-07-16:13
 */

public class WindowTest1 {
    public static void main(String[] args) {
        //三个窗口卖票问题，100张票
        Window1 w1 = new Window1();

        Window1 w2 = new Window1();
        Window1 w3 = new Window1();

        w1.setName("窗口一：");
        w2.setName("窗口二：");
        w3.setName("窗口三：");

        w1.start();
        w2.start();
        w3.start();
    }


}

class Window1 extends Thread{
    private static int ticket = 100;
    private static Dog dog = new Dog();//可以用private static修饰，类似于枚举类
    @Override
    public void run() {
        while (true){
//            synchronized (Window.class){//可以
                synchronized (dog){
//            synchronized (this){ //错的

                if (ticket > 0){
                    System.out.println(getName() + "卖票，票号:" +ticket);
                    ticket--;
                }else{
                    break;
                }
            }
//            System.out.println("run");//不加break 一直跑
        }
    }
}

class Dog{

}