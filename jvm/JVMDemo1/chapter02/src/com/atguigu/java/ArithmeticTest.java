package com.atguigu.java;

import org.junit.Test;

/**
 * @author shkstart
 * @create 2020-09-07 23:28
 * <p>
 * 指令2：算术指令
 *
 * -XX:+TraceClassLoading
 */
public class ArithmeticTest {
    @Test
    public void method1() {
        int i = 10;
        double j = i / 0.0;
        System.out.println(j);//无穷大 Infinity


        double d1 = 0.0;
        double d2 = d1 / 0.0;
        System.out.println(d2);//NaN: not a number
    }

    public void method2() {
        float i = 10;
        float j = -i;
        i = -j;
    }

    public void method3(int j) {
        int i = 100;
//        i = i + 10;
        i += 10;
    }

    public int method4() {
        int a = 80;
        int b = 7;
        int c = 10;
        return (a + b) * c;
    }

    public int method5(int i, int j) {
        return ((i + j - 1) & ~(j - 1));
    }

    //关于(前)++和(后)++
    public void method6() {
        int i = 10;

        /**
         * 当打开i++;时的字节码如下：
         *
         * 0 bipush 10
         * 2 istore_1
         * 3 iinc 1 by 1
         * 6 return
         */
        // i++;

        /**
         * 当打开++i;时的字节码如下：
         *
         * 0 bipush 10
         * 2 istore_1
         * 3 iinc 1 by 1
         * 6 return
         */
        ++i;

//        for(int j = 0;j < 10;j++){}
//        for(int j = 0;j < 10;++j){} //这里当j++或++j没有重新赋值给新的变量的时候，从字节码上来看都是一样的，运行效率没有任何区别！
    }


    /**
     * 前加加和后加加参与运算，即赋值给新的变量的情况如下：
     */
    public void method7() {
        int i = 10;
        int a = i++;

        int j = 20;
        int b = ++j;
    }

    //思考,i++面试题
    @Test
    public void method8() {
        int i = 10;
        i = i++;
        System.out.println(i);//10
    }
    //思考,++i面试题
    @Test
    public void method8_() {
        int i = 10;
        i = ++i;
        System.out.println(i);//11
    }

    @Test
    public void method9() {
        // long a = 0L;
        // long b = 0L;

        //float和double会出现NaN
        float a = 0.0f / 0.0f;
        System.out.println("a->  " + a); //  a->  NaN
        double b = 0.0 / 0.0;
        System.out.println("b->  " + b); // b->  NaN


        //long类型不会出现NaN,而是直接报算数异常！
        long num = 0L / 0L; //java.lang.ArithmeticException: / by zero

        System.out.println(num);//10
    }

}
