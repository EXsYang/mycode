package com.hsp.tankgame04;

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
public class MyPanel extends JPanel implements Runnable, KeyListener {

    //定义我的坦克
    Hero hero = null;

    private int enemysize = 8;

    //定义一个Vector,用于存放炸弹
    //当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    //定义敌人坦克，放入到 Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public MyPanel() {//继承后需要自己写自己的
        hero = new Hero(100, 100);//初始化自己的坦克

        enemyTanks = new Vector<>();

        for (int i = 0; i < enemysize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 + i * 100, 0);
            //设置方向
            enemyTank.setDirect(2);
            //启动敌人坦克线程，让他动起来
            new Thread(enemyTank).start();


            //给该enemyTank 加入一颗子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //加入到enemyTank的Vector 成员
            enemyTank.shots.add(shot);
            //启动
            new Thread(shot).start();


            enemyTanks.add(enemyTank);
        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/hsp/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/hsp/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/hsp/bomb_3.gif"));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认是黑色
//        drawTank(hero.getX(), hero.getY(), g, 0, 0);
        //画出自己的坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);

        //画出hero射击的子弹
//        if (hero.shot != null && hero.shot.isAlive != false) {//先判断是否为空，不然，如果为null,空指针异常
//            g.fill3DRect(hero.shot.getX(), hero.shot.getY(), 3, 3, false);
//        }

        //将hero的子弹集合 shots ,遍历取出绘制
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
        if (shot != null && shot.isAlive) {//先判断是否为空，不然，如果为null,空指针异常
            g.fill3DRect(shot.getX(), shot.getY(), 3, 3, false);
        }else {//如果该shot对象已经无效，就从shots集合中拿掉
            hero.shots.remove(shot);
        }
        }


        //如果bombs集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画对应的图片
            if (bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if (bomb.life > 3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果 bomb life 为0，就从bombs 的集合中删除
            if(bomb.life == 0){
                bombs.remove(bomb);
            }


        }


        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isLive) {//当敌人坦克是存活的，才画出该坦克
            //当敌人坦克是存活的，才画出该坦克
                //判断当前坦克是否还存活
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制子弹
                    if (shot.isAlive) {
                        g.fill3DRect(shot.getX(), shot.getY(), 3, 3, false);
                    } else {
                        //从Vector移除
                        enemyTank.shots.remove(shot);
                    }
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

    public void hitEnemyTank(){
        for(int j = 0 ;j < hero.shots.size();j++) {//将子弹集合中的子弹全部取出，进行遍历，每一颗子弹和每一个敌人进行判断是否击中
            Shot shot = hero.shots.get(j);
            //判断是否击中了敌人坦克
            if (shot != null && shot.isAlive)//当前子弹还活着
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
        }
    }

    //编写方法，判断我方的子弹是否击中敌人坦克
    public void hitTank(Shot shot, EnemyTank enemyTank) {
        //判断s 击中坦克
        switch (enemyTank.getDirect()) {
            case 0:
            case 2:
                if (shot.x > enemyTank.getX() && shot.x < enemyTank.getX() + 40 &&
                        shot.y > enemyTank.getY() && shot.y < enemyTank.getY() + 60) {
                    shot.isAlive = false;
                    enemyTank.isLive = false;
                    //当子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(enemyTank);//移除了上下方向的坦克，下面的左右方向的没有移除，所以出现打掉了坦克还爆炸现象

                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (shot.x > enemyTank.getX() && shot.x < enemyTank.getX() + 60 &&
                        shot.y > enemyTank.getY() && shot.y < enemyTank.getY() + 40) {
                    shot.isAlive = false;
                    enemyTank.isLive = false;

                    enemyTanks.remove(enemyTank);

                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;

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
            if (hero.getY() > 0){

                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//右
            hero.setDirect(1);
            if (hero.getX() + 60 <1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//下
            hero.setDirect(2);
            if (hero.getY() + 60 < 750){
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//左
            hero.setDirect(3);
            if (hero.getX() > 0){
                hero.moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {

            //判断当前子弹线程是否销毁
            //发射一颗子弹：
//            if (hero.shot == null || !hero.shot.isAlive){
//                hero.shotEnemyTank();
//            }

            //发射多颗子弹
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
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //每颗子弹都进行判断是否击中敌人坦克
            hitEnemyTank();

            //下面是用一颗子弹进行判断的
//            if (hero.shot != null && hero.shot.isAlive)//当前子弹还活着
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot, enemyTank);
//                }

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