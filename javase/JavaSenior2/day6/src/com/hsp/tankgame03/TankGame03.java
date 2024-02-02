package com.hsp.tankgame03;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author yangda
 * @description: 自己写了三天的版本
 * @create 2022-11-20-17:23
 */
public class TankGame03 extends JFrame {

    //Bug:坦克一动子弹就会回到原点，并且子弹方向会随着坦克移动改变
    //思路：固定子弹发射时，坦克的方向、位置坐标，子弹按照这个位置进行发射
    //在发射的时候按照当时坦克的方向、位置坐标绘制子弹，并不断刷新

    //定义MyPaneld
    static MyPanel mp = null;
    Shoot s = null;
    static ArrayList<Thread> list = null;//存放子弹对象
    int countBullet;

    public static void main(String[] args) {
        new TankGame03();




    }

    public TankGame03() {
//        bu = new Bullet();
        mp = new MyPanel();
        System.out.println("构造器里的mp:" + mp.hashCode());
        Thread thread = new Thread(mp);
        thread.start();//启动一个线程，用于不停的重绘！！！
//        Shot s = new Shot();
//
//        list = new ArrayList<>(countBullet);
//        for (int i = 0; i < countBullet; i++) {
//            list.add(new Thread(s));//list一开始长度为0,添加操作后，自动扩容。这里装入的不是子弹 ，装的画板对象                                创建坦克对象时设置子弹数量
//        }
//        s = new Shoot(hero);
//        Thread t1 = new Thread(mp);
//        t1.start();

//        this.add(s);
//        this.add(bu);
        this.add(mp);
//        this.addKeyListener(s);
        setSize(1000,750);
        this.addKeyListener(mp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void setList(ArrayList<Thread> list) {
        this.list = list;
    }

    public ArrayList<Thread> getList() {
        return list;
    }
}
