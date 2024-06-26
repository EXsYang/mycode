package com.atguigu.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 *  -XX:StringTableSize=1009
 * @author shkstart  shkstart@126.com
 * @create 2020  23:53
 */
/**
 * 字符串常量池中是不会存储相同内容的字符串的。
 *
 * String的String Pool是一个固定大小的Hashtable，默认值大小长度是1009。
 * 如果放进String Pool的String非常多，就会造成Hash冲突严重，
 * 从而导致链表会很长，而链表长了后直接会造成的影响就是当调用
 * String.intern时性能会大幅下降。
 *
 * 使用-XX:StringTableSize可设置StringTable的长度。
 *
 * 在JDK 6中，StringTable的默认长度是1009，如果常量池中的
 * 字符串过多就会导致效率下降很快。可以使用StringTableSize参数来修改默认长度。
 * StringTableSize设置没有要求。【即使设置为非常小 不报错,如:-XX:StringTableSize=10】
 *
 * 在JDK7中，StringTable的长度默认值是60013，1009是可设置的最小值。【即使设置为非常小 不报错,如:-XX:StringTableSize=10】
 *
 * JDK8开始，StringTable的长度默认值是60013，设置StringTable的长度的话，1009是可设置的最小值。【设置的非常小 会报错,如:-XX:StringTableSize=10】
 * Error: Could not create the Java Virtual Machine.
 * Error: A fatal exception has occurred. Program will exit.
 * StringTable size of 10 is invalid; must be between 1009 and 2305843009213693951
 */
public class StringTest2 {
    public static void main(String[] args) {
        //测试StringTableSize参数
        // System.out.println("我来打个酱油");
        // try {
        //     Thread.sleep(1000000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        //-XX:StringTableSize=10
        //测试StringTableSize对性能的影响
        /**
         * StringTable 的底层结构
         * StringTable 的底层是一个哈希表（HashTable）。
         * 每个位置可能包含一个链表，用于解决哈希冲突问题。
         * 哈希表是通过哈希函数将字符串映射到特定的桶（bucket）中，每个桶是一个链表。
         *
         * intern() 方法的查找和插入过程
         * String.intern() 方法的作用是确保字符串在常量池中只有一个实例。
         * 如果常量池中已经存在相等的字符串，则返回该字符串的引用；
         * 如果不存在，则将该字符串添加到常量池中，并返回其引用。
         *
         * 内存结构图示
         * 假设字符串 "example" 的哈希值为 1122，哈希表大小为 1009：
         *
         * StringTable (哈希表)
         * +-------------------------+
         * | 0  | 1  | 2  | ... | 1008 |
         * +-------------------------+
         *   |
         *   v
         * 链表
         * Node("example") --> Node("anotherExample") --> null
         *
         *
         *
         *
         * 性能检测原因
         * 通过 data.intern() 方法，可以检测操作 StringTable 的性能，原因如下：
         *
         * 查找过程性能：
         *
         * intern() 方法需要在 StringTable 中查找字符串。如果哈希冲突严重（例如 StringTable 大小太小），
         * 链表长度变长，查找时间会显著增加。
         * 插入过程性能：
         *
         * 如果 StringTable 中没有找到该字符串，则需要将其插入。插入操作同样会受到哈希冲突的影响，
         * 哈希冲突严重时插入时间会显著增加。
         * 高频操作：
         *
         * 代码中，data.intern() 方法被频繁调用，每次调用都会进行查找和可能的插入操作，
         * 这样可以放大哈希冲突对性能的影响。
         * 通过调整 StringTable 的大小，可以观察到以下性能差异：
         *
         * -XX:StringTableSize=1009：
         *
         * 小的 StringTable 大小（1009）导致较多的哈希冲突，从而使 intern 操作的时间较长。
         * 输出示例：花费的时间为：145ms
         * -XX:StringTableSize=100009：
         *
         * 较大的 StringTable 大小（100009）减少了哈希冲突，从而使 intern 操作的时间较短。
         * 输出示例：花费的时间为：45ms
         * 结论
         * 通过 data.intern() 方法，可以检测和比较不同 StringTable 大小对操作性能的影响。
         * 由于 intern 操作直接与 StringTable 的查找和插入操作相关，
         * 因此能够明显反映出 StringTable 大小对性能的影响。较大的 StringTable 能减少哈希冲突，
         * 从而提高性能，而较小的 StringTable 则会增加哈希冲突，降低性能。
         */
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("words.txt"));
            long start = System.currentTimeMillis();
            String data;
            while((data = br.readLine()) != null){
                // 通过调用 intern() 方法，检测操作 StringTable 的性能
                //性能检测：
                // 通过调整 StringTable 的大小，可以观察到 intern 操作的性能差异。
                // 小的 StringTable：较多的哈希冲突导致链表长度增加，查找和插入时间增加，从而降低性能。
                // 大的 StringTable：较少的哈希冲突导致链表长度减少，查找和插入时间减少，从而提高性能。
                // 这段代码通过读取文件 words.txt 中的每一行字符串，并调用 data.intern() 方法，
                // 将字符串插入到常量池中，从而检测和比较不同 StringTable 大小对性能的影响。

                // 查找过程：
                // 1. 计算字符串的哈希值
                // 2. 使用哈希值在 StringTable 中查找对应的位置
                // 3. 遍历链表，比较字符串是否相等
                // 插入过程：
                // 1. 如果没有找到相等的字符串，则创建新节点表示该字符串
                // 2. 将新节点插入到链表的头部（或尾部）
                data.intern(); // 如果字符串常量池中没有对应 data 的字符串，则在常量池中生成
            }

            long end = System.currentTimeMillis();

            // 打印花费的时间以检测 StringTable 的性能
            System.out.println("花费的时间为：" + (end - start));// 1009:145ms  100009:45ms
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
