package com.hsp.exer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

/**
 * @author yangda
 * @description:
 * @create 2022-11-22-12:16
 */
public class Homework1 extends JFrame {


    public static void main(String[] args) throws InterruptedException {

        A a = new A();

        a.setName("A线程");
        a.start();

        System.out.println(a);
        System.out.println(a.getName());

        B b = new B(a);
        b.setName("B线程");
        b.start();


    }


}

class A extends Thread {
    boolean key = true;
    int count = 0;

    @Override
    public void run() {

        while (key) {//key 没用上 通知方式
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "输出随机数：" +((int)(Math.random() *100) + 1));
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public void setKey(Boolean key) {
        this.key = key;
    }


}

class B extends Thread {
    //B 输入“Q” 时，退出，并且，A线程退出
    private A a;
    private boolean loop = true;

    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("请输入Q");
            String next = scanner.next();
            char c = next.charAt(0);
            if (c == 'Q') {
                a.setKey(false);
//                loop = false; //第一种退出方式
                System.out.println("输入为Q");
                break;//第二种退出方式，更加简便！！！

            }
        }
        System.out.println(Thread.currentThread().getName()+"结束");


    }

}