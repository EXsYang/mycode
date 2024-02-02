package com.hsp.tankgame00;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author yangda
 * @description: 坦克大战绘图区域
 * @create 2022-11-20-11:35
 */
public class MyPanel extends JPanel implements Runnable,KeyListener {

    //定义我的坦克
    Hero hero = null;
    private int enemysize = 3;
    Vector<EnemyTank> enemyTanks;

    public MyPanel() {//继承后需要自己写自己的
        hero = new Hero(100, 100);//初始化自己的坦克

        enemyTanks = new Vector<>();

        for (int i = 0; i < enemysize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 + i * 100, 0);
            //设置方向
            enemyTank.setDirect(2);
            //给该enemyTank 加入一颗子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //加入到enemyTank的Vector 成员
            enemyTank.shots.add(shot);
            //启动
            new Thread(shot).start();


            enemyTanks.add(enemyTank);
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认是黑色
//        drawTank(hero.getX(), hero.getY(), g, 0, 0);
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);

            if (hero.shot !=null &&hero.shot.isAlive != false  ){//先判断是否为空，不然，如果为null,空指针异常
                g.fill3DRect(hero.shot.getX(),hero.shot.getY(),3,3,false);
            }

        for (int i = 0; i < enemysize; i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
            //画出enemyTank 所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //绘制子弹
                if (shot.isAlive == true){
                    g.fill3DRect(shot.getX(),shot.getY(),3,3,false);
                }else{
                    //从Vector移除
                    enemyTank.shots.remove(shot);

                }


            }


        }

    }
    /**
     * @description:
     * @author: yangda
     * @date: 2022/11/21 8:30
     * @param: x 坦克的左上角x坐标
     * @param: y 坦克的左上角y坐标
     * @param: g 画笔
     * @param: direct 坦克方向（上下左右）
     * @param: type 坦克类型
     * @return: void
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        switch (type) {
            case 0://自己的坦克
                g.setColor(Color.ORANGE);
                break;
            case 1://敌方的坦克
                g.setColor(Color.cyan);
                break;
        }

        //根据坦克方向来绘制坦克
        switch (direct) {
            case 0://向上的坦克
                g.fill3DRect(x, y, 10, 60, false);//左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间方形盖子
                g.fillOval(x + 10, y + 20, 20, 20);//中间圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//炮筒
                break;
            case 1://向右的坦克
                g.fill3DRect(x, y, 60, 10, false);//左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//中间方形盖子
                g.fillOval(x + 20, y + 10, 20, 20);//中间圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//炮筒
                break;
            case 2://向下的坦克
                g.fill3DRect(x, y, 10, 60, false);//左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间方形盖子
                g.fillOval(x + 10, y + 20, 20, 20);//中间圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
                break;
            case 3://向左的坦克
                g.fill3DRect(x, y, 60, 10, false);//左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//中间方形盖子
                g.fillOval(x + 20, y + 10, 20, 20);//中间圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//炮筒
                break;
            default:
                System.out.println("暂时不处理");
        }
    }


    //有字符输出时，该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {


    }

    //当某个键按下，该方法就会触发
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {//上
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//右
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//下
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//左
            hero.setDirect(3);
            hero.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemyTank();
        }


        this.repaint();

    }

    //当某个键(释放)松开时，该方法会被触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            this.repaint();//只画了最后一个子弹线程
        }
    }
}


//class A{
//    int a;
//
////    public A() {
////    }
//
//    public A(int a) {
//        this.a = a;
//    }
//}
//class B extends A{//继承后，父类有空参构造器时，需要自己写构造器，自动生成只有父类的
//    int c;
//
//    public B(int a, int c) {//继承后，父类没有空参构造器时，，可以自动生成
//        super(a);
//        this.c = c;
//    }
//}