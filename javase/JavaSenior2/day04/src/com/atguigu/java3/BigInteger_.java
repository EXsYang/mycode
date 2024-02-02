package com.atguigu.java3;

import java.math.BigInteger;

/**
 * @author yangda
 * @create 2022-06-06-1:58
 */
public class BigInteger_ {
    public static void main(String[] args) {
        //当我们编程中，需要处理很大的整数，long不够用
        //可以用BigInteger的类来搞定
//        long l = 355454333333333333335555555555555l;
//        System.out.println("l="+l);

        BigInteger bigInteger = new BigInteger("3");
        BigInteger bigInteger1 = new BigInteger("10");
        System.out.println(bigInteger);

        //1.在对BigInteger进行加减乘除的时候，需要使用对应的方法，不能直接+ - * /
        //2.可以创建一个 要操作的BigInteger 然后进行操作
        BigInteger add = bigInteger.add(bigInteger1);
        System.out.println("add:"+ add);

        BigInteger subtract = bigInteger.subtract(bigInteger1);
        System.out.println("subtract:"+subtract);//减

        BigInteger multiply = bigInteger.multiply(bigInteger1);
        System.out.println("multiply:"+ multiply);//乘

        BigInteger divide = bigInteger1.divide(bigInteger);//除
        System.out.println("divide:"+divide);//divide:3  这里截断了









    }
}
