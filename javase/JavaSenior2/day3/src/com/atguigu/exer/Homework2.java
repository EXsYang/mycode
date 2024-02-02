package com.atguigu.exer;

import sun.security.x509.SerialNumber;

/**
 * @author yangda
 * @description:
 * @create 2022-11-12-18:36
 */
public class Homework2 {
    public static void main(String[] args) {
        Forck.getNextNum();
        System.out.println(Forck.getNextNum());
        Forck forck = new Forck();
        System.out.println(forck.getSerialNumber());
        Forck forck1 = new Forck();
        System.out.println(forck1.getSerialNumber());
        Forck forck2 = new Forck();
        System.out.println(forck2.getSerialNumber());
    }
}
class Forck{
    private static int currentNum = 100000;
    private int SerialNumber;

    public Forck() {
        SerialNumber = getNextNum();
    }

    public int getSerialNumber() {
        return SerialNumber;
    }

    public static int getNextNum(){
        return currentNum += 100;
    }



}