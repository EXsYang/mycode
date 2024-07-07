package com.atguigu.java;

import java.util.ArrayList;

/**
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+PrintGCDetails -Xloggc:./logs/gc.log
 *
 * -Xms60m -Xmx60m -XX:+PrintGC -XX:+PrintGCDetails
 * 如果写两个打印GC信息的参数，则以更详细的`-XX:+PrintGCDetails`为准
 *
 * -Xms60m -Xmx60m -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps
 *
 *
 *
 *
 *
 * ----------------解释时间相关的两个参数【-XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps】---------------------
 * -XX:+PrintGCTimeStamps参数在垃圾收集日志行首输出的数字（例如7.904、17.400等）,
 * 表示自JVM启动以来的时间点（以秒为单位），即在这些时间点上发生了垃圾收集事件。
 * ，-XX:+PrintGCTimeStamps输出的这些时间戳是GC事件发生时距离JVM启动的时间，以秒为单位。通过这些时间戳，你可以了解GC事件的频率和时间分布。
 *
 *
 *
 * -XX:+PrintGCDateStamps参数输出的时间戳（例如2024-07-04T20:17:35.449+0800）表示GC事件发生的实际日期和时间，
 * 格式为ISO 8601标准。这与-XX:+PrintGCTimeStamps参数的作用类似，但区别在于：
 * -XX:+PrintGCDateStamps输出的时间戳表示GC事件发生的真实日期和时间，精确到毫秒。例如：
 * 7.895: [GC (Allocation Failure) [PSYoungGen: 15331K->2552K(17920K)] 15331K->13485K(58880K), 0.0048914 secs]
 * 这里的2024-07-04T20:17:35.449+0800表示GC事件发生在2024年7月4日20点17分35秒，东八区时间（+0800）。
 *
 * 结合使用这两个参数可以更详细地了解GC事件的时间信息。例如:
 * `2024-07-04T20:17:35.449+0800: 7.895:` 表示这次GC事件发生在2024年7月4日20点17分35秒，东八区时间，同时这是JVM启动后的7.895秒。
 * -------------------------------------------------------
 *
 *
 * -Xloggc:./logs/gc.log 该参数是将GC日志信息保存到文件中，
 * 其中 ./logs/gc.log 的 ./ 指的是当前目录，当前工程下logs文件夹,而不是指的当前module，
 * 需要手动先创建好logs文件夹，否则会报错：Java HotSpot(TM) 64-Bit Server VM warning: Cannot open file ./logs/gc.log due to No such file or directory
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  18:12
 */
public class GCLogTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            byte[] arr = new byte[1024 * 100];//100KB
            list.add(arr);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
