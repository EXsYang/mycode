package com.atguigu.extends_thread;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author yangda
 * @create 2021-12-07-11:50
 */
public class StringByte {
    @Test
    public void test1() throws UnsupportedEncodingException {
        String str3 = "abcdef汉字";
        //字符串转换为byte[]
        //使用默认的字符集进行编码
        byte[] arr1 = str3.getBytes();//[97, 98, 99, 100, 101, 102, -26, -79, -119, -27, -83, -105]
        System.out.println(Arrays.toString(arr1));

        //重载方法
        //使用gbk字符集进行编码
        byte[] gbks = str3.getBytes("gbk");//这里有可能写错，写成"gbo"了,抛出一个异常UnsupportedEncodingException
        // If the named charset is not supported
        System.out.println(Arrays.toString(gbks));//[97, 98, 99, 100, 101, 102, -70, -70, -41, -42]
        //复习：
        char a = 'a';

        System.out.println(a+3);//a    字符类型直接打印是实体内容
        System.out.println(a+3);//a    字符类型直接打印是实体内容
        System.out.println(a+3);//a    字符类型直接打印是实体内容
        System.out.println(a+3);//a    字符类型直接打印是实体内容
        System.out.println(a+3);//a    字符类型直接打印是实体内容
        System.out.println(a+3);//a    字符类型直接打印是实体内容
        byte b = 127;
        //byte c = 128;报错 int类型
        byte c = (byte)128;
        byte d = (byte)129;

        System.out.println(b);//127
        System.out.println(c);//-128
        System.out.println(d);//-127
        System.out.println();

        //byte[]转换为字符串      调用String的构造器
        String str2 = new String(arr1);
        System.out.println(str2);

        //重载的构造器
        //offset:指定位置开始
        //length:取几个，[],两边都是闭区间
        String str4 = new String(arr1,6,3);//汉   默认编码汉字三个一位，取三个才能打印出"汉"
        System.out.println(str4);


    }
}
