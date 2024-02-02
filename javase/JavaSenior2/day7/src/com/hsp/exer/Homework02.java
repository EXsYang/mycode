package com.hsp.exer;

/**
 * @author yangda
 * @description:
 * @create 2022-11-22-16:53
 */
public class Homework02 {
    public static void main(String[] args) {
        Account account = new Account();
        Consumer c1 = new Consumer(account);
        Consumer c2 = new Consumer(account);

        c1.setName("用户1");
        c2.setName("用户2");

        c1.start();
        c2.start();

    }
}
class Account{
private double balance = 10000;

//取钱方法
public synchronized void drawBalance(){
    if (balance >= 1000) {
        balance -= 1000;

        System.out.println(Thread.currentThread().getName() + "取钱，余额为：" + balance);

    }
}

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
class Consumer extends Thread{
    private Account account;

    public Consumer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        while (true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.drawBalance();
            if (account.getBalance() <= 0){
                break;
            }
        }


    }
}