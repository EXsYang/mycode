package com.hspedu.servlet;

import com.hspedu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-05-28-6:55
 */
public class CalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 此时配置的tomcat Application context 若是 /
        // 浏览器 输入 http://localhost:8080/cal.html 提交 可以找到该calServlet

        //接收提交的数据进行计算
        //复制当前行 ctrl+alt+下光标
        String strNum1 = request.getParameter("num1");
        String strNum2 = request.getParameter("num2");

        //把strNum1 和 strNum2 转成 int
        int num1 = WebUtils.parseInt(strNum1, 0);
        int num2 = WebUtils.parseInt(strNum2, 0);
        int result = num1 + num2;

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>" + num1 + " + " + num2 + " = " + result + "<h1>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
