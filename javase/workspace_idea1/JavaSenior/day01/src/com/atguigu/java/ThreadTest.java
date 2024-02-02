package com.atguigu.java;

import java.util.Collections;

/**
 * @author yangda
 * @create 2021-11-24-9:26
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread m = new MyThread();
        m.start();
    }


}

class MyThread extends Thread {
    //
    int number = 100;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
//            if(number % 2 == 0 ){//99进不去，number就减不了，继续循环还是进不去减不了
//                System.out.println(number);
//                number--;
//            }
            if (number % 2 == 0) {

                System.out.println(number);
            }
            number--;
        }
    }
}