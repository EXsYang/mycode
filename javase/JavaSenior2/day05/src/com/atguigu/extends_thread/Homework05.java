package com.atguigu.extends_thread;

public class Homework05 {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        ProductTor p1 = new ProductTor(clerk);
        p1.setName("生产者1");
        Customer c1 = new Customer(clerk);
        c1.setName("消费者1");        c1.start();
        p1.start();

    }
}

class ProductTor extends Thread {//生产者，因为不是一个生产者，所以多线程
    private Clerk clerk;//店员对象

    public ProductTor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (clerk.getShangping() < 20) {
            synchronized (this) {
                if (clerk.getShangping() < 20) {
                    System.out.println(Thread.currentThread().getName() + ":开始生产" + clerk.getShangping());
                    clerk.setShangping(clerk.getShangping() + 1);
                    System.out.println("当前商品数量" + clerk.getShangping());
                } else {
                    System.out.println("商品已经有20个了，无需生产");
                }
            }
        }
    }
}

class Clerk {//店员类
    private static int shangping = 10;//商品数量

    public int getShangping() {
        return shangping;
    }

    public void setShangping(int shangping) {
        synchronized (this) {
            this.shangping = shangping;
        }
    }
}

class Customer extends Thread {//消费者，因为不是一个消费者，所以多线程
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (clerk.getShangping() > 0) {
            synchronized (this) {
                if (clerk.getShangping() > 0) {
                    System.out.println(Thread.currentThread().getName() + "购买商品" + clerk.getShangping());
                    clerk.setShangping(clerk.getShangping() - 1);
                    System.out.println("购买后商品数量" + clerk.getShangping());
                } else {
                    System.out.println("商品<0卖完了");
                }
            }
        }
    }
}
