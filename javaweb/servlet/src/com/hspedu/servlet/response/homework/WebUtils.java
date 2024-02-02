package com.hspedu.servlet.response.homework;

/**
 * @author yangda
 * @description:
 * @create 2023-05-27-19:22
 */
public class WebUtils {

    public static int parseString(String str){
        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("输入的字符不正确");
        }

        return num;
    }

}
