package com.atguigu.exer;

import com.sun.xml.internal.ws.assembler.jaxws.ValidationTubeFactory;

import javax.lang.model.element.VariableElement;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-12:11
 */
@SuppressWarnings({"all"})
public class AccountTest {
    //有个账户，两个储户分别往里存3000，每次存1000，存三次，每次存完打印账户余额
    public static void main(String[] args) {
        Account account = new Account(0);

        Thread t1 = new Customer(account);
        Thread t2 = new Customer(account);

        t1.setName("储户一：");
        t2.setName("储户二：");

        t1.start();
        t2.start();

    }


}
class Account{
    private double balance;
    public Account(double balance){
        this.balance = balance;
    }


    public synchronized void deposit(double amt){//存钱方法
            notify();//交替打印
            balance += 1000;
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "存钱：" + "\t账户余额：" + balance);

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }

    }
class Customer extends Thread{

    Account account;
    public Customer(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0 ; i < 10000; i++){

            account.deposit(1000);//通过提高循环次数，两个线程确实交互了
        }

    }
}

