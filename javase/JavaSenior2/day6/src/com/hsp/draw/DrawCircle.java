package com.hsp.draw;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;

/**
 * @author yangda
 * @description: draw画图  circle 圆
 * @create 2022-11-19-20:58
 */
public class DrawCircle extends JFrame{//JFrame 对应一个窗口，可以理解成是一个画框

    //定义一个面板
    private MyPanel mp = null;

    public static void main(String[] args) {

        new DrawCircle();

    }

    public DrawCircle(){
        mp = new MyPanel();
        //把面板放入到窗口（画框）
        this.add(mp);
        //设置窗口大小
        this.setSize(600,600);

        //当点击窗口的小X 程序完全退出，没有这句话点完❌程序仍然不会退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);//可以显示
        System.out.println("退出程序");
    }
}

//1.先定义一个MyPanel,继承JPanel类，画图形，就在面板上画
class MyPanel extends JPanel{
   /*
    public class JPanel extends JComponent implements Accessible
    Component 类 提供了两个和绘图相关的最重要的方法
    1.paint(Graphics g)绘制组件的外观
    2.repaint()刷新组件的外观
    */

    /*
        说明：
       1. MyPanel 对象就是一个画板
       2. Graphics g 把 g 理解成一支画笔
       3. Graphics 提供了很多绘图的方法
        Graphics g
     */


    /*
    paint()在以下情况被调用：
    1.当组件第一次在屏幕上显示的时候，程序会自动地调用paint()方法来绘制组件
    2.窗口最小化，在最大化
    3.窗口的大小发生变化
    4.repaint方法被调用
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);//调用父类的方法完成初始化、不能省
        //画出一个圆形
        System.out.println("paint方法被调用~~");
//        g.drawOval(10,10,100,100);//单位像素

        g.setColor(Color.GREEN);
        //演示绘制不同的图形
        //画直线

//        g.drawLine(10,10,100,100);
        g.drawLine(145 ,205,145,230);

        //画矩形边框
//        g.drawRect(10,10,100,100);

        g.fillRect(100,200,20,100);
        g.fillRect(170,200,20,100);

        g.fillRect(120,225,50,50);

        //画椭圆边框
//        g.drawOval(10,10,100,200);
        g.setColor(Color.CYAN);
        g.fillOval(125,230,40,40);

        //填充矩形
//        g.setColor(Color.BLUE);
//        g.fillRect(10,10,100,100);

        //填充椭圆
//        g.fillOval(10,10,100,100);

        //画图片
        //1. 获取图片资源，/bg.jpeg 第一个斜杠表示在该项目的根目录 获取图片资源
//        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bg.jpeg"));
//        g.drawImage(image,10,10,175,221,this);

        //画字符串
//        g.setColor(Color.red);
//        g.setFont(new Font("楷体",Font.BOLD,50));
//        //此时的x,y 是字符串的左下角
//        g.drawString("日本你好",100,100);



        //设置画笔的字体


        //设置画笔的颜色








    }
}