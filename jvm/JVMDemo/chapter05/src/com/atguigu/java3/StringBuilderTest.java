package com.atguigu.java3;

/**
 * 面试题：
 * 方法中定义的局部变量是否线程安全？具体情况具体分析
 *
 *   何为线程安全？
 *      如果只有一个线程才可以操作此数据，则必是线程安全的。
 *      如果有多个线程操作此数据，则此数据是共享数据。如果不考虑同步机制的话，会存在线程安全问题。
 * @author shkstart
 * @create 2020 下午 7:48
 */
public class StringBuilderTest {

    int num = 10;

    //s1的声明方式是线程安全的
    public static void method1(){
        //StringBuilder:线程不安全
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        //...
    }
    //sBuilder的操作过程：是线程不安全的
    public static void method2(StringBuilder sBuilder){
        sBuilder.append("a");
        sBuilder.append("b");
        //...
    }
    //s1的操作：是线程不安全的
    public static StringBuilder method3(){
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1;
    }
    //s1的操作：是线程安全的
    public static String method4(){
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1.toString();
    }

    /**
     * 为什么会出现线程安全问题？
     * 共享变量访问：在代码中，StringBuilder 实例 s 被主线程和一个子线程共享。由于 StringBuilder 不是线程安全的，它的内部状态（如字符数组的长度和内容）可能在没有适当同步的情况下被多个线程同时修改。
     *
     * 非原子操作：StringBuilder 的 append 方法并非原子操作，它涉及多个步骤，如检查内部数组大小、扩展数组（如有必要）、添加字符和更新长度计数器。当两个线程同时执行这些操作时，没有机制保证这些操作的原子性，可能导致内部状态的混乱。
     *
     * 线程安全问题具体表现
     * 数据损坏：由于线程之间的干扰，最终的字符串可能包含重复的字符，或者字符数目少于预期（例如，期望是四个字符"abab"，实际可能只有三个字符如"aba"）。
     *
     * 顺序错乱：虽然每个线程都尝试按照 "a" 后 "b" 的顺序添加字符，但由于线程调度和执行的不确定性，最终字符串的顺序可能不符合任何单个线程的添加顺序（尽管通常应保持每个线程内的顺序不变，但实际输出可能是如"abba"、"bbaa"等）。
     *
     * 结论
     * 由于线程之间对共享 StringBuilder 实例的并发访问未加同步，可能导致数据损坏或结果不一致。这突显了在多线程环境中共享可变状态时使用线程安全数据结构或同步机制的重要性。如果需要确保操作的线程安全，考虑使用 StringBuffer 或在操作 StringBuilder 时加入适当的同步措施。
     */
    public static void main(String[] args) {
        // 创建一个StringBuilder实例，这个实例将被多个线程共享
        StringBuilder s = new StringBuilder();


        //主线程启动了一个新线程来修改StringBuilder实例，
        // 而几乎同时主线程自己也在修改同一个StringBuilder实例。
        // 这两个操作是并发执行的，这正是潜在的线程安全问题所在。
        //这意味着新线程可以在主线程继续执行下一行代码之前开始执行，也可能稍后执行。
        // 因此，以下两个操作：
        //
        // 新线程中的 s.append("a"); s.append("b");
        // 主线程调用的 method2(s); 中的 sBuilder.append("a"); sBuilder.append("b");
        // 是并发执行的。这两个操作可能在任何时刻交替进行，取决于操作系统如何调度两个线程。
        //
        //并发执行的问题
        // 因为 StringBuilder 是非线程安全的，它的内部状态（如存储字符的数组和维护当前长度的计数器）可能在没有适当的同步措施的情况下被并发修改，这可能导致以下几种问题：
        //
        // 数据竞争：如果两个线程试图同时修改StringBuilder的内部数据结构，一个线程的修改可能覆盖另一个线程的修改，或者使内部状态（如字符数组的大小和内容）不一致。
        // 结果不可预测：由于执行顺序的不确定性，最终的字符串内容可能是 "aabb"、"abab"、"bbaa" 等任何组合，甚至可能因为数据竞争导致输出结果损坏（如字符数少于4个）。
        // 解决并发问题
        // 要解决这种并发问题，您可以采取以下措施之一：
        //
        // 使用线程安全的替代品：比如使用 StringBuffer，它是线程安全的，内部实现了同步。
        // 同步访问共享资源：您可以通过同步块或者锁来控制对StringBuilder的访问，确保一次只有一个线程可以修改它。
        // 避免共享：尽可能确保每个线程使用自己的 StringBuilder 实例，或者其他避免共享可变状态的策略。
        // 通过这些方法，您可以防止多线程环境下的数据竞争和状态不一致问题，确保应用的稳定性和可靠性。


        // 启动一个新线程，对StringBuilder实例进行操作
        new Thread(() -> {
            s.append("a");  // 在新线程中添加字符'a'
            s.append("b");  // 紧接着添加字符'b'
        }).start();  // 启动线程

        // 在主线程中也对同一个StringBuilder实例进行操作
        method2(s);

    }


    // public static void main(String[] args) {
    //     StringBuilder s = new StringBuilder();
    //
    //     // 创建第一个线程，负责向StringBuilder中添加1000个'a'
    //     Thread t1 = new Thread(() -> {
    //         for (int i = 0; i < 1000; i++) {
    //             s.append("a");
    //         }
    //     });
    //
    //     // 创建第二个线程，负责向StringBuilder中添加1000个'b'
    //     Thread t2 = new Thread(() -> {
    //         for (int i = 0; i < 1000; i++) {
    //             s.append("b");
    //         }
    //     });
    //
    //     // 启动两个线程
    //     t1.start();
    //     t2.start();
    //
    //     try {
    //         // 等待两个线程执行完毕
    //         t1.join();
    //         t2.join();
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //
    //     // 输出最终StringBuilder的长度
    //     // 预期是2000，但由于线程安全问题，可能会小于2000
    //     System.out.println("Length of StringBuilder: " + s.length());
    //     // 这种长度小于2000的现象表明StringBuilder在多线程操作中的线程不安全性
    // }

}
