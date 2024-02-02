package com.hspedu.cookie.homework;

import com.hspedu.cookie.CookieUtils;

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
 * @create 2023-06-08-10:09
 */
public class LoginCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginCookie 被调用...");


        // 访问登录页面之前先检查cookie 如果有cookie 就写入 用户名输入框
        // 得到 cookies
        Cookie[] cookies = request.getCookies();
        // 判断是否有 username = hspedu
        //如果有，将其写入用户名输入框
        String username = "";
        //String username = null;
        //if (cookies != null) {
        Cookie cookie = CookieUtils.readCookieByName("username", cookies);
        if (cookie != null) {//如果有
            username = cookie.getValue();
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Login</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>用户登录界面</h1>\n" +
                "<form action=\"/cs/checkServlet\" method=\"post\">\n" +
                //"    u:<input type=\"text\" value=\"" + username + "\" name=\"username\"><br/>\n"
                "    用户名：<input type=\"text\" name=\"username\" value= '" + "" + username + "" + " \'><br/>\n" +
                "    密码：<input type=\"password\" name=\"pwd\"><br/>\n" +
                "    <input type=\"submit\" value=\"登录\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        //}
        //}
        System.out.println("    用户名：<input type=\"text\" name=\"username\" value= '" + "" + username + "" + " \'><br/>\n");
        System.out.println("    u:<input type=\"text\" value=\"" + username + "\" name=\"username\"><br/>\n");
        writer.flush();
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
