package com.hsp.tankgame04;

import javax.swing.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-17:23
 */
public class TankGame04 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        new TankGame04();


    }

    public TankGame04() {
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        setSize(1000,750);
        this.addKeyListener(mp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}
