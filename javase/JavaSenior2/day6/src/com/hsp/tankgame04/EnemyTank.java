package com.hsp.tankgame04;

import java.util.Vector;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-12:19
 */
public class EnemyTank extends Tank implements Runnable{
    //在敌人坦克类，使用Vector 保存多个Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    int x,y;
    int speed;

//    public EnemyTank(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }

    public void moveUp(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLeft(){
        x -= speed;
    }

    @Override
    public void run() {
        while (true){
            //根据坦克的方向继续移动
            switch (getDirect()){
                case 0:
                    //让坦克保持一个方向走
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0){
                            moveUp();
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
//            setDirect(1);
        //一旦写多线程，并发程序，一定要考虑清楚，该线程什么时候结束
            if (!isLive){
                break;
            }
        }
    }
}