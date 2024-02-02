package src.com.atguigu.java;


import org.junit.jupiter.api.Test;
import sun.management.Agent;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yangda
 * @create 2021-11-28-16:20
 */

public class StringTest {

    class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }
    }

    @Test
    public void test1() {
        String s11 = "ccc";
        String s22 = "aaa";
        String s33 = s11 + s22;
        String s5 = "cccaaa";
        System.out.println((s33 == s5));


//        String s2 = "ccc|cdd|dd|gg";//字面量
        int[] arr = new int[]{1, 3, 4, 6};
        String[] arr1 = new String[]{"1", "2", "2", "2"};
        System.out.println(arr);
        System.out.println(arr1.toString());


//        System.out.println(Arrays.toString(arr));
//        System.out.println(s2.split("\\|", 2).toString());//[Ljava.lang.String;@c46bcd4
//
//        System.out.println(s1 == s2);//true
//        s2 = "aaa";
//        System.out.println(s1 == s2);//false
//        s1 = s2;
//        System.out.println(s1 == s2);
//        System.out.println(s1);
//        System.out.println(s2);

        Person p1 = new Person("Tom", 12);

        String s3 = "beijingguanyingni";
        System.out.println(s3.substring(3));//jingguanyingni
        System.out.println(s3.substring(3, 6));//jin,左闭右开的


    }


    public void test3() {
        String s1 = "aaa";
        String s2 = "aaa";//字面量
        String s3 = "bbb";
        String s4 = "aaabbb";
        String s5 = s1 + s3;
        System.out.println(s1 == s2);//true
        s2 = "vvv";
        System.out.println(s1 == s2);//false
        s1 = s2;
        System.out.println(s1 == s2);//true

        System.out.println(s5 == s4);//false
        String s6 = "aaa" + "bbb";
        System.out.println(s5 == s6);//false

        System.out.println(s4 == s6);//true

        final String s7 = "abc";
        final String s8 = "def";
        final String s11 = new String("abc");//s11在堆空间中,"abc"在字符串常量池中
        final String s12 = new String("def");//s12在堆空间中
        String s13 = s11 + s12;
        String s10 = s7 + s8;
        String s9 = "abcdef";
        System.out.println(s9 == s10);//true
        System.out.println(s9 == s13);//false


    }


    public void test2() {

    }

    @Test
    public void test4() {


    }

    public void test5() {



        }


    }

    @Test
    public void test6() {//Calendar无论周几都能取到上周一
        String str1 = "abcwerthelloyuiodef";
        //字符串转换为char[]
        char[] arr1 = str1.toCharArray();
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
        System.out.println();
        //char[]转换为字符串
        String str2 = new String(arr1);
        System.out.println(str2);

}

    @Test
    public void test7() {//Calendar获取当前年的季度的第一天


    }
}


