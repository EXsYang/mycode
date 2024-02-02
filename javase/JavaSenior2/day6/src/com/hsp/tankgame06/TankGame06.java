package com.hsp.tankgame06;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-17:23
 */
public class TankGame06 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        new TankGame06();


    }

    public TankGame06() {
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);//把面板(就是游戏绘图区域)


        setSize(1300,950);
        this.addKeyListener(mp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
            }
        });


    }
}
