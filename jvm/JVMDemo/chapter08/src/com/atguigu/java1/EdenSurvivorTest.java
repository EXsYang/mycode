package com.atguigu.java1;

/**
 * -Xms600m -Xmx600m
 *
 * -XX:NewRatio ： 设置新生代与老年代的比例。默认值是2. 新生代:老年代 = 1 : 2。-XX:NewRatio=2 表示老年代的大小是新生代的两倍。
 *
 * -XX:SurvivorRatio ：设置新生代中Eden区与Survivor区的比例。默认值是8【实际测试是6】。
 * -XX:SurvivorRatio=8 表示 Eden 区的大小是一个 Survivor 区的 8 倍。
 * 新生代内存划分：
 * 新生代分为三个区域：一个 Eden 区和两个 Survivor 区 (S0 和 S1)。
 * 新生代总大小 = Eden 区大小 + 两个 Survivor 区大小。
 * Eden 区大小 = 新生代总大小的 8 / (8 + 1 + 1) = 新生代总大小的 8/10。
 * 每个 Survivor 区大小 = 新生代总大小的 1 / (8 + 1 + 1) = 新生代总大小的 1/10。
 *
 *
 * -XX:-UseAdaptiveSizePolicy ：关闭自适应的内存分配策略  （暂时用不到）,关闭后也不好使，还是默认值是8【实际测试是6】
 * -Xmn:设置新生代的空间的大小。 （一般不设置）
 *
 * 如果同时设置了-XX:NewRatio和-Xmn, 则以显示的设置新生代的内存大小的 -Xmn为准
 * 测试参数如下:
 * -Xms600m -Xmx600m (默认不设置-XX:NewRatio时,该参数默认值就为2)
 * -Xms600m -Xmx600m -Xmn100m
 *
 *
 *  存在的问题:
 *  默认情况下 新生代中Eden:Survivor0:Survivor1 为 8 : 1 : 1
 *  但是实际发现 新生代中Eden:Survivor0:Survivor1 为 6 : 1 : 1
 *  要想8 : 1 : 1 需要显示的设置一下JVM参数 -XX:SurvivorRatio=8
 *  才会变为8 : 1 : 1
 *  (补充，不管-XX:+UseAdaptiveSizePolicy启用还是不启用，
 *   如果没有显示的设置-XX:SurvivorRatio=8,
 *  新生代中Eden:Survivor0:Survivor1 都为 6 : 1 : 1)
 *
 *
 * 设置参数:
 * -Xms600m -Xmx600m -XX:SurvivorRatio=8
 * -Xms600m -Xmx600m -Xmn100m
 *
 * @create 2020  17:23
 */
public class EdenSurvivorTest {
    public static void main(String[] args) {
        System.out.println("我只是来打个酱油~");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
