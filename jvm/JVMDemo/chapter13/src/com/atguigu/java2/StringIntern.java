package com.atguigu.java2;

import org.junit.Test;

/**
 * 如何保证变量s指向的是字符串常量池中的数据呢？
 * 有两种方式：
 * 方式一： String s = "shkstart";//字面量定义的方式
 * 方式二： 调用intern()
 *         String s = new String("shkstart").intern();
 *         String s = new StringBuilder("shkstart").toString().intern();
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  18:49
 */
public class StringIntern {
    public static void main(String[] args) {

        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了"1"
        String s2 = "1";
        System.out.println(s == s2);//jdk6：false   jdk7/8：false


        String s3 = new String("1") + new String("1");//s3变量记录的地址为：new String("11"), 即通过StringBuilder的toString()方法创建的new String("11")
        //执行完上一行代码以后，字符串常量池中，是否存在"11"呢？答案：不存在！！
        s3.intern();//在字符串常量池中生成"11"。如何理解：jdk6:创建了一个新的对象"11",也就有新的地址。
                                            //         jdk7/8:此时常量中并没有创建"11",而是创建一个指向堆空间中new String("11")的地址
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码代码执行时，在常量池中生成的"11"的地址。
        // 在jdk6中,这里到常量池中去找是否有"11", 找到的是通过`s3.intern();`创建了一个新的字符串常量池中的对象"11",也就有新的地址。
        // 在jdk7/8中,这里到字符串常量池中去找是否有"11",结果找到是通过`s3.intern();`创建了一个指向堆空间中new String("11")的地址。
        // 也就是说“String s4 = "11";” 并不一定都是指向字符串常量池中的字符串"11",也有可能是一个指向堆空间中new String("11")的地址

        // 关键点:jdk7、8中的变化
        // ①  s3.intern();, 如果常量池当中没有"11",则在常量池当中放入一个指向堆空间中的new String("11")的引用地址,
        //    如果常量池当中有"11",则就会直接返回常量池中"11"的地址，而不再返回一个指向堆空间中的new String("11")的引用地址,因为没有必要放一个堆空间的new String("11")引用地址了
        // ②  String s4 = "11";到字符串常量池中找"11",但是此时常量池中并没有"11",而在堆中的一个对象new String("11")中有"11"
        //    ,s4指向堆空间中的new String("11")对象
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }


}
