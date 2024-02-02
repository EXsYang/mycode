package com.hsp.tankgame03;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author yangda
 * @description: 坦克大战绘图区域
 * @create 2022-11-20-11:35
 */
public class MyPanel extends JPanel implements Runnable, KeyListener {

    static int num = 0;//记录按下"J"的次数
    boolean loop;
    Vector<EnemyTank> enemyTanks;
    boolean key = false;//为什么一直是false?

    public boolean getKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

//    public void test(){
//        System.out.println(" ");
//    }

    //定义我的坦克
//    Hero hero = null;//共享同一个hero
    static Hero hero = null;//共享同一个hero
    private int enemysize = 3;

    public MyPanel() {//继承后需要自己写自己的
        hero = new Hero(0, 0, 3);//初始化自己的坦克

        enemyTanks = new Vector<>();

        for (int i = 0; i < enemysize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 + i * 100, 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }


    }

    boolean drawBu;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认是黑色
//        drawTank(hero.getX(), hero.getY(), g, 0, 0);
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        //调用了，但没进去
        System.out.println("没进去");
        if (drawBu) {//按下j,画出子弹
//            while (true){//不停的重绘
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            System.out.println("进来了");//每按一次j,就需要重新画一颗子弹，并且启动一个子弹线程
//            num 记录按下j 的次数
            if (num < hero.bu.size()) {
                for (int i = 0; i < num + 1; i++) {//打完一颗再打另一颗
                    drawBullet(hero.bu.get(num).getBuX(), hero.bu.get(num).getBuY(), g);
                }
                num++;
            }
            //画出子弹，不停的在原地画子弹，用的是刚刚按下的坐标，没有用一直变化的坐标

            //把子弹对象保存到一个类里，封装成一个对象，不同的子弹对象
            //有不同的对应的坐标值，

//                if (!(MyPanel.hero.getBulletSiteX() > 0 && MyPanel.hero.getBulletSiteY() > 0 && MyPanel.hero.getBulletSiteX() <= 1000 && MyPanel.hero.getBulletSiteY() <= 750)) {
//                    break;//子弹碰到边界，跳出循环
        }
//            }
//
//        }


        for (int i = 0; i < enemysize; i++) {
            EnemyTank enemyTank = enemyTanks.get(i);

            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);


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

    /*
    单独绘制子弹的方法
    buX:按下J键时，子弹的横坐标
    buDirect:按下J键时，子弹的方向
     */
    public void drawBullet(int buX, int buY, Graphics g) {
        g.setColor(Color.white);
        g.fill3DRect(buX, buY, 3, 3, false);//绘制子弹


    }

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
//                g.setColor(Color.white);
//                g.fillOval(hero.getBulletSiteX(), hero.getBulletSiteY(), 4, 4);//子弹
                break;
            case 1://向右的坦克
                g.fill3DRect(x, y, 60, 10, false);//左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//中间方形盖子
                g.fillOval(x + 20, y + 10, 20, 20);//中间圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//炮筒
//                g.setColor(Color.white);
//                g.fillOval(hero.getBulletSiteX(), hero.getBulletSiteY(), 4, 4);//子弹
                break;
            case 2://向下的坦克
                g.fill3DRect(x, y, 10, 60, false);//左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间方形盖子
                g.fillOval(x + 10, y + 20, 20, 20);//中间圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
//                g.setColor(Color.white);
//                g.fillOval(hero.getBulletSiteX(), hero.getBulletSiteY(), 4, 4);//子弹
                break;
            case 3://向左的坦克
                g.fill3DRect(x, y, 60, 10, false);//左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//中间方形盖子
                g.fillOval(x + 20, y + 10, 20, 20);//中间圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//炮筒
//                g.setColor(Color.white);
//                g.fillOval(hero.getBulletSiteX(), hero.getBulletSiteY(), 4, 4);//子弹
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
//            根据时刻在变化的坦克的位置，来设置子弹任意时刻的坐标
            hero.setBulletSiteX(hero.getX() + 18);
            hero.setBulletSiteY(hero.getY() + 28);
//            hero.shootUp();

        } else if (e.getKeyCode() == KeyEvent.VK_D) {//右
            hero.setDirect(1);
            hero.moveRight();

            hero.setBulletSiteX(hero.getX() + 28);
            hero.setBulletSiteY(hero.getY() + 18);
//            hero.shootRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//下
            hero.setDirect(2);
            hero.moveDown();

            hero.setBulletSiteX(hero.getX() + 18);
            hero.setBulletSiteY(hero.getY() + 28);
//            hero.shootDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//左
            hero.setDirect(3);
            hero.moveLeft();

            hero.setBulletSiteX(hero.getX() + 28);
            hero.setBulletSiteY(hero.getY() + 18);
//            hero.shootLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_J) {//按下“J”，启动一个子弹线程，子弹开始发射，
            //当按下“J”键时，确定此时坦克子弹的发出位置

            drawBu = true;    //单独绘制子弹，不可以和坦克同时绘制


            //取出子弹对象的集合/这里按一次赋一次值
            if (num < hero.bu.size()) {//
//                hero.setLoop(true);//这里是主线程，通知方式，告诉子线程发射子弹
                System.out.println("第" + (num + 1) + "颗子弹"); //按一次J，加一次,启动子弹线程
                hero.bu.get(num).setBuX(hero.getBulletSiteX());
                hero.bu.get(num).setBuY(hero.getBulletSiteY());
                hero.bu.get(num).setBuDirect(hero.getDirect());//设置第num个子弹的信息

                hero.shot();
            } else {
                System.out.println("没有子弹了！！");
                drawBu = false;//没有子弹了就不画子弹了
            }

        }

