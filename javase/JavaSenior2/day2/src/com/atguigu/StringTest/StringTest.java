package com.atguigu.StringTest;

import java.lang.String;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author yangda
 * @description: String、StringBuffer、StringBuilder方法的测试，以及自己做的五道String相关的算法题
 * @create 2022-11-09-13:09
 */
public class StringTest {
    public static void main(String[] args) {

//        1）模拟一个trim方法，去除字符串两端的空格。
//
//        2）将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
//
//        3）获取一个字符串在另一个字符串中出现的次数。
//        比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
//
//        4）获取两个字符串中最大相同子串。比如：
//        str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
//        提示：将短的那个串进行长度依次递减的子串与较长的串比较。
//
//        5）对字符串中字符进行自然顺序排序。
//        提示：
//        1字符串变成字符数组。
//        2对数组排序，择，冒泡，Arrays.sort();
//        3将排序后的数组变成字符串。








    }

    @Test
    public void trimTest(){
        //去除两端的空格 "  fdsfsfeeww  dsa fef   "
        //String类型是length()方法，而不是length属性
        //模拟一个trim方法，
        String a = "  fdsfsfeeww  dsa fef   ";

        char[] a1 = a.toCharArray();
        int beginIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < a1.length; i++) {
            if(a1[i] != ' '){
                beginIndex = i;
                break;
            }
        }
        for (int i = a1.length - 1; i >= 0; i--) {
            if(a1[i] != ' '){
                endIndex = i;
                break;
            }
        }
        String substring = a.substring(beginIndex, endIndex);
        System.out.println("|" + substring + "|");

    }
@Test
public void testReversal(){
//    2）将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
    String s = "abcdefg";   //2，5
    String substring = s.substring(2, 6);//左闭右开的
    char[] chars = substring.toCharArray();
    for (int i = 0; i < chars.length / 2; i++) {
        char temp = chars[i];
        chars[i] = chars[chars.length - 1 - i];
        chars[chars.length - 1 - i] = temp;
    }
    String reversal = new String(chars);
    System.out.println(reversal);
    String replace = s.replace(substring, reversal);
    System.out.println("反转之后的字符串是：" + replace);

}
@Test
public void testsubNum(){

    System.out.println("\"\".endsWith(\"\")= "+"".endsWith("")); // "".endsWith("")= true


    int count = 0;
//        3）获取一个字符串在另一个字符串中出现的次数。
//        比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
    String a = "abkkcadkabkebfkabkskab";
    String sub = "ab";
    //int first = a.indexOf("abss");//找不到返回 -1
    int first = a.indexOf("ab");//找到计数器加一
    int last = a.lastIndexOf("ab");
    System.out.println("first= " + first);
    System.out.println(last);
    System.out.println("字符串a的长度为：" + a.length());

    if (last >= 0) {

        int temp = 0;//定义临时变量
        temp = a.indexOf("ab");//第一次找到
        while (temp >= 0) {
//            String substring = a.substring(temp + 2);//创建新的子字符串，为找到ab后面的字符串
            temp = a.indexOf("ab", temp + 2);//按照相同的规则继续找,将子串中找到的新的索引值重新赋给temp
            //在a字符串中，找"ab",从上一次找到的位置加2，的位置开始
            //新的子字符串中索引位置已经改变了，不能按照之前的索引继续找，但是可以去原字符串中，按照这个索引去找
            System.out.println(temp);
            count++;

        }
        System.out.println("一共出现了" + ++count + "次");
    }
    }

    @Test
    public void maxSum(){
//     4）获取两个字符串中最大相同子串。比如：
////        str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
////        提示：将短的那个串进行长度依次递减的子串与较长的串比较。
        //去除str2中的字符去str1中一个一个去找，找到后放在一边，如果再次找到，比较length
        // 最后保留最长的字符串
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        char[] chars = str2.toCharArray();
        String temp;
        int l = 0 ;
        int index = 0;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length - i; j++) {
                String sub = str2.substring(j, chars.length-i);

                if(str1.contains(sub)){//找出所有包含的相同子串

//                    System.out.println(sub);
                    int length = sub.length();//记录相同子串长度
                    if (length > l){//
                        l = length;
//                        System.out.println(l);
//                      //记录，子串的角标，按照长度取length个
//                        System.out.println(sub);
                        int i1 = str1.indexOf(sub);
                        index = i1;

                    }
                }
//                System.out.println(sub);
            }
        }
        String maxsub = str1.substring(index, index + l);
        System.out.println(maxsub);

    }
 @Test
