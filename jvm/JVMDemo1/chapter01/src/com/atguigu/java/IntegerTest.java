package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020-09-01 22:31
 */
public class IntegerTest {
    public static void main(String[] args) {

        Integer x = 5; // x 存的是一个引用类型的地址
        int y = 5; // y 存的是一个基本类型的数，数值
        System.out.println(x == y); //按说引用数据类型和基本数据类型不可以做等等比较的，这里使用/存在 自动拆箱的问题,在字节码层面调用的是`intValue`方法

        /**
         * 在Java标准库中，缓存的是 `-128` 到 `127` 之间的 `Integer` 对象，而不是单纯的数值。
         *
         * 工作原理：
         * - 当你通过任何方式创建一个 `Integer` 对象（例如通过 `Integer.valueOf()` 方法，或者自动装箱），
         *   如果该整数值在 `-128` 到 `127` 之间，Java不会创建一个新的 `Integer` 对象，
         *   而是直接从 `IntegerCache` 中返回对应的实例。
         * - 这种做法减少了对象的创建，从而优化了内存使用和提高了性能。
         *
         *
         * 在这个例子中，`i1` 和 `i2` 是同一个对象的引用，因为它们的值在缓存范围内。
         * 而 `i3` 和 `i4` 的值超出了缓存范围，所以每次调用 `Integer.valueOf(128)` 时都会创建一个新的 `Integer` 对象。
         */
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);//true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);//false

    }
}
