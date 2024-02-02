package com.hspedu.cookie.homework;

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
 * @create 2023-06-08-10:13
 */
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckServlet 被调用...");
        //设置接收数据的编码格式
        request.setCharacterEncoding("utf-8");
        // 检查用户名密码是否正确 hspedu  123456
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        if ("hspedu".equals(username)){
            System.out.println("用户名存在，进行密码验证");
            if ("123456".equals(pwd)){
                System.out.println("密码正确登录成功");
                // 将用户名Cookie写入浏览器的 并返回响应信息给浏览器
                // 创建Cookie对象
                Cookie cookie = new Cookie("username", username);
                // 设置cookie 生命周期 3 天
                cookie.setMaxAge(259200);
                // 将cookie 保存到浏览器
                response.addCookie(cookie);

                // 返回给浏览器登录成功信息
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.print("<h1>登录OK!!</h1>");
                writer.flush();
                writer.close();


            }else {
                System.out.println("密码有误，请重新输入");
                // 重定向到，登录界面
                response.sendRedirect(request.getContextPath() + "/loginCookie");
                System.out.println("重定向到，登录界面");
            }
        }else {
            System.out.println("用户名不存在，请重新输入");
            // 重定向到，登录界面
            response.sendRedirect(request.getContextPath() + "/loginCookie");
            System.out.println("重定向到，登录界面");

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
