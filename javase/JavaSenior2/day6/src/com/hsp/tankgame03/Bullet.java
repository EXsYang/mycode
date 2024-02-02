package com.hsp.tankgame03;

/**
 * @author yangda
 * @description:
 * @create 2022-11-24-10:41
 */
public class Bullet {

    private int buX;
    private int buY;//子弹发射时的纵坐标
    private int buDirect;//子弹发射时的方向
    private int speed = 15;

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



}
