package com.hspedu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {

        String str = "abcdef.mx";
//        System.out.println(str.substring(0,4));

//        String content = "234.333.ss..axs";
//      String regStr = "[.]";   //匹配到 . 本身
//      String regStr = ".";     //匹配任意字符
//        String regStr = "(.)";   //匹配任意字符

//        String regStr = "[\\w.]+";   //
//        String regStr = "([\\w.])+";   //匹配 捕获 一个字符，限定一到多
//        String regStr = "([\\w.]+)";   //匹配 捕获 1到多 个字符


//        Pattern pattern = Pattern.compile(regStr);
//        Matcher matcher = pattern.matcher(content);
//
//        while (matcher.find()) {
//
//            System.out.println("找到= " + matcher.group(0));
//            System.out.println("找到= " + matcher.group(1)); //
//        }

        String content = "909533571@qq.com";
        boolean matches = content.matches("[\\w]+\\@[\\w.]+");
//        boolean matches = content.matches("^[\\w]+\\@[\\w.]+com$");
        if (matches){
            System.out.println("验证成功");
        }else{
            System.out.println("验证失败");
        }

    }
}
