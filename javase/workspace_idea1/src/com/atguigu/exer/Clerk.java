package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-26-12:53
 */
public class Clerk {

    public static int productCount = 0;

    public synchronized void produceProduct() {//生产产品,同步监视器当前类的对象c


        if (productCount < 20) {

            productCount += 1;
            System.out.println("生产者" + Thread.currentThread().getName() + ":生产第" + productCount + "个产品");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public synchronized void produceConsumer() {//消费产品,同步监视器当前类的对象


        if (productCount > 0) {

            System.out.println("客户" + Thread.currentThread().getName() + ":消费第" + productCount + "个产品");
            productCount -= 1;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

class ClerkTest {
    public static void main(String[] args) {

        Clerk c = new Clerk();

        Productor p = new Productor(c);


        Customer1 c1 = new Customer1(c);
        Customer1 c2 = new Customer1(c);

        p.setName("1");
        c1.setName("1");
        c2.setName("2");

        p.start();
        c1.start();
        c2.start();

    }
}