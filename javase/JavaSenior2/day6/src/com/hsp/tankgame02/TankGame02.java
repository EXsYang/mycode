package com.hsp.tankgame02;

import javax.swing.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-17:23
 */
public class TankGame02 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        new TankGame02();


    }

    public TankGame02() {
        mp = new MyPanel();
        this.add(mp);
        setSize(1000,750);
        this.addKeyListener(mp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}
