package com.atguigu.extends_thread;

import org.junit.Test;

/**
 * @author yangda
 * @create 2021-12-07-9:59
 */
public class StringChar {
    @Test
    public void test1(){
        String str1 = "abcwerthelloyuiodef";
        String str3 = "String";
        //字符串转换为char[]
        char[] arr1 = str3.toCharArray();
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]);
        }
        System.out.println();
        //char[]转换为字符串
        String str2 = new String(arr1);
        System.out.println(str2);
        System.out.println();

        Character b ;
        char a = 'w';//char点不进去！！char的toString()在Character包装类中可以找到定义的属性value
        //基本数据类型 和 关键字, 都是在 jvm 里定义的
        //想看的话, 去找 开源的 jdk
        //需要 C++ 支持

         /*getChars() 方法将字符从字符串复制到目标字符数组。
        srcBegin -- 字符串中要复制的第一个字符的索引。
        srcEnd -- 字符串中要复制的最后一个字符之后的索引。
        dst -- 目标数组。
        dstBegin -- 目标数组中的起始偏移量。
        没有返回值，但会抛出 IndexOutOfBoundsException 异常。*/
        String str4 = "StRinG";
        char[] arr2 = new char[6];
        str4.getChars(1,6,arr2,0);//左闭右开的
        System.out.println(arr2);//tR  char[]数组可以直接打印出来

        String[] strs = new String[]{"1","3","4"};
        System.out.println(strs);//[Ljava.lang.String;@4ee285c6  String类型数组直接打印是地址值

        int[] ints = new int[]{1,2,3};
        System.out.println(ints);//[I@621be5d1  int类型数组直接打印是地址值
    }
    @Test
    public void test2(){
        /*
        关于数组输出的测试
        char[]直接输出时，各字符会拼接成字符串输出。
        char[]拼接字符串后再输出，则输出数组的地址值。
        引用数据类型输出类名+地址值
        其余基本数据类型输出地址值
        综上所述，char[]需要特别注意下
         */
        char c[] = {'c','h','i','n','a'};
        Character ch[] = {'c','h','i','n','a'};
        String s[] = {"ch","ina"};
        StringChar stringchar[] = {};
        int i[] = {1,2,3};
        double d[] = {2.2,3.3};
        boolean bool[] = {true,false};

        System.out.println("char数组输出：");
        System.out.print("直接输出：");
        System.out.print(c);
        System.out.print("\n拼接字符串输出：");
        System.out.print("char[] = "+c+"\n");

        System.out.println("************************************************************");

        System.out.println("引用数据类型数组输出：");

        System.out.println("直接输出：");
        System.out.println(ch);
        System.out.println(s);
        System.out.println(stringchar);

        System.out.println("拼接字符串输出：");
        System.out.println("Character[]："+ch);
        System.out.println("String[]："+s);
        System.out.println("StringChar[]："+stringchar);
        System.out.println("************************************************************");

        System.out.println("其它基本数据类型数组输出：");

        System.out.println("直接输出：");
        System.out.println(i);
        System.out.println(d);
        System.out.println(bool);

        System.out.println("拼接字符串输出：");
        System.out.println("int[]："+i);
        System.out.println("double[]："+d);
        System.out.println("boolean[]："+bool);
    }

@Test
    public void test3(){
    String str1 = "abcwerthelloyuiodef";
    String str2 = "cvhellobnm";
    char[] maxchar1 = new char[10];
    str2.getChars(0,11,maxchar1,0);
    System.out.println(maxchar1);
}

}
