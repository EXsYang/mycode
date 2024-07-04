package com.atguigu.java;

/**
 * 1. 设置堆空间大小的参数
 * -Xms 用来设置堆空间（年轻代+老年代）的初始内存大小
 *      -X 是jvm的运行参数
 *      ms 是memory start
 * -Xmx 用来设置堆空间（年轻代+老年代）的最大内存大小
 *
 * 最好将-Xms和-Xmx设置为相同大小，可以提高性能
 *
 * 使用 `-Xms10m -Xmx10m -XX:+PrintGCDetails` 这组 JVM 参数
 * 确实设置了堆的最小（`-Xms`）和最大（`-Xmx`）大小都为 10 MB。
 * 理论上，这意味着堆的总大小被限制在这个范围内，不会超过 10 MB。
 * 然而，这并不意味着堆内的各个子区（如年轻代、老年代）的大小是固定不变的。
 * 这些区域的具体大小和占堆的比例可能会根据 JVM 的垃圾回收策略和堆的使用情况动态调整。
 *
 *
 * 2. 默认堆空间的大小
 *    初始内存大小：物理电脑内存大小 / 64
 *             最大内存大小：物理电脑内存大小 / 4
 * 3. 手动设置：-Xms600m -Xmx600m
 *     开发中建议将初始堆内存和最大的堆内存设置成相同的值。
 *
 * 4. 查看设置的参数：方式一： jps   /  jstat -gc 进程id
 *                  方式二：-XX:+PrintGCDetails
 * @author shkstart  shkstart@126.com
 * @create 2020  20:15
 *
 * -Xms600m -Xmx600m -XX:+PrintGCDetails
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {

        //返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

       // System.out.println("系统内存大小为：" + initialMemory * 64.0 / 1024 + "G");
       // System.out.println("系统内存大小为：" + maxMemory * 4.0 / 1024 + "G");

        // try {
        //     Thread.sleep(1000000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
