package com.atguigu.java2;

/**
 * 题目：
 * new String("ab")会创建几个对象？看字节码，就知道是两个。
 *     一个对象是：new关键字在堆空间创建的
 *     另一个对象是：字符串常量池中的对象"ab"。 字节码指令：ldc
 *
 *
 * 思考：
 * new String("a") + new String("b")呢？
 *  对象1：new StringBuilder()
 *  对象2： new String("a")
 *  对象3： 常量池中的"a"
 *  对象4： new String("b")
 *  对象5： 常量池中的"b"
 *
 *  深入剖析： StringBuilder的toString():
 *      对象6 ：new String("ab")
 *       强调一下，toString()的调用，在字符串常量池中，没有生成"ab"   【没有看到 `ldc <ab>` ,即在StringNewTest.class的字节码指令和StringBulider的toString()方法中都没有看到 `ldc <ab>` 】
 *
 *
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  20:38
 */
public class StringNewTest {
    public static void main(String[] args) {
       // String str = new String("ab"); //字符串常量池中在编译期就有字符串"ab",可以通过反编译指令`javap -v StringNewTest.class`查看编译后的常量池验证

        String str = new String("a") + new String("b"); //字符串常量池中在编译期就有字符串 "a" 和 "b"

        // 在Java中，异常分为两大类：检查型异常（checked exceptions）和非检查型异常（unchecked exceptions）。这两种异常的主要区别在于编译器如何处理它们：
        // throw new NullPointerException(); //非检查型异常：这些异常包括 RuntimeException 及其子类和 Error 及其子类。它们通常不需要在方法声明中用 throws 显式声明，也不需要强制捕获。这些异常通常是由程序逻辑错误引起的，如空指针异常（NullPointerException）、数组越界异常等。
        // throw new Exception(); //检查型异常：这些异常必须在方法中被显式地捕获处理（try-catch），或者在方法声明中通过 throws 关键字声明。这些异常通常代表外部错误，如 I/O 错误或网络问题，且程序应当能够从这些异常中恢复。Exception 类及其子类（除了 RuntimeException 及其子类）属于检查型异常。
    }
}
