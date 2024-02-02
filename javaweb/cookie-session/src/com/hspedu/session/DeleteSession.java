package com.hspedu.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author yangda
 * @description:
 * @create 2023-06-09-12:26
 */
public class DeleteSession extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DeleteSession 被调用...");
        //演示如何删除session
        HttpSession session = request.getSession();
        //public void invalidate() 让当前 Session 会话立即无效
        //invalidate: 使无效
        //session.invalidate();

        //老韩再多说一句, 如果你要删除session的某个属性
        //session.removeAttribute("xxx");



        System.out.println("DeleteSession sid= " + session.getId());

        //如果IllegalStateException: 如果对无效的会话调用此方法 //  java.lang.IllegalStateException: getAttributeNames: Session already invalidated
        //Enumeration是一个枚举数组
        Enumeration<?> enumeration = session.getAttributeNames();

        // 遍历enumeration中的
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("<B>" + name + "</B>=" + value + "<br>/n");

        }
        session.removeAttribute("u");
        System.out.println("==========session.removeAttribute(\"u\") 之后：=============");
        Enumeration<String> enumeration2 = session.getAttributeNames();
        while (enumeration2.hasMoreElements()) {
            // 获取session键值
            String name = enumeration2.nextElement();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("<B>" + name + "</B>=" + value + "<br>/n");

        }

        session.invalidate();
        System.out.println("已经调用session.invalidate();");






        // 4.给浏览器发送回复信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>删除Session成功！</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
