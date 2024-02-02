package com.hsp.tankgame00;

import com.hsp.tankgame03.Bullet;

/**
 * @author yangda
 * @description:
 * @create 2022-11-22-21:57
 */
public class Shot implements Runnable {//子弹射击动作，一直做射击动作

    //    private int bullet;//记录坦克子弹数量
//     int bulletNum;//记录坦克子弹数量
    int x;//记录坦克子弹横向坐标
    int y;//记录坦克子弹纵向坐标
    int direct;
    int speed = 5;
    boolean isAlive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

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

    public int getSpeed() {
        return speed;
    }

    Bullet bullet;

    @Override
    public void run() {//按下“J”键，发射子弹，启动一个线程，当子弹触碰到边界时，销毁
//        System.out.println("线程启动");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
            }
            System.out.println(Thread.currentThread().getName() + "子弹线程：x=" + x + "    y=" + y);
            if (!(x >= 0 && y >= 0 && x <= 1000 && y <= 750)) {
                isAlive = false;
                System.out.println(Thread.currentThread().getName() + "子弹线程退出");
                break;
            }
        }


    }
}
