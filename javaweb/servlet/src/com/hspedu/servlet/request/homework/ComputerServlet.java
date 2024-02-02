package com.hspedu.servlet.request.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangda
 * @description:
 * @create 2023-05-27-14:11
 */
public class ComputerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //可以获取到浏览器所在电脑的操作系统版本和位数(32还是64), 显示在页面即可
        System.out.println("ComputerServlet 被调用");

        //解决 接收到的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String userAgent = request.getHeader("User-Agent");
        //Mozilla/5.0 (Windows NT 10.0; Win64; x64)
        // AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36

        String[] splits = userAgent.split(" ");
        if (splits.length > 10){//谷歌的
            System.out.println("浏览器：" + splits[splits.length-2].split("\\/")[0]);
        }else {//火狐的
            System.out.println("浏览器：" + splits[splits.length-1].split("\\/")[0]);
        }

        // 匹配五个连续相同的数字  (\\d)\\1{4}
        // \\d{2}  匹配任意的两位数字
        // \\d{1}  匹配任意的一位数字

        Pattern pattern = Pattern.compile("\\w+\\s\\w{2}\\s\\d{2}+\\.\\d");
        //Pattern pattern = Pattern.compile("\\w+\\s\\w+\\s\\d+\\.\\d");
        Matcher matcher = pattern.matcher(userAgent);
        if (matcher.find()){
            System.out.println("匹配成功");
        }else {
            System.out.println("匹配失败");
        }
        System.out.println("浏览器的操作系统：" + matcher.group(0));
        Pattern pattern1 = Pattern.compile("\\w+\\d{2}");
        //Pattern pattern1 = Pattern.compile("\\w+\\d+");
        Matcher matcher1 = pattern1.matcher(userAgent);
        if (matcher1.find()){
            System.out.println("匹配成功");
        }else {
            System.out.println("匹配失败");
        }
        System.out.println("浏览器的电脑的位数：" + matcher1.group(0));

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>浏览器的操作系统：" + matcher.group(0) + "</h1><br/>");
        writer.print("<h1>浏览器的电脑的位数：" + matcher1.group(0) + "</h1>");
        writer.flush();
        writer.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
