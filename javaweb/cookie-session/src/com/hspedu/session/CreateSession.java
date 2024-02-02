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
 * @create 2023-06-08-20:02
 */
public class CreateSession extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CreateSession 被调用...");
        //1. 当用户打开浏览器，访问某个网站, 操作 session 时，服务器就会在内存(在服务端)为该
        //浏览器分配一个 session 对象，该 session 对象被这个浏览器独占, 如图
        //2. 这个 session 对象也可看做是一个容器/集合,session 对象默认存在时间为 30min(这是在
        //  tomcat/conf/web.xml)，也可修改
        /*重新发布Redeploy 会清空服务器端的Session对象，关闭浏览器会清空会话级别的Cookie*/
        // 生命周期 是放在服务器端维护的，不会因为浏览器的关闭而销毁！！！！
        // 1.获取Session 同时也可能创建Session
        HttpSession session = request.getSession();
        // 2.获取Session id
        System.out.println("CreateSession 当前sessionId= " + session.getId());
        // 3.给Session存放数据
        session.setAttribute("email","tom@qq.com");

        // 4.给浏览器发送回复信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>创建/操作Session成功！</h1>");
        writer.flush();
        writer.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
