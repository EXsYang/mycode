package com.atguigu.java2;

/**
 * 栈上分配测试
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xmx1G -Xms1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 *
 * -Xmx256m -Xms256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 *
 * 注意: -XX:+DoEscapeAnalysis 逃逸分析是默认开启的
 *
 *
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  10:31
 */

/**
 * 逃逸分析与栈上分配的讨论：
 * - 逃逸分析 (Escape Analysis) 是JVM的一个优化技术，用于分析对象是否可以在方法内局部化，从而不必分配在堆上。
 * - 理论上，如果对象不逃逸到方法之外，这些对象可以在栈上分配，从而减少垃圾回收的压力。
 * - 但在Oracle HotSpot JVM中，逃逸分析主要用于其他优化，如标量替换（Scalar Replacement）和同步省略（Lock Elimination）。
 * - 实际上，HotSpot JVM并不普遍实施 `栈上分配` 。即使逃逸分析确定对象不逃逸，这些对象通常仍在堆上分配。
 * - 因此，逃逸分析虽然在理论上支持栈上分配，但在HotSpot JVM中，栈上分配并未广泛实施。
 * - 逃逸分析的实际效果包括减少不必要的对象分配和优化程序性能，但主要通过方法如标量替换，而非直接的栈上分配。
 * - 文档和实验数据表明，所有对象实例仍主要创建在堆上。
 */

public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();//未发生逃逸
    }

    static class User {

    }
}
