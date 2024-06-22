package com.atguigu.java1;

import java.lang.reflect.Constructor;

/**
 * 《深入理解Java虚拟机》中的案例：
 * staticObj、instanceObj、localObj存放在哪里？
 * @author shkstart  shkstart@126.com
 * @create 2020  11:39
 */
public class StaticObjTest {
    static class Test {
        /**
         * 果然找到了一个引用该对象的地方，是在一个java.lang.Class的实例里，并且给出了这个实例的地
         * 址，通过Inspector查看该对象实例，可以清楚看到这确实是一个java.lang.Class类型的对象实例，里面
         * 有一个名为staticObj的实例字段
         *
         * 从《Java虚拟机规范》所定义的概念模型来看，所有Class相关的信息都应该存放在方法区之中，
         * 但方法区该如何实现，《Java虚拟机规范》并未做出规定，这就成了一件允许不同虚拟机自己灵活把
         * 握的事情。JDK 7及其以后版本的HotSpot虚拟机选择把静态变量与类型在Java语言一端的映射Class对
         * 象存放在一起，存储于Java堆之中
         */
        //静态变量staticObj 是和大的Class实例对象存放在一起的,
        // 因为大的Class实例对象是存放在堆Heap当中的,
        // 因此静态变量staticObj也是存放在堆空间当中的
        static ObjectHolder staticObj = new ObjectHolder();

        //成员变量instanceObj 是和Test对象实例关联的,存放在堆当中
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            //局部变量 localObj 是存放在foo()方法栈帧的局部变量表中的
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");

        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new StaticObjTest.Test();
        test.foo();
    }
}
