package com.atguigu.synchronized1;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-10:21
 */
public class BankTest {

}
class Bank{
    //解决懒汉式线程安全问题
    private Bank(){}

    private static Bank instance = null;

//    public static synchronized Bank getInstance(){
//        if (instance == null){
//            instance = new Bank();
//        }
//        return instance;
//    }
    public static Bank getInstance(){
        if (instance == null){
            synchronized (Bank.class) {
                if (instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }










}