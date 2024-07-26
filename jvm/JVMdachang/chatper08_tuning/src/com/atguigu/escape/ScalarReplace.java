package com.atguigu.escape;

/**
 * 标量替换测试
 *  -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:-EliminateAllocations
 * 栈上分配的是那些不会逃逸的对象。
 *
 *  疑问？C2编译器的栈上分配不应该是在栈上分配好多的User对象吗？怎么没看到那些User对象呢？
 *  结论：Java中的逃逸分析，其实优化的点就在于对栈上分配的对象进行标量替换。是使用的变量替换了，所以看不到User对象！
 *
 * @author shkstart  shkstart@126.com
 * @create 2021  12:01
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
