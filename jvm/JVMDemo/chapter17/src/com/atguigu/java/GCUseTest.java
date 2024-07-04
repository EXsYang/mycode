package com.atguigu.java;

import java.util.ArrayList;

/**
 *  -XX:+PrintCommandLineFlags
 *
 *  jps 、 jinfo -flag 相关垃圾回收器参数 进程ID【如: jinfo -flag UseParallelGC 6154】
 *
 *  -XX:+UseSerialGC:表明新生代使用Serial GC ，同时老年代使用Serial Old GC 【JVM没有`-XX:+UseSerialOldGC`参数】
 *
 *  -XX:+UseParNewGC：表明新生代使用ParNew GC 【设置-XX:+UseParNewGC并不会同时启用CMS GC，需要手动启用CMS GC。参考参数设置: -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:+UseConcMarkSweepGC】
 *  -XX:+UseConcMarkSweepGC：表明老年代使用CMS GC。
 *
 *
 *  jdk8中默认使用的是Parallel GC和 Parallel Old GC
 *  -XX:+UseParallelGC:表明新生代使用Parallel GC
 *  -XX:+UseParallelOldGC : 表明老年代使用 Parallel Old GC
 *  说明：二者可以相互激活
 *
 *  jdk9中默认使用的是G1,直到jdk22默认使用的还是G1
 *  jdk15引入了ZGC,但默认使用的还是G1
 *  -XX:+UseConcMarkSweepGC：表明老年代使用CMS GC。同时，年轻代会触发对ParNew 的使用
 *
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  0:10
 */
public class GCUseTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        while(true){
            byte[] arr = new byte[100];
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
