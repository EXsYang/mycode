package com.hsp.thread;

/**
 * @author yangda
 * @description: 用代码查看计算机核心数，几核的
 * @create 2022-11-21-17:28
 */
public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        int i = runtime.availableProcessors();

        System.out.println("电脑核心数：" + i);//电脑核心数：8
    }
}
