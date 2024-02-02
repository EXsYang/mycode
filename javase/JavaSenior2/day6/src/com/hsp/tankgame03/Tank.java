package com.hsp.tankgame03;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-11:33
 */
public class Tank {
    private int x;
    private int y;//坦克的横纵坐标
    private int direct;//坦克的方向
    private int speed = 10;//坦克的速度

//    private int bullet;//记录坦克子弹数量
//    private int bulletNum;//记录坦克子弹数量
    private int bulletSiteX = x + 18;//记录坦克子弹横向坐标
    private int bulletSiteY = y + 28;//记录坦克子弹纵向坐标

    private int buX;
    private int buY;//子弹发射时的纵坐标
    private int buDirect;//子弹发射时的方向

    public int getBuX() {
        return buX;
    }

    public void setBuX(int buX) {
        this.buX = buX;
    }

    public int getBuY() {
        return buY;
    }

    public void setBuY(int buY) {
        this.buY = buY;
    }

    public int getBuDirect() {
        return buDirect;
    }

    public void setBuDirect(int buDirect) {
        this.buDirect = buDirect;
    }
//子弹初始坐标为炮筒的炮管头的位置，先发射一枚子弹，先确定，子弹坐标，子弹大小，子弹速度
    //按下j 键，子弹开始移动，移动方向与坦克朝向相同




    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank() {
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

//    public int getBullet() {
//        return bullet;
//    }

//    public void setBullet(int bullet) {
//        this.bullet = bullet;
//    }

    public int getBulletSiteX() {
        return bulletSiteX;
    }

    public void setBulletSiteX(int bulletSiteX) {
        this.bulletSiteX = bulletSiteX;
    }

    public int getBulletSiteY() {
        return bulletSiteY;
    }

    public void setBulletSiteY(int bulletSiteY) {
        this.bulletSiteY = bulletSiteY;
    }

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


    public void shootUp(){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        buY -= speed;//子弹坐标时刻在变化
    }
    public void shootRight(){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buX += speed;
    }
    public void shootDown(){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buY += speed;
    }
    public void shootLeft(){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buX -= speed;
    }

}
