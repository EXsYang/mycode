package com.athuigu.java;

/**
 * @author yangda
 * @create 2021-11-26-8:35
 */
public class BankTest {
    public static void main(String[] args) {

    }
}
class Bank{
    private Bank(){

    }
    private static Bank instance = null;
    public static  Bank getInstance() {
        if (instance == null) {
            synchronized (Bank.class) {

                if (instance == null) {
                    instance = new Bank();
                }
            }

        }
        return instance;
    }


}