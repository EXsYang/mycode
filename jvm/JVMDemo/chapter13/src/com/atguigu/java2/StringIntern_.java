package com.atguigu.java2;

/**
 * 如何确保变量s指向字符串常量池中的数据？
 * 有两种方式：
 * 方式一：String s = "shkstart"; // 直接使用字符串字面量的方式
 * 方式二：使用intern()方法
 *        String s = new String("shkstart").intern();
 *        String s = new StringBuilder("shkstart").toString().intern();
 *
 *
 * @author yangda
 * @create 2024-06-26-12:57
 * @description:
 */
public class StringIntern_ {
    public static void main(String[] args) {

        // 示例1：验证字符串字面量和通过new创建的字符串
        String s = new String("1");
        s.intern(); // 调用此方法之前，字符串常量池中已存在"1"
        String s2 = "1";
        System.out.println(s == s2); // JDK6：false   JDK7/8：false

        // 示例2：验证通过new String()创建的字符串是否在常量池中
        String s3 = new String("1") + new String("1"); // s3引用一个新的String对象"11"，通过StringBuilder的toString()方法创建
        // 字符串常量池中不存在"11"
        s3.intern(); // 在字符串常量池中加入"11"。不同JDK版本的行为：
        // JDK6：在常量池中创建一个新的"11"对象，有新的地址。
        // JDK7/8：常量池中没有创建新的"11"对象，而是创建一个指向堆中new String("11")的引用。
        String s4 = "11"; // s4引用常量池中的"11"。
        // JDK6：在常量池中查找"11"，找到通过`s3.intern()`新创建的对象。
        // JDK7/8：在常量池中查找"11"，找到的是指向堆中new String("11")的引用。
        // 也就是说“String s4 = "11";” 不一定指向常量池中的"11"，也可能是指向堆中的new String("11")。

        // 关键点: JDK7/8中的变化
        // ① s3.intern(); 如果常量池中没有"11"，则放入一个指向堆中new String("11")的引用，
        //    如果常量池中已有"11"，则直接返回常量池中的"11"地址，不再放入新的引用。
        // ② String s4 = "11"; 查找常量池中的"11"，但常量池中没有独立的"11"，而是引用堆中的new String("11")对象。
        System.out.println(s3 == s4); // JDK6：false  JDK7/8：true
    }
}
