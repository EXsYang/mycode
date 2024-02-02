package com.hsp.tankgame;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-11:33
 */
public class Tank {
    private int x;
    private int y;//坦克的横纵坐标

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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


}
