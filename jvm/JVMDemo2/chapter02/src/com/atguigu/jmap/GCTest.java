package com.atguigu.jmap;

import java.util.ArrayList;

/**
 * @author shkstart
 * @create 17:49
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8
 *
 *
 * 自动的方式【即发生OOM时自动生成dump文件】生成dump文件的jvm参数是
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=<filename.hprof>
 *
 * -XX:HeapDumpPath=<filename.hprof> : 指定生成dump文件的路径，如果没指定生成dump文件的路径则生成在当前路径下。
 *
 * 另一个自动生成dump文件的jvm参数是：-XX:HeapDumpBeforeFullGC ,即发生FullGC之前生成dump文件。
 * 也需要搭配 -XX:HeapDumpPath=<filename.hprof> 来使用。
 * 如果没指定生成dump的路径则生成在当前路径下。
 *
 *
 *  -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:\aatest\testautodump.hprof
 *
 */
public class GCTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024 * 100];//100KB
            list.add(arr);
            try {
                Thread.sleep(1660);
                // Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
