package com.hspedu.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-06-09-12:12
 */
public class ReadSession2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadSession2 被调用...");
        //1. 获取到session
        HttpSession session = request.getSession();
        System.out.print("读取到的 sid= " + session.getId());
        //2. 读取session的属性
        Object u = session.getAttribute("u");
        Object pwd = session.getAttribute("pwd");
        if (u != null){
            System.out.print("读取到session属性 u= " + (String)u);
        }else {
            System.out.println(" 读取不到session属性 u 说明原来的session被销毁");
        }
        if (pwd != null){
            System.out.println("  读取到session属性 pwd= " + (String)pwd);
        }else {
            System.out.println("  读取不到session属性 u 说明原来的session被销毁");
        }


        // 4.给浏览器发送回复信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>读取Session成功！</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
