package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-26-11:27
 */


class Account{
private double balance;

public Account(double balance){
    this.balance = balance;
}

public synchronized void deposid(double amt){
    if (amt > 0 ){
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            Thread.currentThread().sleep(20);//注意有形参
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance += amt;
        System.out.println(Thread.currentThread().getName()+"存钱成功。余额为："+balance);
    }


}


}
class Customer extends Thread{

    private Account acct;
    public Customer(Account acct){
        this.acct = acct;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.currentThread().sleep(22);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            acct.deposid(1000);

        }
    }
}










public class AccountTest {
    public static void main(String[] args) {
        Account acc = new Account(0);

        Customer c1 = new Customer(acc);
        Customer c2 = new Customer(acc);

        c1.setName("客户1");
        c2.setName("客户2");

        c1.start();
        c2.start();





    }
}
