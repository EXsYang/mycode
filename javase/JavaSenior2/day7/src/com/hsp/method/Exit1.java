package com.hsp.method;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;

/**
 * @author yangda
 * @description:
 * @create 2022-11-22-14:29
 */
public class Exit1 extends JFrame {
    T1 t1 = null;

    T1 t2 = null;
    public Exit1(){
        t1 = new T1();
        t2 = new T1();

        this.addKeyListener(t1);

        t1.start();
        t2.start();

        this.setSize(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    static boolean loop = true;

    public static void main(String[] args) throws InterruptedException {
//        T1 t1 = new T1();
//
//        T1 t2 = new T1();
//        t1.setDaemon(true);//设置为守护线程
//        t1.start();

//        for (int i = 0; i < 50; i++) {
//            Thread.sleep(1000);
//            System.out.println("主线程在跑" + i);
////            if (i == 20){
////                t1.setKey(false);//通知方式
////                break;//不要忘了
////            }
//        }

        new Exit1();

    }
}
class T1 extends Thread implements KeyListener {
    int count;
    static boolean key = true;
    @Override
    public void run() {
        while (key){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName() + "线程在跑" + ++count);
        }
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.out.print(currentThread().getName());//AWT-EventQueue-0
            System.out.println("键盘读取了“Q”");
            key = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}