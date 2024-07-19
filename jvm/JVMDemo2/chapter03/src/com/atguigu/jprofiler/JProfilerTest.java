package com.atguigu.jprofiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 功能演示测试
 * @author shkstart
 * @create 12:19
 */
public class JProfilerTest {
    public static void main(String[] args) {
        while (true){
            // 这里每次while循环，旧的ArrayList对象都会被丢弃！
            // 在这个代码中，list 的旧实例被丢弃是因为：
            // list 是一个局部变量，它在每次循环中都被重新创建和赋值。
            // 旧的 ArrayList 实例在新 ArrayList 实例赋值后不再有活跃的引用。
            // Java 的垃圾回收器会检测到这些不再被引用的对象，并将它们标记为垃圾以便进行回收。
            ArrayList list = new ArrayList();

            for (int i = 0; i < 500; i++) {
                Data data = new Data();
                list.add(data);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /**
         * 每次循环时，`list` 的旧实例会被丢弃的原因是
         * 由于 Java 中的变量作用域和对象引用的机制。具体来说，这里有几个关键点：
         *
         * 1. 局部变量作用域
         *
         * 在 `while` 循环中，`list` 是一个局部变量。局部变量在每次循环
         * 迭代时都会被重新声明和初始化。每次迭代时，`list` 被分配一个
         * 新的 `ArrayList` 实例，而之前的 `ArrayList` 实例就不再被引用。
         *
         * 2. 对象引用
         *
         * 在每次循环开始时，`list` 被赋值为一个新的 `ArrayList` 对象。
         * 上一个循环中 `list` 引用的 `ArrayList` 实例在新的赋值操作后
         * 不再有任何活动引用。由于 `list` 是局部变量，旧的 `ArrayList`
         * 实例的引用在当前循环体内被丢弃。
         *
         * 3. 垃圾回收
         *
         * 当 `list` 引用的旧 `ArrayList` 实例没有其他引用指向它时，
         * 它就变成了垃圾对象。这意味着这个对象已经无法通过任何活跃的
         * 引用访问，因此垃圾回收器会在适当的时机将其标记为垃圾，并回收
         * 这块内存。
         *
         * 4. 新实例的创建
         *
         * 每次循环时，新创建的 `ArrayList` 实例会替代旧的 `ArrayList`
         * 实例的引用。由于 `list` 在每次循环时都被赋值为一个新的
         * `ArrayList`，之前的 `ArrayList` 实例在 `list` 重新指向新的对象后
         * 就失去了引用。
         *
         * 总结
         *
         * 在这个代码中，`list` 的旧实例被丢弃是因为：
         *
         * - `list` 是一个局部变量，它在每次循环中都被重新创建和赋值。
         * - 旧的 `ArrayList` 实例在新 `ArrayList` 实例赋值后不再有活跃的引用。
         * - Java 的垃圾回收器会检测到这些不再被引用的对象，并将它们
         *   标记为垃圾以便进行回收。
         */


    }
}
class Data{
    private int size = 10;
    private byte[] buffer = new byte[1024 * 1024];//1mb
    private String info = "hello,atguigu";
}