public void testSort(){
//     5）对字符串中字符进行自然顺序排序。
//        提示：
//        1字符串变成字符数组。
//        2对数组排序，择，冒泡，Arrays.sort();
//        3将排序后的数组变成字符串。

     String str1 = "abcwerthelloyuiodef";
     char[] chars = str1.toCharArray();
     Arrays.sort(chars);//排完序后，原本的chars数组被改变了！！！
     String s = new String(chars);
     System.out.println(s);







 }
    @Test
    public void testString(){
        String string = new String();
//        string.reverse();    没有反转方法
        String main = "1231232562728229";
        System.out.println(main.indexOf("2", 4));//包含开始找的索引4，从4号索引位置开始找


    }
 @Test
public void testStringBuffer(){
    StringBuffer stringBuffer = new StringBuffer();

     String yangda = new String("yangda");

     StringBuffer stringBuffer1 = new StringBuffer(yangda);
     StringBuilder stringBuilder1 = new StringBuilder(yangda);

     String s3 = new String(stringBuffer1);
     String s4 = new String(stringBuilder1);//三者之间的转换调用构造器
     StringBuilder stringBuilder2 = new StringBuilder(stringBuffer1);
     StringBuffer stringBuffer2 = new StringBuffer(stringBuilder1);
     StringBuffer stringBuffer3 = new StringBuffer(s3);
     StringBuilder stringBuilder3 = new StringBuilder(s3);


     int[]  i = new int[5];
     i.toString();//基本数据类型的数组也有
     String s2 = stringBuffer.toString();//返回的是一个String类型

     String[] s11 = new String[5];
     s11[1] = "dssss";
     s11[2] = "33fdsf";

     int[]  ints = new int[4];
     ints[1] = 2;

     char[] chars = new char[4];
     chars[0] = 'w';
     chars[1] = 'w';
     chars[2] = 'w';
     chars[2] = 'w';//不会报错
     chars[3] = 'w';
     System.out.println(ints);//[I@621be5d1
     System.out.println("ints:" + ints);//ints:[I@621be5d1
     System.out.println(chars);//直接打印，显示的是数组内容 wwww
     System.out.println("chars:" + chars);//有连接操作的，打印的是 chars:[C@573fd745
     System.out.println(s11);//String直接打印，是地址值
     System.out.println("String[] s:" + s11);//String[] s:[Ljava.lang.String;@15327b79
     System.out.println(Arrays.toString(ints));//[0, 2, 0, 0]   有默认值
     //除了char[] 数组之外，其他基本数据类型数组直接sout,是地址值

//     为什么char类型数组打印出的是内容，而其他数组打印出的是地址值？
//     char类型的数组就相当于一个字符串。
//     因为输出流System.out是PrintStream对象，PrintStream有多个重载的println方法，
//     其中一个就是public void println(char[] x)，直接打印字符数组的话，不像int[]等其他数组，
//     它会直接调用这个方法来打印，因而可以打印出数组内容，而不是地址。




     stringBuffer.append("北京附件是佛山警方");
     stringBuffer.append('a');
     stringBuffer.append(false);
     stringBuffer.append(3);
     char c = stringBuffer.charAt(2);//返回指定位置字符
     stringBuffer.delete(1,2);//左闭右开的
     System.out.println("删除" + stringBuffer);
     stringBuffer.setCharAt(0,'改');
     stringBuffer.charAt(0);
     stringBuffer.insert(0,"插");
     System.out.println(stringBuffer);

     StringBuilder stringBuilder = new StringBuilder("杨达");

     stringBuilder.reverse();//反转字符串
     String string = new String(stringBuilder);
     System.out.println("空的；" + string);

     String s = new String();
     String s1 = new String();
     new String();
//     System.out.println(c);
//
//     System.out.println(stringBuffer.length());



}


}
