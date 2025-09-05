package com.atguigu.sync;

//第一步 创建资源类，定义属性和操作方法
class Share {
    //初始值
    private int number = 0;
    //+1的方法
    public synchronized void incr() throws InterruptedException {


        //第二步 判断 干活 通知
        // if(number != 0) { //判断number值是否是0，如果不是0，等待

        //
        // 关键问题点：现在轮到 DD 抢到锁了。DD 也从 wait() 的地方醒来。同样，因为它使用的是 if，它也不会再次检查条件，直接执行 number--。但此时 number 已经是 0 了！执行减法后，number 变成了 -1
        // 通俗的讲，DD wait后释放锁，后来又被DD抢到了锁，但是这时候如果是if判断，则会在睡着的地方醒来，不会走上面的if判断。
        //     this.wait(); //在哪里睡，就在哪里醒,这里如果使用if判断则会存在虚假唤醒问题。
        // }

        // API文档描述：
        // 对于某一个参数的版本，实现中断和虚假唤醒是可能的，而且此方法应始终在循环中使用：
 //        synchronized (obj) {
 //            while (<condition does not hold>)
 //            obj.wait();
 // Perform action appropriate to condition
 //        }
        // 此方法只应由作为此对象监视器的所有者的线程来调用。


        // 关键区别：因为使用的是 while 循环，DD 醒来后会重新回到循环的开始，
        // 再次检查条件 number != 1。
        while(number != 0) { //判断number值是否是0，如果不是0，等待
            this.wait(); //在哪里睡，就在哪里醒
        }
        //如果number值是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName()+" :: "+number);
        //通知其他线程
        this.notifyAll();
    }

    //-1的方法
    public synchronized void decr() throws InterruptedException {
        //判断
        while(number != 1) {
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName()+" :: "+number);
        //通知其他线程
        this.notifyAll();
    }
}

public class ThreadDemo1 {
    //第三步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        Share share = new Share();
        //创建线程
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.incr(); //+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.decr(); //-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.incr(); //+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.decr(); //-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
