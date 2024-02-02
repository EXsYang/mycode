package com.hspedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-06-06-17:52
 */
public class MyRedirectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyRedirectServlet 被调用...");
        // 使用重定向 访问user.html

        //response.sendRedirect("http://localhost:8080/webpath/views/user/user.html");
        //response.sendRedirect("/webpath/views/user/user.html"); //比较稳定,但工程路径不灵活
        //response.sendRedirect("views/user/user.html"); // 不推荐,太依赖于浏览器地址栏,地址变了找不到

        String contextPath = request.getServletContext().getContextPath();
        System.out.println("contextPath= " + contextPath);
        response.sendRedirect(contextPath + "/views/user/user.html"); //推荐,稳定, 灵活



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