//        }
//       else if {//发射子弹
//
//            switch(hero.getDirect()){
//                case 0:
//                    hero.shootUp();
//                case 1:
//                    hero.shootRight();
//                case 2:
//                    hero.shootDown();
//                case 3:
//                    hero.shootLeft();
//            }
//
//        }

        this.repaint();//如果按得是其他键，自动重绘
    }

    public void shootB(Graphics g) {


//
//        }

    }


    //当某个键(释放)松开时，该方法会被触发
    @Override
    public void keyReleased(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_J) {//发射子弹
//
//            switch(hero.getDirect()){
//                case 0:
//                    hero.shootUp();
//                case 1:
//                    hero.shootRight();
//                case 2:
//                    hero.shootDown();
//                case 3:
//                    hero.shootLeft();
//            }
//
//        }
//        this.repaint();
    }


    //    public void shootUp(){
//        while (true){
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            bulletSiteY -= speed;//子弹坐标时刻在变化
//        }
//    }
    @Override
    public void run() {//一发射子弹就要启动一个子弹线程，调用shot方法，
        //子弹坐标开始修改，并且不停的重新绘制，定义一个变量，控制绘制开关

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("while:getKey():" + getKey());//通知的方式，确实改变了key的值
            while (key) {//如果有子弹线程调用了shot方法，就进来绘制，子弹线程死亡，停止绘制
//                System.out.println(this.getClass());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.repaint();//不停重绘
                System.out.println("面板正在不停的重绘");
                if (MyPanel.hero.getShot().isAlive) {//子弹线程活着时设置为false,防止没开炮时进来，
                    //开炮后改变子弹状态，此时子弹线程死亡改为true,跳出重绘循环
                    //即，没有子弹线程时不用重绘面板
                    System.out.println("isAlive = true");//可以进来，上面的条件设置成功了
//                子弹线程死亡
                    //子弹没装上TankGame03.list！！！
//                System.out.println(TankGame03.list.get(num).getName() + "子弹线程死亡");
//                    Shot.isAlive = false;//重置为false
                    MyPanel.hero.getShot().isAlive = false;
                    break;
                }
            }
//            System.out.println("mianmmmmmm");
        }


    }
//        int count = 0;

//        while (true) {
//            if (loop){
//                switch (hero.getDirect()) {//一开始无论怎样都往上发射，因为第一次进来就判度为向上，默认是0，然后又进了case 0 里面
//                    //shootUp()内的死循环出不来了,之前写的是上面注释的代码！！
//                    case 0:
//
//                        for (int i = 0; i < 100; i++) {
//                            hero.shootUp();
//                            this.repaint();//
//                        }
//                        loop = false;
//
//                        break;//跳出switch语句，并不会跳出while循环
//                    case 1:
//                        for (int i = 0; i < 100; i++) {
//                            hero.shootRight();
//                            this.repaint();//
//                        }
//                        loop = false;
//                        break;
//                    case 2:
//                        for (int i = 0; i < 100; i++) {
//                            hero.shootDown();
//                            this.repaint();//
//                        }
//                        loop = false;
//                        break;
//                    case 3:
//                        for (int i = 0; i < 100; i++) {
//                            hero.shootLeft();
//                            this.repaint();//
//                        }
//                        loop = false;
//                        break;
//                }
//                this.repaint();//
//            }
//            System.out.println("正在跑" + ++count);//按下J 开始发射子弹后，直接随时都发射子弹了，需要重置一下
//        }
//    }
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