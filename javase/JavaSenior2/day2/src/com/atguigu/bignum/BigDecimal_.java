package com.atguigu.bignum;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description:
 * @create 2022-11-11-15:23
 */
public class BigDecimal_ {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("22222222222.333333333333444");
        BigDecimal bigDecimal1 = new BigDecimal("33.33");

//        System.out.println(bigDecimal.divide(bigDecimal1));//除不尽，不会停止，抛异常：ArithmeticException: Non-terminating decimal expansion; no exact representable decimal
        System.out.println(bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_CEILING));//666733340.004000400040008
                                                        //小数点后保留和分子相同的位数


    }
}
