package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020 上午 11:43
 *
 * ### clinit()
 *
 * 1.  初始化阶段就是执行类构造器方法`<clinit>()`的过程
 *
 * 2.  此方法不需定义，是javac编译器自动收集类中的所有**类变量**的赋值动作和静态代码块中的语句合并而来。也就是说，当我们代码中包含static变量的时候，就会有clinit方法
 *
 * 3.  `<clinit>()`方法中的指令按语句在源文件中出现的顺序执行
 *
 * 4.  `<clinit>()`不同于类的构造器。（关联：构造器是虚拟机视角下的`<init>()`）
 *
 * 5.  若该类具有父类，JVM会保证子类的`<clinit>()`执行前，父类的`<clinit>()`已经执行完毕
 *
 * 6.  虚拟机必须保证一个类的`<clinit>()`方法在多线程下被同步加锁
 */
public class HelloApp2 {

    //只有下面这行代码时会生成clinit函数
    // private static int a = 1;//prepare：a = 0 ---> initial : a = 1

    private static final int a = 1;//只有这行代码时不会生成clinit函数


    // private static int b = 2;//只有这行代码时会生成clinit函数


    // static{
    //
    // }

    public static void main(String[] args) {
        // System.out.println(a);
        System.out.println("aaaa");
    }
}
