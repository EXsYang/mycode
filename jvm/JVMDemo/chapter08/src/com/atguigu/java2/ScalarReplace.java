package com.atguigu.java2;

/**
 * 标量替换测试。  栈上分配的是那些不会逃逸的对象。
 * 标量替换是被应用起来了，但逃逸分析还不成熟，并没有被应用起来。
 * 这里测试的逃逸分析带来的优化提升，主要来源于标量替换。
 *
 *  -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 *
 * 标量替换的参数 -XX:-EliminateAllocations  【默认是打开的 -XX:+EliminateAllocations】
 * @author shkstart  shkstart@126.com
 * @create 2020  12:01
 */
public class ScalarReplace {
    public static class User {
        public int id;
        public String name;
    }

    public static void alloc() {
        User u = new User();//未发生逃逸
        u.id = 5;
        u.name = "www.atguigu.com";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");


        // 为了方便查看标量替换参数是否默认打开的，线程sleep
        // try {
        //     Thread.sleep(1000000);
        // } catch (InterruptedException e1) {
        //     e1.printStackTrace();
        // }

    }
}

/*
class Customer{
    String name;
    int id;
    Account acct;

}

class Account{
    double balance;
}


 */
