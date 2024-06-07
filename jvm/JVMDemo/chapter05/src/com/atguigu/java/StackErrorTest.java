package com.atguigu.java;

/**
 * 演示栈中的异常:StackOverflowError
 * @author shkstart
 * @create 2020 下午 9:08
 *
 *  默认情况下：count : 11420
 *  设置栈的大小： -Xss256k : count : 2465
 *
 */

/**
 * https://docs.oracle.com/en/java/javase/11/tools/java.html#GUID-3B1CE181-CD30-4178-9602-230B800D4FAE
 * 官方文档中对-Xss的解读
 * -Xss size
 * Sets the thread stack size (in bytes). Append the letter k or K to indicate KB, m or M to indicate MB, and g or G to indicate GB. The default value depends on the platform:
 *
 * Linux/x64 (64-bit): 1024 KB
 *
 * macOS (64-bit): 1024 KB
 *
 * Oracle Solaris/x64 (64-bit): 1024 KB
 *
 * Windows: The default value depends on virtual memory 【在Windows系统下线程栈的大小取决于虚拟机内存】
 *
 * The following examples set the thread stack size to 1024 KB in different units:
 *
 *
 * Copy
 * -Xss1m
 * -Xss1024k
 * -Xss1048576
 * This option is similar to -XX:ThreadStackSize.
 */
public class StackErrorTest {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }

}
