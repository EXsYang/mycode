package com.atguigu.exer1;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-16:31
 */
public class Clerk{
    //店员类，告诉生产者或消费者等一下，假设店员最多有20个产品
    private int product;//共享数据,产品的数量


    public synchronized void customerProduct() {
        if(product > 0){
            //消费者消费
            while (true){

                product--;
                System.out.println("消费者消费：第" + product + "个");
                notify();
            }
//            notify();//Unreachable statement    遥不可及的声明
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void produceProduct() {
        if (product < 20){
            //生产者生产
                product++;
                notify();
                System.out.println("生产者生产：第" + product + "个");
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
