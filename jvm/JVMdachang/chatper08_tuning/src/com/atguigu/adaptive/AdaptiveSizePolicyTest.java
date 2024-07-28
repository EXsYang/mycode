package com.atguigu.adaptive;

/**
 * 使用ParallelGC的情况下，不管是否开启了UseAdaptiveSizePolicy参数，默认Eden与Survivor的比例都为：6:1:1
 *
 *
 * 默认情况下jdk8分配的堆空间的大小为:  127 + 21 + 21 + 339 = 508M 【6:1:1】
 *
 *
 *
 *
 * 为什么jdk8默认我们的SurvivorRatio= 8 (默认时，即没手动设置过，但此时没有显示设置在JVM参数里)
 * 但是内存分配却不是8:1:1而是6:1:1?
 *
 * JDK 1.8 默认使用 UseParallelGC 垃圾回收器，该垃圾回收器默认启动了 AdaptiveSizePolicy，
 * 会根据GC的情况自动计算计算 Eden、From 和 To 区的大小；
 * 所以这是由于JDK1.8的自适应大小策略导致的，
 * 除此之外，我们下面观察GC日志发现有很多类似这样的FULLGC（Ergonomics），也是一样的原因。
 *
 *
 * 测试开启CMS GC的参数： -XX:+UseConcMarkSweepGC
 *
 *
 *
 *
 * 附：对于面向外部的大流量、低延迟系统，不建议启用此参数，建议关闭该参数(AdaptiveSizePolicy)。
 *
 * 如果不想动态调整内存大小，以下是解决方案：
 *
 * 1、保持使用 UseParallelGC，显式设置 -XX:SurvivorRatio=8。
 *
 * 2、使用 CMS 垃圾回收器。CMS 默认关闭 AdaptiveSizePolicy。配置参数 -XX:+UseConcMarkSweepGC
 *
 * @author shkstart
 * @create 12:53
 */
public class AdaptiveSizePolicyTest {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
