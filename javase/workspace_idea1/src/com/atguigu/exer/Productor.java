package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-26-12:52
 */
public class Productor extends Thread {//生产产品

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者：" + getName() + "开始生产");
        for (; ; ) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }

}
