package com.atguigu.java3;

import com.sun.org.apache.bcel.internal.generic.D2F;

import java.math.BigDecimal;

/**
 * @author yangda
 * @create 2022-06-06-2:27
 */
public class BigDecimal_ {
    public static void main(String[] args) {
        //当我们需要保存一个精度很高的数时，double不够用
        //可以是 BigDecimal

        double d = 33334.5555555555555555533333333;//d 可加可不加，表明是double类型
        System.out.println(d);//33334.555555555555

        BigDecimal bigDecimal = new BigDecimal("10");
        BigDecimal bigDecimal1 = new BigDecimal("3");
        System.out.println(bigDecimal);

        //1.如果对 BigDecimal进行运算 比如加减乘除 需要使用对应的方法
        //2.创建一个需要操作的BigDecimal 然后调用相应的方法即可
        System.out.println(bigDecimal.add(bigDecimal1));
        System.out.println(bigDecimal.subtract(bigDecimal1));
        System.out.println("multiply:"+bigDecimal.multiply(bigDecimal1));//乘

//        System.out.println(bigDecimal.divide(bigDecimal1));//除，出现ArithmeticException，
        // 在调用divide()时，指定精度即可，BigDecimal.ROUND_CEILING

        System.out.println(bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_CEILING));//除，四舍五入模式四舍五入到正无穷。
        //4
        System.out.println(bigDecimal.divide(bigDecimal1,25,BigDecimal.ROUND_HALF_UP));//除，ROUND_HALF_UP四舍五入
        //3.3333333333333333333333333


    }
}
