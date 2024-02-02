package com.hspedu.tomcat.utils;

import com.hspedu.tomcat.HspTomcatV3;
import com.hspedu.tomcat.handler.HspRequestHandler;
import com.hspedu.tomcat.http.HspResponse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * @author yangda
 * @description:
 * @create 2023-06-04-18:49
 */
public class WebUtils {
    // 将字符串转换为数字，转换失败，返回默认值
    public static int parseInt(String strNum, int defaultVal) {

        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println(strNum + "不能转换为数字");
        }

        return defaultVal;
    }

    // 识别资源方法,判断uri是静态资源html, 还是servlet
    public static boolean identifyResources(String uri) {
        return uri.endsWith(".html");
    }

    // 读取html
    public static String readHtml(String uri){
        // 读取静态页面
        URL url = HspTomcatV3.class.getResource("/");
        String path = HspTomcatV3.class.getResource("/").getPath();
        String path1 = HspRequestHandler.class.getResource("/").getPath();
        System.out.println("url=" + url);
        System.out.println("path= " + path);
        System.out.println("path1= " + path1); // path 和 path1 相同都是 file:/D:/Java_developer_tools/javaweb/hsptomcat/ydtomcat/target/classes/
        System.out.println("uri= " + uri); //
        System.out.println("uri.substring(1)= " + uri.substring(1)); //

        StringBuilder respHtml = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            //substring(1) 拿掉 斜杆 /
            //bufferedReader = new BufferedReader(new FileReader(path + uri )); // 多一个uri中的/也可以访问到
            //bufferedReader = new BufferedReader(new FileReader("/D:/Java_developer_tools/javaweb/hsptomcat/ydtomcat/target/classes/cal.html"));
            //bufferedReader = new BufferedReader(new FileReader(path + uri.substring(1)));
            System.out.println("path + uri = " + (path + uri));
            System.out.println("path + uri.substring(1) = " + (path + uri.substring(1)));



         //new FileReader(直接写URL字符串会报错) 路径不规范,前面有个"file:" (文件名、目录名或卷标语法不正确。)
         //bufferedReader = new BufferedReader(new FileReader(
         //"file:/D:/Java_developer_tools/javaweb/hsptomcat/ydtomcat/target/classes" + "/cal.html"));
         bufferedReader = new BufferedReader(new FileReader(
                 "D:\\Java_developer_tools\\javaweb\\hsptomcat\\ydtomcat\\target\\classes\\cal.html" ));// 字符串文字中存在非法转义符 反斜杠\\用两个等于一个/




            String buf = "";
        //String respHtml = "";
        while ((buf = bufferedReader.readLine()) != null){
            respHtml.append(buf);
        }
            System.out.println("读取到的数据:respHtml" + respHtml);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respHtml.toString();
    }

}
