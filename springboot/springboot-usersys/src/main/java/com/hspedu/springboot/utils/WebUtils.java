package com.hspedu.springboot.utils;

import com.hspedu.springboot.controller.UploadController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yangda
 * @create 2023-12-10-13:38
 * @description:
 */
public class WebUtils {

    //定义一个文件上传的路径
    public static String UPLOAD_FILE_DIRECTORY = "static/images/upload/";

    /**
     * 判断该请求是否为ajax请求
     *
     * @param httpServletRequest
     * @return 返回true 是ajax请求
     */
    // public static boolean isAjaxRequest(HttpServletRequest httpServletRequest) {
    //     return "XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"));
    // }

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

    // 生成目录的日期 年/月/日
    public static String getUploadFileDirectory(){
        return UPLOAD_FILE_DIRECTORY + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        // new File() 创建File对象时 会拿掉最后一个斜杠！！
        // return UPLOAD_FILE_DIRECTORY + new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
    }


    public static void main(String[] args) {
        String uploadFileDirectory = WebUtils.getUploadFileDirectory();
        System.out.println(uploadFileDirectory);
    }
}
