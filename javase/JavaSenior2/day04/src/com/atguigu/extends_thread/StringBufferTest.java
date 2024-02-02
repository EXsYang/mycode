package com.atguigu.extends_thread;

/**
 * @author yangda
 * @create 2021-12-08-13:54
 */
public class StringBufferTest {
    public static void main(String[] args) {

        String str1 = new String("abc");////char[] value = new char[]{'a','b','c'};
        StringBuffer sb1 = new StringBuffer("abc");//     super(capacity:16);    value = new char[capacity];

        //底层创建了一个长度16的char型数组   char[] value = new char[capacity];
        System.out.println(sb1.length());//0    return count;

        sb1.append('a');
        sb1.append('b');
        sb1.append("c").append("v").append("b").append("v");//通过StringBuffer对象调用append(),
        // 返回值是一个StringBuffer类型的，相当于还是一个对象，可以继续调用append(),支持方法链操作
        System.out.println(sb1.length());//2   return count;

        StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];
        sb2.delete(1,2);
        System.out.println(sb2);//ac    左闭右开的







    }




}
