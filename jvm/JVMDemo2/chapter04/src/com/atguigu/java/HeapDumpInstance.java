package com.atguigu.java;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m -XX:+HeapDumpBeforeFullGC -XX:HeapDumpPath=d:\aatest\heapdump\heapdumpinstance.hprof
 * -Xms600m -Xmx600m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:\aatest\heapdump\heapdumpinstance.hprof
 *
 * 如果要指定生成dump文件的位置，需要指定的目录要存在，否则报下面的信息。
 * Dumping heap to d:\aatest\heapdump\heapdumpinstance.hprof ...
 * Unable to create d:\aatest\heapdump\heapdumpinstance.hprof: No such file or directory
 *
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpBeforeFullGC
 * -XX:HeapDumpPath=d:\heapdumpinstance.hprof
 *
 * @author shkstart
 * @create 10:16
 */
public class HeapDumpInstance {
    private static int _1MB = 1024 * 1024;
    byte[] buffer = new byte[10 * _1MB];

    public static void main(String[] args) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<HeapDumpInstance> list = new ArrayList<HeapDumpInstance>();
        for(int i = 0;i < 500;i++){
            list.add(new HeapDumpInstance());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("程序执行结束");
    }
}
