package com.atguigu.java;

/**
 * -XX:+PrintGCDetails
 * 证明：java使用的不是引用计数算法
 *
 *
 *
 * 引用计数（Reference Counting）概述
 * 引用计数法是一种简单的内存管理技术。每个对象都有一个与之相关的引用计数器，这个计数器用来记录有多少个引用指向该对象。
 *
 * 基本机制：每当一个新的引用指向一个对象时，该对象的引用计数增加1；当某个引用不再指向该对象时，引用计数减少1。当对象的引用计数为0时，意味着没有任何引用指向该对象，对象可以被安全地销毁。
 * 优点
 * 实时性：最大的优点是实时性，能立即回收垃圾，不会出现明显的延迟。
 * 简单性：算法实现简单，易于理解，易于实现。
 * 缺点
 * 无法解决循环引用问题：这是引用计数的最大缺点。在包含循环引用的情况下，即使对象彼此间只有内部引用，也不会被回收，导致内存泄漏。
 * 维护引用计数的开销：每次引用变更都需要更新计数，这种开销在高并发情况下可能会成为性能瓶颈。
 * 分段性能影响：更新引用计数需要时间，尤其是在多线程环境中，可能需要额外的锁定或同步操作，影响性能。
 * 总结
 * 尽管引用计数法有其局限性，但在某些情况下，如需要即时回收内存的场景中，它仍然是一个有效的内存管理策略。然而，由于循环引用问题，它很少作为Java等现代语言的主要垃圾回收机制。
 *
 *
 *
 * @author shkstart
 * @create 2020 下午 2:38
 */
public class RefCountGC {
    //这个成员属性唯一的作用就是占用一点内存
    private byte[] bigSize = new byte[5 * 1024 * 1024];//5MB

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        // System.gc(); //测试先不调用gc,在内存够的情况下，不会有gc行为。运行程序，
        // 发现 -XX:+PrintGCDetails 并没有打印GC垃圾回收相关的日志信息,而是直接显示堆的内存情况
        // 说明没有进行过垃圾回收,

        //显式的执行垃圾回收行为
        //这里发生GC，obj1和obj2能否被回收？回收掉了，说明Java使用的不是引用计数算法
        /**
         * 因为这里 堆空间中的两个对象的reference属性互相指向另一个对象
         * 引用计数器肯定不是0
         * 但是测试发现，这两个对象已经被回收了，反证可得,Java使用的不是引用计数算法
         */
        System.gc(); // 显示调用GC

        // try {
        //     Thread.sleep(1000000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
