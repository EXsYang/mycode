package com.atguigu.java;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 * @author shkstart  shkstart@126.com
 * @create 2020  21:12
 */
/**
 * 问题1: 为什么在jvisualvm的抽样器-内存中，byte[]占用的最多99%？
 * 解释: 在Java中，对象与它们的实例变量（如数组）在内存中的占用是分开计算的。在这个例子中，每个Picture对象包含一个字节数组（byte[]）作为实例变量。
 * 因为数组`pixels`的大小是动态计算的，且可能非常大（每个数组大小在0到大约1MB之间变化），所以数组在内存中占用的比例远大于包含一个简单引用的Picture对象本身的占用。
 * 数组直接存储数据，而Picture对象只是包含了指向其字节数组的引用，所以内存分析显示byte[]的占用非常高。
 */

/**
 * 问题2: 创建一个数组就会开辟内存空间，即使不存放数据，也会占用内存？
 * 解释: 是的，在Java中创建任何数组（不论是基本类型还是对象数组）时，都会为该数组分配内存空间。这包括数组的管理信息（如数组长度、类型标识等）
 * 和足够的空间来存储它能容纳的所有元素。例如，创建一个int数组`int[] arr = new int[10];`即便元素未显式初始化，每个元素会自动初始化为0，
 * 并且数组会占用至少40字节的内存（每个int元素4字节）。数组的内存是即时分配的，大小固定，确保当数组元素需要使用时，相应的空间已经可用。
 */

public class OOMTest {
    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while(true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}

/**
 * Picture: 照片；电影；图画；描绘；相片；
 * 
 */
class Picture{
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
