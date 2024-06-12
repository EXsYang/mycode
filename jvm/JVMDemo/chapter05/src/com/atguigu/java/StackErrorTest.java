package com.atguigu.java;

/**
 * 演示栈中的异常: StackOverflowError
 * - 默认情况下：count : 11420
 * - 设置栈的大小：-Xss256k : count : 2465
 *
 * 栈大小设置参考：
 * -Xss size
 * 设置线程栈大小（单位为字节）。可以使用 k 或 K 表示 KB，m 或 M 表示 MB，g 或 G 表示 GB。
 * 不同平台的默认值：
 * - Linux/x64 (64-bit): 1024 KB
 * - macOS (64-bit): 1024 KB
 * - Oracle Solaris/x64 (64-bit): 1024 KB
 * - Windows: 依赖于虚拟内存
 * 例子：
 * -Xss1m
 * -Xss1024k
 * -Xss1048576
 *
 * @link https://docs.oracle.com/en/java/javase/11/tools/java.html#GUID-3B1CE181-CD30-4178-9602-230B800D4FAE
 * 本选项类似于 -XX:ThreadStackSize
 *
 * @author shkstart
 * @create 2020 下午 9:08
 */
public class StackErrorTest {
    private static int count = 1;

    public static void main(String[] args) {
        // 以下代码是用于演示栈中的异常: StackOverflowError
        // 若要测试StackOverflowError，请取消以下注释，并确保没有任何终止递归的条件
        // System.out.println(count);
        // count++;
        // main(args);

        /**
         * 为什么调用 m1() 2亿次不会报 StackOverflowError？
         * - m1() 方法体为空，不涉及递归或深层次的方法调用，因此不会导致栈深度增加。
         * - 每次调用 m1() 完成后，栈帧被立即清除，栈的深度保持不变。
         * - 与递归调用不同，循环调用不会不断增加栈深度，因为控制权返回到循环而非方法自身。
         */
        for (int i = 0; i < 200000000; i++) {
            m1();
        }

        /**
         * 编译器优化可能会识别并移除对空方法的无效调用。
         * 若要确保调用发生，可在 m1() 中添加具有副作用的操作，例如打印消息。
         */
    }

    public static void m1(){
        // System.out.println("Called m1"); // 激活此行以观察调用效果
    }
}
