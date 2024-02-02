package com.hsp.tankgame06;

import java.util.Vector;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-12:19
 */
public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector 保存多个Shot

    //让当前坦克持有其他敌人坦克的信息
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //    int x,y;//属性名和父类相同，就近原则,子类中有自己的属性，就先用自己的属性，就近原则
    int speed = 4;//子类自己定义的速度，没有用上，

//    public EnemyTank(int x, int y) {//默认是0，画地方坦克坐标，是按照子类的？x,y
//        this.x = x;
//        this.y = y;
//    }

    //这里提供一个方法 可以将MyPanel中的成员 Vector<EnemyTank> enemyTanks 设置到 EnemyTank 的成员enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断当前的这个敌人坦克，是否和 enemyTanks 中的其他坦克发生重叠或碰撞
    public boolean isTouchEnemyTank() {
        int count = 0;
        switch (getDirect()) {
            case 0://当前坦克方向向上，敌方坦克向上下/左右
                for (int i = 0; i < enemyTanks.size(); i++) {

                    EnemyTank enemyTank = enemyTanks.get(i);

                    //不和自己比较
                    if (this != enemyTank) {
                        //如果敌方坦克上下方向
                        //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 40]
                        //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 左上角的坐标 [this.getX(),this.getY()]
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40 && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                            //3.当前坦克右上角的坐标 [this.getX() + 40,this.getY()]
                            if (getX() + 40 >= enemyTank.getX() && getX() + 40 <= enemyTank.getX() + 40 && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60) {
                                System.out.println("坦克重叠了");
                                return true;
                            }

                        }
                        //敌方坦克左右方向
                        //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 60]
                        //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 左上角的坐标 [this.getX(),this.getY()]
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 60 && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 40) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                            //3.当前坦克右上角的坐标 [this.getX() + 40,this.getY()]
                            if (getX() + 40 >= enemyTank.getX() && getX() + 40 <= enemyTank.getX() + 60 && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 40) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://当前坦克方向向右，敌方坦克向上下/左右

                for (int i = 0; i < enemyTanks.size(); i++) {

                    EnemyTank enemyTank = enemyTanks.get(i);

                    //不和自己比较
                    if (this != enemyTank) {
                        //如果敌方坦克上下方向
                        //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 40]
                        //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 右上角坐标 [this.getX() + 60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40 && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                            //3.当前坦克 右下角的坐标 [this.getX() + 60,this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40 && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {
                                System.out.println("坦克重叠了");
                                return true;
                            }

                        }
                        //敌方坦克左右方向
                        //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 60]
                        //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 右上角的坐标 [this.getX() + 60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                            //3.当前坦克右下角的坐标 [this.getX() + 60,this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                        }
                    }
                }

                break;
            case 2://当前坦克方向向下，敌方坦克向上下/左右


            for (int i = 0; i < enemyTanks.size(); i++) {

                EnemyTank enemyTank = enemyTanks.get(i);

                //不和自己比较
                if (this != enemyTank) {
                    //如果敌方坦克上下方向
                    //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 40]
                    //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 60]

                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                        //2.当前坦克 左下角的坐标 [this.getX(),this.getY() + 60]
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                            System.out.println("坦克重叠了");
                            return true;
                        }
                        //3.当前坦克右下角的坐标 [this.getX() + 40,this.getY() + 60]
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40 && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                            System.out.println("坦克重叠了");
                            return true;
                        }

                    }
                    //敌方坦克左右方向
                    //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 60]
                    //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 40]
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                        //2.当前坦克 左下角的坐标 [this.getX(),this.getY() + 60]
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60 && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {
                            System.out.println("坦克重叠了");
                            return true;
                        }
                        //3.当前坦克右下角的坐标 [this.getX() + 40,this.getY() + 60]
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60 && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {
                            System.out.println("坦克重叠了");
                            return true;
                        }
                    }
                }
            }
                break;
            case 3://当前坦克方向向左，敌方坦克向上下/左右
                for (int i = 0; i < enemyTanks.size(); i++) {

                    EnemyTank enemyTank = enemyTanks.get(i);

                    //不和自己比较
                    if (this != enemyTank) {
                        //如果敌方坦克上下方向
                        //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 40]
                        //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2.当前坦克 左上角坐标 [this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                            //3.当前坦克 左下角的坐标 [this.getX(),this.getY() + 40]
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {
                                System.out.println("坦克重叠了");
                                return true;
                            }

                        }
                        //敌方坦克左右方向
                        //1.  x 的范围 [enemyTank.getX(),enemyTank.getX() + 60]
                        //    y 的范围 [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2.当前坦克 左上角的坐标 [this.getX(),this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60 && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                            //3.当前坦克左下角的坐标 [this.getX(),this.getY() + 40]
                            if (this.getX() >= enemyTank.getX() && this.getX()<= enemyTank.getX() + 60 && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                System.out.println("坦克重叠了");
                                return true;
                            }
                        }
                    }
                }

                break;
        }
        return false;

    }


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
        while (true) {
            if (isLive && shots.size() == 0) {//如果坦克子弹数量为0，创建一颗新的子弹
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), getDirect());
                        //加入到enemyTank的Vector 成员
                        shots.add(s);
                        new Thread(shot).start();
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, getDirect());
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
            switch (getDirect()) {
                case 0:
                    //让坦克保持一个方向走
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                        if (!isTouchEnemyTank()){
                            moveUp();//让坦克动起来
                        }
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
                        if (getX() + 60 < 1000) {
                            if (!isTouchEnemyTank()){
                                moveRight();
                            }
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
                        if (getY() + 60 < 750) {
                            if (!isTouchEnemyTank()){
                                moveDown();
                            }
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
                        if (getX() > 0) {
                            if (!isTouchEnemyTank()){
                                moveLeft();
                            }
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
            setDirect((int) (Math.random() * 4));

//            // 给该enemyTank 加入一颗子弹
//            Shot shot = new Shot(getX() + 20, getY() + 60, getDirect());
//            //加入到enemyTank的Vector 成员
//            shots.add(shot);
//            //启动
//            new Thread(shot).start();
////            setDirect(1);

            //一旦写多线程，并发程序，一定要考虑清楚，该线程什么时候结束
            if (!isLive) {
                break;
            }
        }
    }
}