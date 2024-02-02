package com.hsp.tankgame05;

import java.util.Vector;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-12:19
 */
public class EnemyTank extends Tank implements Runnable{
    //在敌人坦克类，使用Vector 保存多个Shot

    public EnemyTank(int x, int y) {
        super(x, y);
    }
//    int x,y;//属性名和父类相同，就近原则,子类中有自己的属性，就先用自己的属性，就近原则
    int speed = 4;//子类自己定义的速度，没有用上，

//    public EnemyTank(int x, int y) {//默认是0，画地方坦克坐标，是按照子类的？x,y
//        this.x = x;
//        this.y = y;
//    }


//    @Override
//    public int getX() {//如果子类中不重写父类的get(),调用的是父类里的getX()
//        return x;
//    }
//
//    @Override
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    @Override
//    public int getY() {
//        return y;
//    }
//
//    @Override
//    public void setY(int y) {
//        this.y = y;
//    }

//    public void moveUp(){//关闭以后，坦克不动了
//        y -= speed;
//    }
//    public void moveRight(){//子类重写父类的，调用的是父类重写的，
//        x += speed;//就近原则
//    }
//    public void moveDown(){
//        y += speed;
//    }
//    public void moveLeft(){
//        x -= speed;
//    }

    @Override
    public void run() {
        while (true){
            if (isLive && shots.size() == 0){//如果坦克子弹数量为0，创建一颗新的子弹
                Shot s = null;
                switch (getDirect()){
                    case 0:
                        s = new Shot(getX() + 20, getY(), getDirect());
                        //加入到enemyTank的Vector 成员
                        shots.add(s);
                        new Thread(shot).start();
                        break;
                    case 1:
                    s =   new Shot(getX() + 60, getY() + 20, getDirect());
                    //加入到enemyTank的Vector 成员
                    shots.add(s);
                        break;
                    case 2:
                    s = new Shot(getX() + 20, getY() + 60, getDirect());
                    //加入到enemyTank的Vector 成员
                    shots.add(s);
                        break;
                    case 3:
                    s = new Shot(getX(), getY() + 20, getDirect());
                    //加入到enemyTank的Vector 成员
                    shots.add(s);
                        break;
                }
//                System.out.println("走到了吗");
                new Thread(s).start();
            }



            //根据坦克的方向继续移动
            switch (getDirect()){
                case 0:
                    //让坦克保持一个方向走
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0){
                            moveUp();//让坦克动起来
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 1:
                    //让坦克保持一个方向走
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60  < 1000){
                             moveRight();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:
                    //让坦克保持一个方向走
                    for (int i = 0; i < 30; i++) {
                        if (getY()+60 < 750){
                            moveDown();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 3:
                    //让坦克保持一个方向走
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0){
                            moveLeft();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }



            //然后随机的改变坦克方向 0-3
            setDirect((int)(Math.random() * 4));

//            // 给该enemyTank 加入一颗子弹
//            Shot shot = new Shot(getX() + 20, getY() + 60, getDirect());
//            //加入到enemyTank的Vector 成员
//            shots.add(shot);
//            //启动
//            new Thread(shot).start();
////            setDirect(1);

        //一旦写多线程，并发程序，一定要考虑清楚，该线程什么时候结束
            if (!isLive){
                break;
            }
        }
    }
}