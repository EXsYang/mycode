package com.atguigu.StringTest;

import org.junit.Test;
import sun.security.util.Length;

/**
 * @author yangda
 * @description:老师讲的三道String的算法题，2，3，4
 * @create 2022-11-10-10:57
 */
public class StringTestexer {

    @Test
    public void testreverse() {

        StringTestexer t = new StringTestexer();
        System.out.println(t.reverse("0123456789", 2, 5));
        System.out.println(t.reverse("0", 0, 0));

        System.out.println(t.reverse1("0123456789", 2, 5));
        System.out.println("********************");
        System.out.println(t.reverse2("0123456789", 2, 5));


    }

    private String reverse(String str, int startIndex, int endIndex) {
        if (str != null) {
            char[] chars = str.toCharArray();
//        for (int i = startIndex,int j = endIndex; i < j ; i++,j--) {//不能定义两次初始变量，不能int两次
            for (int i = startIndex, j = endIndex; i < j; i++, j--) {//不能定义两次初始变量
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
            }
//            String s = new String(chars);
//            return s ;
            return new String(chars);//如果只有一个字符，直接放在构造器里返回！
        }
        return null;
    }

    private String reverse1(String str, int startIndex, int endIndex) {
        if (str != null) {
            //第一部分
            String substring = str.substring(0, startIndex);

            //第二部分
//        for (int i = startIndex,int j = endIndex; i < j ; i++,j--) {//不能定义两次初始变量，不能int两次
            for (int i = endIndex; i >= startIndex; i--) {//不能定义两次初始变量
                substring += str.charAt(i);//效率低
            }
            substring += str.substring(endIndex + 1);

            return substring;//如果只有一个字符，直接放在构造器里返回！
        }
        return null;


    }

    private String reverse2(String str, int startIndex, int endIndex) {
        if (str != null) {

            StringBuilder stringBuilder = new StringBuilder(str.length());
            //第一部分
            stringBuilder.append(str.substring(0, startIndex));

            //第二部分
            for (int i = endIndex; i >= startIndex; i--) {//
                stringBuilder.append(str.charAt(i));
            }

            //第三部分
            stringBuilder.append(str.substring(endIndex + 1));//一直截取到最后
//            return stringBuilder;//这里返回类型是String类型
            return new String(stringBuilder);//这里返回类型是String类型
        }
        return null;


    }

    @Test
    public void testCount() {
        int count = 0;
//        3）获取一个字符串在另一个字符串中出现的次数。
//        比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
        String a = "abkkcadkabkebfkabkskab";
        String sub = "ab";
        int first = a.indexOf("ab");//找到计数器加一
        int last = a.lastIndexOf("ab");
        System.out.println(first);
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
    public void testCount1() {
        StringTestexer stringTestexer = new StringTestexer();
        System.out.println(stringTestexer.getCount("abkkcadkabkebfkaabkskab", "ab"));

    }

    public int getCount(String mainStr, String subStr) {
        int index = 0;
        int count = 0;
        String substring;
        if (mainStr.length() >= subStr.length()) {
            //判断字符串的长度
            //方式一：
            while ((index = mainStr.indexOf(subStr)) != -1) {//找到进去循环，返回一个数
                mainStr = mainStr.substring(index + subStr.length());//将找到ab后剩下的，生成一个新的subStr
                //有返回值，返回子字符串第一次出现时的索引
                count++;
            }
            //方式二：效率更高
//            while (mainStr.indexOf(subStr, index) != -1){//找到进去循环，返回一个数
//                index = mainStr.indexOf(subStr, index) + subStr.length();
//                ;//有返回值，返回子字符串第一次出现时的索引
//                count++;
//            }
            return count;
        }
        return 0;
    }

    @Test
    public void testSameString() {
        StringTestexer stringTestexer = new StringTestexer();

        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";

        System.out.println(stringTestexer.getMaxSameString(str1, str2));
    }

    public String getMaxSameString(String str1, String str2) {

//        4 获取两个字符串中最大相同子串。比如：
//       str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
//       提示：将短的那个串进行长度依次递减的子串与较长的串比较。
//       去除str2中的字符去str1中一个一个去找，找到后放在一边，如果再次找到，比较length
//       最后保留最长的字符串

        //获取两个字符串中较大的那一个和较小的那一个
        String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
        String minStr = (str1.length() < str2.length()) ? str1 : str2;

        //定义两个变量x,y 控制字符串左右两端
        //从最大的往小的去找，找到了，直接返回，后面不用找了

        for (int i = 0; i < minStr.length(); i++) {//相当于把字符串从后面开始缩短，缩短后固定这一段的长度往右滑动，划到最后
            //一个结束，y的取值范围最后可以取到minStr.length(),因为用substring进行截取时左闭右开，length位置在字符串的最后
            // 一个后面，正好可以取到length-1位置上的字符
            for (int x = 0, y = minStr.length() - i; y <= minStr.length(); x++, y++) {

                if (maxStr.contains(minStr.substring(x, y))) {//把取出来的字符串从大到小和maxStr对比
                    // //contains() 返回一个boolean 类型的值

                    //找到了，最大相同子串
                    return minStr.substring(x, y);
                }
            }
        }
        return null;

    }
}
