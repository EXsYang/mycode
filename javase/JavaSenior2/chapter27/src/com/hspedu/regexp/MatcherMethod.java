package com.hspedu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangda
 * @create 2024-03-17-21:48
 * @description:
 */
public class MatcherMethod {
    public static void main(String[] args) {
        String content = "hello edu jack hspedutom hello smith hello hspedu hspedu";
        String regStr = "hello";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("=================");
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("找到: " + content.substring(matcher.start(), matcher.end()));
        }
        //整体匹配方法，常用于，去校验某个字符串是否满足某个规则
        System.out.println("整体匹配=" + matcher.matches());
        //完成如果 content 有 hspedu 替换成 韩顺平教育
        regStr = "hspedu";
        pattern = Pattern.compile(regStr);
        matcher = pattern.matcher(content);
        //注意：返回的字符串才是替换后的字符串 原来的 content 不变化
        String newContent = matcher.replaceAll("韩顺平教育");
        System.out.println("newContent=" + newContent);
        System.out.println("content=" + content);


        /**
         * 使用 Pattern 和 Matcher 类进行正则表达式匹配（第一种写法）:
         *
         * Pattern.compile() 方法用于将给定的正则表达式编译成一个 Pattern 对象。
         * 使用 Pattern 对象的 matcher() 方法生成一个 Matcher 对象。
         *
         * 注意:
         * - 使用 matcher.matches() 进行全字符串匹配，确保整个字符串满足正则表达式。
         * - 使用 matcher.find() 可以在字符串中搜索多个符合正则的子序列，不限于整个字符串。
         * - 如果正则表达式需要多次使用，先编译成 Pattern 对象更高效，因为编译过程只需进行一次。
         *
         * 示例:
         * Pattern pattern = Pattern.compile(regStr); // 编译正则表达式
         * Matcher matcher = pattern.matcher(strValue); // 创建匹配器
         * return matcher.matches(); // 检查整个字符串是否匹配
         */
// Pattern 和 Matcher 示例代码
// Pattern pattern = Pattern.compile(regStr);
// Matcher matcher = pattern.matcher(strValue);
// return matcher.matches();

/**
 * 使用 String 的 matches 方法进行正则表达式匹配（第二种写法）:
 *
 * String 的 matches 方法用于检查整个字符串是否匹配给定的正则表达式。
 * 内部也是通过创建 Pattern 和 Matcher 来完成匹配。
 *
 * 注意:
 * - 每次调用都会编译正则表达式，适用于只匹配一次的场景。
 * - 对于频繁匹配相同正则表达式的情况，使用 Pattern 和 Matcher 的方式更高效。
 *
 * 示例:
 * return value.toString().matches(regStr); // 直接使用字符串的 matches 方法
 */
// String 的 matches 方法示例代码
// return value.toString().matches(regStr);

/**
 * 关于正则表达式 "^[0-1]$":
 *
 * - "^" 和 "$" 分别是正则表达式的开始和结束标记。
 * - "[0-1]" 匹配单个数字字符，只能是 '0' 或 '1'。
 * - 整个表达式意味着只有整个字符串是单个 '0' 或 '1' 时，才会匹配成功。
 */

    }
}
