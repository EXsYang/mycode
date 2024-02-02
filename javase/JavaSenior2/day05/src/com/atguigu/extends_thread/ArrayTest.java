package com.atguigu.extends_thread;

/**
 * @author yangda
 * @create 2022-06-06-19:16
 */
public class ArrayTest {
    public static void main(String[] args) {
        String[] s = new String[]{"2","33","333","566"};

        s[1] = null;

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }


    }






}
