package com.hspedu.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-06-07-13:49
 */
public class ReadCookieByNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadCookieByNameServlet 被调用");
        //得到指定的cookie的value
        //1. 先得到浏览器携带的所有cookie
        Cookie[] cookies = request.getCookies();

        //2. 使用工具类来获取指定的cookie
        Cookie emailCookie = CookieUtils.readCookieByName("email", cookies);
        if (emailCookie != null){
            System.out.println("得到的Cookie name= " + emailCookie.getName() + " value= " + emailCookie.getValue());
        }else {
            System.out.println("sorry,没有找到指定的Cookie");
        }

        //3. 给浏览器返回信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>读取Cookie任务完成</h1>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
