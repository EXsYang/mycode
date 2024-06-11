package com.atguigu.java1;

/**
 * @author shkstart
 * @create 2020 下午 10:25
 */
public class OperandStackTest {
    public void testAddOperation() {
        //byte、short、char、boolean：都以int型来保存
        byte i = 15;
        int j = 8;
        int k = i + j;

        // int m = 800;

    }

    public int getSum() {
        int m = 10;
        int n = 20;
        int k = m + n;
        return k;
    }

    public void testGetSum() {
        // 调用 getSum() 方法并获取返回值，返回值首先被压入当前栈帧的操作数栈顶
        // 然后将返回值从操作数栈顶存储到局部变量 i 中
        int i = getSum();
        // 将整数 10 直接存储到局部变量 j 中
        int j = 10;


        /*
        getSum() 的返回值是在 invokevirtual 指令执行完毕后自动压入操作数栈顶的。，这里也是先把它的返回值先压入到testGetSum()的操作数栈顶，然后在保存到testGetSum的局部变量表中的？

        是的，您的理解是正确的。在 testGetSum() 方法中调用 getSum()方法
        的过程，确实涉及到将 getSum() 的返回值先压入 testGetSum() 的
        操作数栈顶，然后再将这个值从操作数栈顶移动到 testGetSum() 的
        局部变量表中。这个过程可以详细解释如下：

        invokevirtual 指令
        当 invokevirtual 指令调用 getSum() 方法时，getSum() 方法执行
        并最终返回一个整数值。
        invokevirtual 指令完成执行后，getSum() 的返回值被自动压入调用
        它的方法（即 testGetSum()）的操作数栈顶。

        存储返回值
        紧接着，istore_1 指令用于从操作数栈顶取出这个返回值，
        并将其存储到 testGetSum() 方法的局部变量表中的
        指定索引（在这个例子中是索引1，对应变量 i）。

        字节码层面的说明
        当前方法testGetSum()反编译后的字节码指令以及详细解释如下:

         0 aload_0    // 加载this引用到操作数栈顶
         1 invokevirtual #2 <com/atguigu/java1/OperandStackTest.getSum> // 调用getSum()方法
         4 istore_1   // 将操作数栈顶的返回值存储在局部变量1中（即int i）
         5 bipush 10  // 将常量10压入操作数栈
         7 istore_2   // 将栈顶的10存储在局部变量2中（即int j）
         8 return     // 从方法返回

            总结
            invokevirtual 指令的执行结果（即 getSum() 的返回值）
            首先进入 testGetSum() 的操作数栈顶。
            这是一个典型的JVM的栈操作行为，
            确保方法间的数据传递和处理的一致性和安全性。
            这个返回值随后通过 istore 类指令移动到局部变量表，
            用于后续操作或保持状态。
            这种操作允许JVM维护严格的数据流控制和方法局部性，
            使得每个方法都可以作为一个独立的执行单元，
            同时支持数据在方法间的流动。

         */



        /*
        为什么下面这两行代码
        int i = getSum();
        int j = 10;
        反编译后的字节码指令的 Code length = 9 ?

        在Java字节码中，“code length”通常指的是字节码指令的总字节数，
        而不是指令的行数。每条字节码指令都有一个或多个字节组成，
        这些字节包括操作码本身及可能的操作数。

        描述Java字节码时确实更标准和清晰的方式是首先给出两位的16进制数，
        然后说明对应的十进制数。
        这种方式可以更直观地与字节码文档或工具中显示的信息相对应。
        我来重新给出那些指令的操作码和它们的字节表示：

        aload_0 - 操作码为 0x2A（十进制：42），占1个字节。
        invokevirtual - 操作码为 0xB6（十进制：182），后面跟两个字节的索引值，总共3个字节。
        istore_1 - 操作码为 0x3C（十进制：60），占1个字节。
        bipush - 操作码为 0x10（十进制：16），后面跟一个字节的立即数，总共2个字节。
        istore_2 - 操作码为 0x3D（十进制：61），占1个字节。
        return - 操作码为 0xB1（十进制：177），占1个字节。
        对应的字节总计是9字节。
         */
        // int i = getSum();
        // int j = 10;
    }

    /*
    程序员面试过程中， 常见的i++和++i 的区别，放到字节码篇章时再介绍。

     */
    public void add() {
        //第1类问题：
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        //第2类问题：
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        //第3类问题：
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        //第4类问题：
        int i9 = 10;
        int i10 = i9++ + ++i9;
    }
}
