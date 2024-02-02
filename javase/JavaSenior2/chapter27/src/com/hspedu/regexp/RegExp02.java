package com.hspedu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 韩顺平
 * @version 1.0
 * 演示转义字符的使用
 */
public class RegExp02 {
    public static void main(String[] args) {
        String content = "/abc$(a.bc(123( )";
        String content1 = "abc$(a.bc(123( )\\";
        String content2 = "abc$(a.bc(123( )\\\\";
        // 提示:
        // 在Java的正则表达式中，两个反斜杠 \\ 代表其它语言中的一个反斜杠 \
        // 可以理解为第一个反斜杠\ 是java里的转义字符 真正用到的是后面的\.
        // 匹配字符串中的一个反斜杠 需要正则表达式中写四个反斜杠 同时在字符串中不可以只写一个
        // 反斜杠 会报错 字符串中两个反斜杠代表一个字符串反斜杠
        // 正则表达式写四个反斜杠代表要匹配一个字符串反斜杠 即content1中的写的两个反斜杠

        //普通程序中，即使是正则中，斜杠也就是斜杠。
        //但是java中，由于string的设计，导致斜杠，是特殊的转义字符，所以，在正则中，如果想要写普通的，正则的转义，比如'\d'表示数字，则要写成'\\d'才可以。
        //所就变成了：其他程序中，正常的写单个的斜杠的，java中，都要变成双斜杠。


        //匹配( => \\(
        //匹配. => \\.    只写一个 .  不写转义符 会匹配所有的字符
        //String regStr = "\\.";
        //String regStr = "\\d\\d\\d";
        //String regStr = "\\d{3}";
        //String regStr = "\\\\";
        String regStr = "/"; // 正斜杠不需要进行转义就可以匹配到
        //String regStr = "\\/"; // 当然啊 正斜杠转义后仍然可以匹配到
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        Matcher matcher1 = pattern.matcher(content1);
        Matcher matcher2 = pattern.matcher(content2);

        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }
}
