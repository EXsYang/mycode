package com.atguigu.exer;

import org.junit.Test;

/**
 * @author yangda
 * @create 2022-06-04-0:28
 */
public class ExerTest1 {
    // //1.模拟trim(), 去除两端的空格
    @Test
    public void test1() {
        /**
         * 这是字符串相关的方法
         * 找出字符串两端中的空格
         *
         *
         */
        String s = "  sfdasfda fdaf fdafd  a   ";

        //首先遍历一下，找出第一次非空格，最后一次非空格位置
//        for (int i = 0; i < s.length(); i++) {
////            if(s.charAt(i) == " "&&s.charAt(i+1) != " " ){
////
////            }
//
//
//        }
        int c = 3;
        char cc = 's';
        char ccc = 'a';
        String a = "a";
//        boolean b = a == ccc;//Operator '==' cannot be applied to 'java.lang.String', 'char'
//        System.out.println(b);


    }

    // //1.模拟trim(), 去除两端的空格
    @Test
    public void test2() {
        /**
         * 这是字符串相关的方法
         * 找出字符串两端中的空格
         *
         *
         */
        String s = "     sfdasfda fdaf fdafda  a   ";
        int start = 0;
        int end = 0 ;


        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(arr[i] == ' '&&arr[i+1] != ' '){
//                System.out.println(i+1);
                start = i+1;
                break;
            }
        }
        for (int i = arr.length-1; i >= 0; i--) {
            if(arr[i] == ' '&&arr[i-1] != ' '){
                System.out.println(i-1);
                end = i - 1;
                break;
            }
        }
        System.out.println(s.substring(start, end+1));//左闭右开的
        System.out.println(s.substring(7,11));//左闭右开的


    }
}