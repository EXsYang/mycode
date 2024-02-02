package com.atguigu.bignum;

import java.math.BigInteger;

/**
 * @author yangda
 * @description:
 * @create 2022-11-11-15:23
 */
public class BigInteger_ {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("10");
//        BigInteger bigInteger2 = new BigInteger("43.33");//.NumberFormatException: For input string: "43.33"
        BigInteger bigInteger2 = new BigInteger("3");

        BigInteger add = bigInteger.add(bigInteger2);
        System.out.println(add);//344444444444444443333333333333337

        BigInteger subtract = bigInteger.subtract(bigInteger2);
        System.out.println(subtract);

        System.out.println(bigInteger.multiply(bigInteger2));

        System.out.println(bigInteger.divide(bigInteger2));//3
        System.out.println(bigInteger.divide(bigInteger2));//3
    }
}
