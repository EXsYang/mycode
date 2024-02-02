package com.hsp.tankgame03;


import java.util.ArrayList;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-11:34
 */
public class Hero extends Tank {

    int count;
    boolean loop;


    Bullet bullet;
    ArrayList<Bullet>  bu = null;
    static Shot shot = new Shot();//子弹
//    Bullet bullet = new Bullet();//子弹,.StackOverflowError,栈溢出

//    ArrayList<Thread> list = new ArrayList<>();//存放线程对象

    private int countBullet;//设置子弹数量

//    public ArrayList<Thread> getList() {
//        return list;
//    }
//
//    public void setList(ArrayList<Thread> list) {
//        this.list = list;
//    }


    public Hero(int x, int y, int countBullet) {//设置坦克出生位置，设置子弹数量
        super(x, y);
         bu = new ArrayList<Bullet>(countBullet);//存放子弹的集合
        for (int i = 0; i < countBullet; i++) {
            bu.add(new Bullet());//放入子弹对象
//            list.add(new Thread(shot));//放入了三个子弹
        }
    }

    public ArrayList<Bullet> getBu() {
        return bu;
    }

    public void setBu(ArrayList<Bullet> bu) {
        this.bu = bu;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public boolean getLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void shot() {//发射子弹的方法

            new Thread(shot).start();
            System.out.println(getBuX()+ "  " +getBuY());
            System.out.println("正在跑,子弹坐标正在发生改变" + ++count);//按下J 开始发射子弹后，直接随时都发射子弹了，需要重置一下
        }

    }


//    @Override
//    public void run() {
//        int count = 0;
//        while (true) {
//            if (loop){
//                switch (hero.getDirect()) {//一开始无论怎样都往上发射，因为第一次进来就判度为向上，默认是0，然后又进了case 0 里面
//                    //shootUp()内的死循环出不来了,之前写的是上面注释的代码！！
//                    case 0:
//
//                        for (int i = 0; i < 100; i++) {
//                            hero.shootUp();
//                            hero.repaint();//
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


//}
