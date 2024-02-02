package com.atguigu.exer;

import javafx.concurrent.Worker;

/**
 * @author yangda
 * @create 2021-11-24-21:01
 */
public class Window extends Thread{
    public static void main(String[] args) {
       Window window = new Window();
        window.show();
    }

    private static int ticket = 100;//有线程安全问题
    @Override
    public void run() {
        //关于for循环
        //1.第一个参数必须要定义，不然编译失败
        //
        //2.第二个参数可以不写，因为没用终止条件所以无限循环下去
        //
        //3.第三个也可以不写，也是无限循环，但是输出的永远是零
        for (;;) {
            if(ticket > 0){
                try {
                    sleep(10);//可以省略类名，因为这是子类
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(getName()+"票号为："+ticket);
                ticket--;
            }//这里不加break，for循环会继续判断，死循环
            //System.out.println( ticket);
            else {
                break;
            }
        }
//        for (int i = 0; i < 2 ;) {//i必须赋初值，迭代条件不写是死循环
//            if(ticket > 1){
//                System.out.println(ticket);
//                ticket--;
//            }//这里不加break，for循环会继续判断
//            System.out.println(i);//i == 0
//        }

    }
    public static void show(){
        System.out.println("调用静态方法show()1");
    }
}
