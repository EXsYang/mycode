package com.hspedu.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-05-25-19:41
 */
public class ManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManageServlet 被调用 ... ");

        //获取信息，打印到浏览器
        String username = request.getParameter("username");
        //从request域中获取信息
        String role = (String)request.getAttribute("role");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>用户名：" + username + "</h1>");
        writer.print("<h1>身  份：" + role + "</h1>");
        writer.flush();
        writer.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
