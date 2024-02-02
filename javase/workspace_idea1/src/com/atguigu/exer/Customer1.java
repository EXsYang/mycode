package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-26-12:58
 */
public class Customer1 extends Thread {//消费产品

    private Clerk clerk;

    public Customer1(Clerk clerk) {
        this.clerk = clerk;
    }


    @Override
    public void run() {
        System.out.println("客户："+getName()+"开始消费");
        for (; ; ) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           clerk.produceConsumer();
        }


    }



}
