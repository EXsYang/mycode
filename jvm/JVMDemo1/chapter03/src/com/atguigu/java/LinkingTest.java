package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020-09-13 10:50
 * <p>
 * 过程二：链接阶段
 * <p>
 * 基本数据类型：非final修饰的变量，在准备环节进行默认初始化赋值。
 * final修饰以后，在准备环节直接进行显示赋值。
 * <p>
 * 拓展：如果使用字面量的方式定义一个字符串的常量的话，也是在准备环节直接进行显示赋值。
 */
public class LinkingTest {
    private static long id;//
    private static final int num = 1; // 在字节码文件中会有一个`ConstantValue`属性来保存字面量数值`1`

    public static final String constStr = "CONST"; // 在字节码文件中会有一个`ConstantValue`属性来保存字面量 `CONST`

    public static final String constStr1 = new String("CONST");
    // 在字节码文件中没有`ConstantValue`属性来保存字面量 `CONST`,而是在<clinit>中进行赋值的
    //  0 new #5 <java/lang/String>
    //  3 dup
    //  4 ldc #6 <CONST>  // 这个和上面constStr使用的是同一个字符串常量池中的数据`CONST`
    //  6 invokespecial #7 <java/lang/String.<init>>
    //  9 putstatic #8 <com/atguigu/java/LinkingTest.constStr1>
    // 12 return

    public Object getObj() {
        return null;
    }

    public void print1() {
        System.out.println("hello");
    }
}
