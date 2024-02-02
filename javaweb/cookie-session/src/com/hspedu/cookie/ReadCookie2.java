package com.hspedu.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @author yangda
 * @description:
 * @create 2023-06-08-17:38
 */
public class ReadCookie2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadCookie2 被调用...");
        //读取到中文cookie
        Cookie[] cookies = request.getCookies();
        Cookie comCookie = CookieUtils.readCookieByName("company", cookies);

        String cookieVal = "";
        if (comCookie != null) {
            cookieVal = comCookie.getValue();
        }
        System.out.println("未解码，原始的cookieVal= " + cookieVal);//URL编码

        //解码
        String decodeCookieVal = URLDecoder.decode(cookieVal, "utf-8");
        System.out.println("解码后的cookieVal= " + decodeCookieVal);//中文

        //3. 给浏览器返回信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>服务器读取中文Cookie解码成功！</h1>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
