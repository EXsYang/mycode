package com.hsp.tankgame06;

/**
 * @author yangda
 * @description:
 * @create 2022-11-20-11:34
 */
public class Hero extends Tank {
    //定义一个Shot对象，表示一个射击(线程)
//    Shot shot = null;//一颗子弹
//
//    //可以发射多颗子弹//用一个数组存放子弹
//    Vector<Shot> shots = new Vector<>();
    private int x,y;
    private int speed = 50;

    public Hero(int x, int y) {
        super(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    //射击
//    public void shotEnemyTank(){
//        //发射多颗子弹怎么办，控制在我们的面板上，最多只有5颗
//        if (shots.size() == 33){
//            return;
//        }
//

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void moveUp(){//子类对象调用，只能修改父类里对应的属性值
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
//
//
//    //创建 Shot 对象，根据当前Hero对象的位置和方向来创建Shot
//        switch (getDirect()){
//            case 0://向上
//                shot = new Shot(getX() + 20,getY(),0);
//                break;
//            case 1://向右
//                shot = new Shot(getX() + 60,getY() + 20,1);
//                break;
//            case 2://向下
//                shot = new Shot(getX() + 20,getY() + 60,2);
//                break;
//            case 3://向上
//                shot = new Shot(getX(),getY() + 20,3);
//                break;
//        }
//
//        //把子弹放入Vector集合shots
//        shots.add(shot);
//
//        //启动Shot线程
//        new Thread(shot).start();
//    }





}
