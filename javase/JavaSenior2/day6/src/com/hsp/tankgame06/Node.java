package com.hsp.tankgame06;

/**
 * @author yangda
 * @description: 存放读取到的信息
 * @create 2022-12-01-20:07
 */
public class Node {
    int x;
    int y;
    int direct;

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

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Node(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
}
