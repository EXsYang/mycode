package com.atguigu.java2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author yangda
 * @create 2024-03-02-14:07
 * @description:
 */
public class ComparatorTest2 {
    public static void main(String[] args) {

        List numbers = Arrays.asList(1, 2, -5, 77, 22, 9);

        // 默认从小到大排序的规则为:
        // 前面的数比后面的数大，返回正数1，前面的数比后面的数小，
        // 返回负数-1,相等返回0, 此规则下默认从小到大排序

        //测试如下：
        numbers.stream().sorted((num1,num2) -> {
            if ((Integer)num1 > (Integer)num2){
                return 1;
            }else if ((Integer)num1 == (Integer)num2){
                return 0;
            }else {
                return -1;
            }
        }).forEach(System.out::println);




    }
}
