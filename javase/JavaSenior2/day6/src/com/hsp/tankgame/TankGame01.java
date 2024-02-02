package com.hsp.tankgame;

import javax.swing.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-17:23
 */
public class TankGame01 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        new TankGame01();


    }

    public TankGame01() {
        mp = new MyPanel();
        this.add(mp);
        setSize(1000,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}
