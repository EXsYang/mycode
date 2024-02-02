package com.hspedu.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1. Cookie 有效路径 Path 的设置
 * 2. Cookie 的 path 属性可以有效的过滤哪些 Cookie 可以发送给服务器。哪些不发。 path
 * 属性是通过请求的地址来进行有效的过滤
 * 3. 规则如下:
 * cookie1.setPath = /工程路径
 * cookie2.setPath = /工程路径/aaa
 * 请求地址: http://ip:端口/工程路径/资源
 * cookie1 会发给服务器
 * cookie2 不会发给服务器
 * 请求地址: http://ip:端口/工程路径/aaa/资源
 * cookie1 会发给服务器
 * cookie2 会发给服务器
 *
 * 结论：请求地址只要包含 cookie设置的有效路径 该cookie就会被浏览器携带给请求的资源
 * 如果我们没有设置cookie有效路径，默认就是 /工程路径，此时访问这个项目的资源默认都会携带 默认有效路径cookie
 *
 */
public class CookiePath extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CookiePath 被调用...");
        //1. 创建两个cookie
        Cookie cookie = new Cookie("address", "bj");
        Cookie cookie2 = new Cookie("salary", "20000");
        //2. 设置不同有效路径
        //   request.getContextPath() => /cs
        cookie.setPath(request.getContextPath());
        //   cookie2有效路径 /cs/aaa
        cookie2.setPath(request.getContextPath() + "/aaa");

        //老师说明：如果我们没有设置cookie有效路径，默认就是 /工程路径

        //3. 保存到浏览器
        response.addCookie(cookie);
        response.addCookie(cookie2);

        //4. 给浏览器返回信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>设置cookie有效路径成功</h1>");
        writer.flush();
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
