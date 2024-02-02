package com.atguigu.exer1;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-16:31
 */
public class Customer extends Thread{
    //消费者 店员手里没有产品了，店员会通知消费者等一下，如果店里有产品了再通知消费者取走产品
    private Clerk clerk;

    public Customer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        //消费产品
        while (true){
            clerk.customerProduct();

        }

    }
}
