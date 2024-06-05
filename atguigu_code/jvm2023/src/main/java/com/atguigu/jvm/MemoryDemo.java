package com.atguigu.jvm;

public class MemoryDemo {
    public static void main(String[] args) {
        //-Xmx50m -Xms30m -XX:+PrintGCDetails
        System.out.print("最大堆大小：");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.print("当前堆大小：");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
        System.out.println("==================================================");

    }
}
