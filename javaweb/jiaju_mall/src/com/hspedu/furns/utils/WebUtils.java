package com.hspedu.furns.utils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author yangda
 * @description:
 * @create 2023-08-13-18:36
 */
public class WebUtils {

    //定义一个文件上传的路径
    public static String FURN_IMG_DIRECTORY = "assets/images/product-image";

    /**
     * 判断该请求是否为ajax请求
     *
     * @param httpServletRequest
     * @return 返回true 是ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest httpServletRequest) {
        return "XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"));
    }

    /**
     * 生成年月日的方法
     *
     * @return
     */
    public static String getYearMonthDay() {
        //如何得到当前的日期-> java基础 日期 三代类
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        //String yearMonthDay = year + "/" + monthValue + "/" + dayOfMonth + "/";
        String yearMonthDay = year + "" + monthValue + "" + dayOfMonth + "/";
        return yearMonthDay;
    }
}
