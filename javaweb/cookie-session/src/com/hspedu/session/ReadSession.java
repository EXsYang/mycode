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
 * @create 2023-06-08-21:44
 */
public class ReadSession extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadSession 被调用...");
        // 演示读取session
        //1. 获取session, 如果没有session, 也会创建
        HttpSession session = request.getSession();
        //输出sessionId
        System.out.println("ReadSession 当前sessionId= " + session.getId());

        //2. 读取属性
        //String email = (String)session.getAttribute("email"); //不可以直接转，有可能为null
        //System.out.println("email 强制转换后：" + email);
        Object email = session.getAttribute("email");
        Object email2 = session.getAttribute("email2");

        System.out.println("email= " + email);//当前程序创建的Session 没有设置email get后 Object email 是 null
        String email1 = (String) email;
        System.out.println("email1= " + email1); //不设置值输出为 null
        System.out.println("get不存在的属性email2= " + email2); // get不存在的属性 输出为null
        if (email != null){
            System.out.println("session 属性 email= " + (String)email);
        }else {
            System.out.println("session 中没有 email属性");
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
