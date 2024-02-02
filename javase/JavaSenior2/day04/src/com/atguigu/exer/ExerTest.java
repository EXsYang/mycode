package com.atguigu.exer;

import org.junit.Test;

import java.lang.reflect.Array;

/**
 * @author yangda
 * @create 2021-12-07-13:22
 */
public class ExerTest {

    @Test
    public void test1() {
        //1.模拟trim, 去除两端的空格
        //去除开头空格，找出空格，到非空格停止，遍历数组if c[i] != " "
        // 停止打印c[i]
        //打印直到最后一个字母停止，去除尾部空格
        //遍历数组，直到最后一个字母结束，去掉最后几位的空格

        String s = "  aa vv cc ";
        String x = " ";
        char[] c1 = x.toCharArray();
        int i;
        int e = 0;
        int d = 0;
        char[] c = s.toCharArray();
        for (i = 0; i < s.length(); i++) {
            if (c[i] == c1[0] && c[i + 1] != c1[0]) {
                d = i + 1;
                break;
            }
        }
        for (i = s.length() - 1; i >= 0; i--) {//从后往前遍历数组，到c[i]!=" "时停止
            if (c[i] == c1[0] && c[i - 1] != c1[0]) {//获取到从后往前第一个不为空格的角标，当前的i
                e = i - 1;
                break;
            }
        }
        for (int j = d; j <= e; j++) {
            System.out.print(c[j]);
        }
    }

    @Test
    public void test2() {
        //反转指定位置字符串，如abcdefg  abfedcg
        String str1 = "abcdefg";
        // String str2 = str1.replace("cdef", "fedc");
        char[] chars = str1.toCharArray();
        for (int i = 2, j = chars.length - 2; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        String str2 = new String(chars);


        System.out.println(str2);
        System.out.println(str2);


    }

    @Test
    public void test3() {
        //获取一个字符串在另一个字符串中出现的次数
        //比如，获取“ab”在“abkkcadkabkebfkabkskab”中出现的次数
        String str3 = "qabkkcadkab kebfk abksk ab";
        int num = 0;
        int num1 = 0;
        int cont = 0;
        num1 = str3.lastIndexOf("ab");
        System.out.println(num1);
        for (; ; ) {
            num = str3.indexOf("ab", num);
            if (num >= 0) {
                cont++;
                num++;
            } else {
                System.out.println("没找到");
                break;
            }
            if (num - 1 == num1) {
                break;
            }
        }
        System.out.println("ab出现的次数：" + cont);
    }

    @Test
    public void test4() {
        //获取两个字符串中最大相同子串。比如：
        //str1 = "abcwerthelloyuiodef";str2 = "cvhellobnm"
        //boolean contains(CharSequence s): 当且仅当此字符串包含指定的char值序列时，返回true
        //getChars(int srcBegin,int srcEnd,char[] dst,int dstBegin)将字符从字符串复制到目标字符数组
//        srcBegin -- 字符串中要复制的第一个字符的索引。
//        srcEnd -- 字符串中要复制的最后一个字符之后的索引。
//        dst -- 目标数组。
//        dstBegin -- 目标数组中的起始偏移量。
//        没有返回值，但会抛出 IndexOutOfBoundsException 异常。
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";

        //将短的那个放进char[]中，用长的字符串调用contains方法去找有没有相同子字符串，通过循环控制要找的字符串的长度，直到长度位length-1结束
        //结束后通过判断返回的有没有true，判断有没有相同子字符串，如果没有，返回false,打印“没有相同子字符”
        //如果有，即返回true，比较找出来的子字符串长度，把最长的打印出来

        //getChars(int srcBegin,int srcEnd,char[] dst,int dstBegin) 方法将字符从字符串复制到目标字符数组。

        int subLength = 0;
        int templength = 0;
        int l = 0;
        int r = 0;
        //创建一个数组，存放最后得到的相同子字符串
        char[] maxchar = new char[str1.length()];

//        String str2 = "cvhellobnm";
        char[] chars = str2.toCharArray();//将字符串转换为char型数组
        for (int i = 0; i < str2.length(); i++) {//i控制小字符串左边的起点位置,取值范围正确
            for (int j = 0; j < str2.length() - i; j++) {//j的取值范围是正在取的现在的长度，控制小字符串右面的结束位置，j每次都是从0开始，
                //此时的j是被i控制过的小字符串子字符串中的右边j的位置，而不是原来的小字符串右边的j的位置
                //调用String的getChars(i,i+j+1,maxchar,0)将char[]中指定的字符复制到新的字符数组中

                /**
                 *  s.getChars(5,7,c,0);
                 * 5,表示从s的第5个字符开始
                 * 7,表示到s的第7-1个字符结束
                 * c,表示或缺的字符放到字符数组c中
                 * 0,表示从c的位置0开始存放
                 */



                char[] chars1 = new char[j + 1];//创建一个长度与小的字符串长度一致的数组
                str2.getChars(i, j + i + 1, chars1, 0);//这里i的位置是原来的小字符串左边的索引，j+1是小字符串子字符串右边的索引！
                String s = new String(chars1);//将字符数组转换为字符串
                boolean contains = str1.contains(s);//用短的里提取出来的字符串
                if (contains == true) {
                    // System.out.println("找到了，相同子串是：" + s);
                    templength = s.length();//获取相同子串的字符长度
                    if (templength >= subLength) {//如果在找相同子串的过程中，其长度比原有子字符串长，记录现在更长的长度，以及记录此时的左右索引位置
                        subLength = templength;
                        l = i;//记录相同子串左右索引的位置,左边的索引位置固定
                        r = j + i + 1;//右边的索引为位置需要+1，因为后面那一个getChars()方法也取不到真实的子字符串右边索引，因为它是左闭右开的
                        // 这里的r-1是相同子串在原来str2中的索引位置，因为下面又用getChars()去复制相同子串到新的数组了，所以这里r是得到的是正确位置+1
                    }
                }
            }
        }
        str2.getChars(l, r, maxchar, 0);
        String maxsub = new String(maxchar);
        System.out.println("最大相同子字符串是：" + maxsub);
    }

    @Test
    public void test5() {
        //对字符串中字符进行自然顺序排序
        String str = "adgjixlfuky";
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {//冒泡排序
            char team;
            int a = arr[i + 1] - arr[i];//如果a大于0，说明后一个数比前一个数大，那么把后一个数放后面
            if (a <= 0) {
                team = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = team;
            }
        }
        System.out.println(arr);


    }

    @Test
    public void test6() {

        String a = "afdfasfdafdaaad";
        String b = "z";
        System.out.println(a.indexOf(b));//返回-1表示没有找到

//        String substring = a.substring(b);

    }
}
