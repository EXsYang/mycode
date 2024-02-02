package com.atguigu.exer1;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-16:51
 */
public class ClerkTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Customer customer = new Customer(clerk);
        Productor productor = new Productor(clerk);

        customer.setName("消费者1");
        productor.setName("生产者1");

        customer.start();
        productor.start();

    }
}
