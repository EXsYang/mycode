package com.hspedu.utils;

/**
 * @author yangda
 * @description:
 * @create 2023-06-17-0:51
 */
public class WebUtils {

    // 提供方法转换为数值型
    public static int parseInt(String str,int defaultVal){
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println(str + "类型转换失败");
            }
        return defaultVal;
    }
    // 提供方法转换为数值型
    public static double parseDouble(String str,double defaultVal){
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                System.out.println(str + "类型转换失败");
            }
        return defaultVal;
    }


}
