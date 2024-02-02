package com.hsp.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-10:05
 */
public class BallMove extends JFrame{
    MyPanel mp = null;
    public static void main(String[] args) {

        new BallMove();
    }
    public BallMove(){
        mp = new MyPanel();

        this.add(mp);
        //窗口JFrame 对象可以监听键盘事件，即可以监听到面板发生的键盘事件
        this.addKeyListener(mp);
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}


//面板可以画出小球
// KeyListener 是监听器，可以监听键盘事件
class MyPanel extends JPanel implements KeyListener {

    //创建小球对象，小球的坐标x,y 是属性
    //为了使小球移动，将小球的坐标设置为变量
    Ball ball = new Ball();

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        g.fillOval(ball.getX(),ball.getY(),40,40);//默认黑色




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {//Event 事件

//        System.out.println(e.getKeyChar() + "键被按下..."); //可以监听到，获取到键盘上的字符

//    根据用户按下的不同键，来处理小球的移动(上下左右键)
//    在Java中，会给每一个键分配一个值(int)
    if (e.getKeyCode() == KeyEvent.VK_DOWN){//KeyEvent.VK_DOWN 就是向下箭头对应的Code
        ball.plusY();
    }else if(e.getKeyCode() == KeyEvent.VK_UP){
        ball.minusY();
    }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
        ball.minusX();
    }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        ball.plusX();
    }

    //让面板重绘
    this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
class Ball{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void plusX(){
        x += 10;
    }
    public void minusX(){
        x -= 10;
    }
    public void plusY(){
        y += 10;
    }
    public void minusY(){
        y -= 10;
    }



}