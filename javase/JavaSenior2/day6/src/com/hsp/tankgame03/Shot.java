package com.hsp.tankgame03;

import java.awt.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-22-21:57
 */
public class Shot implements Runnable {

    //    private int bullet;//记录坦克子弹数量
//    private int bulletNum;//记录坦克子弹数量
//    private int bulletSiteX = x + 18;//记录坦克子弹横向坐标
//    private int bulletSiteY = y + 28;//记录坦克子弹纵向坐标

    boolean loop;
    boolean isAlive = false;
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//
//    }
    Bullet bullet;

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }


    @Override
    public void run() {//按下“J”键，发射子弹，启动一个线程，当子弹触碰到边界时，销毁
//        System.out.println("线程启动");
        int count = 0;

            System.out.println(Thread.currentThread().getName() + "正在发射子弹");

        //这里有一个通知，
//        TankGame03.mp.setKey(true);//一个子弹线程进来，即start，通知开始重绘面板

        while (true) {
            try {
                Thread.sleep(500);//延迟太长，下面的通知
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("启动线程：" + Thread.currentThread().getName());
//            System.out.println(loop);//false
//            if (loop) {//继承于父类的loop,属于子类对象自己的一套属性，和父类的属性没有关系，父类对象也有自己的一套属性
            System.out.println("开始射击");
            switch (bullet.getBuDirect()) {//一开始无论怎样都往上发射，因为第一次进来就判度为向上，默认是0，然后又进了case 0 里面
                //shootUp()内的死循环出不来了,之前写的是上面注释的代码！！
                case 0:
                    MyPanel.hero.shootUp();
                    break;//跳出switch语句，并不会跳出while循环
                case 1:
                    MyPanel.hero.shootRight();
                    break;
                case 2:
                    MyPanel.hero.shootDown();
                    break;
                case 3:
                    MyPanel.hero.shootLeft();
                    break;
            }
//            }
            if (!(MyPanel.hero.getBu().get(MyPanel.num).getBuX() > 0 &&MyPanel.hero.getBu().get(MyPanel.num).getBuY() > 0 && MyPanel.hero.getBu().get(MyPanel.num).getBuX() <= 1000 &&MyPanel.hero.getBu().get(MyPanel.num).getBuY() <= 750)) {
                MyPanel.hero. getShot().isAlive = true;//子弹线程死亡，状态设置为true,MyPanel可以进入if(isAlive)
                //证明子弹线程死亡，可以跳出重绘循环，停止重绘
//                MyPanel.key = false;//用法不对
                TankGame03.mp.setKey(false); //当子弹线程死亡时停止绘制子弹
                System.out.println(Thread.currentThread().getName() + "线程死亡");
                break;
            }
            System.out.println(MyPanel.hero.getBuX()+ "  " +MyPanel.hero.getBuY());
            System.out.println("正在跑,子弹坐标正在发生改变" + ++count);//按下J 开始发射子弹后，直接随时都发射子弹了，需要重置一下
        }
            //在线程里调用的hero的shot()，我是每按下射击键就创建并启动一个线程，，
                //启动一个线程去调用shot()方法，让线程在run方法里调用其他类的方法，让他在其他类
                //的方法里，不断的循环，如果满足条件，跳出循环
                //这种方法，有局限性，写死了只能调用hero 的shot()方法，而且只有hero类里拥有射击动作，敌人的类里
                //没有射击动作，复用性太差，敌人还得在敌人的类里，再写一次射击动作



            //老师是把射击动作直接写在线程里，有一个射击动作就启动一次线程，这种方法，复用性强





    }
}
