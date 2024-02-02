package com.atguigu.java2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yangda
 * @create 2022-06-05-19:02
 */
public class ComparatorTest {

    public static void main(String[] args) {
        String[] s = new String[]{"AA","BB","CC","WW","kk","SS"};
        Arrays.sort(s,new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String)o1;
                    String s2 = (String) o2;
                   return s1.compareTo(s2);
                }
//                return 0;
                throw new RuntimeException("输入的数据类型不一致！！");
            }
        });
        System.out.println(Arrays.toString(s));
    }

}
