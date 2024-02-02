package com.hspedu.tomcat.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-08-31-12:40
 */
public class CallServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CallServlet doPost...");
        // 这里这个request对象 带有和http请求相关的 与此处请求Socket 相关的输入流
        System.out.println("doPost中使用request调用方法 " + request.getRequestURL());

        m1(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void m1(HttpServletRequest request, HttpServletResponse response){
        //使用m1()方法时 需要传入Socket 相关的输入流
        // 注意：
        // 想要使该形参位置的request对象是和网络Socket 相关的输入流
        // 可以在doPost()中调用该方法
        System.out.println("m1中使用request调用方法 " + request.getRequestURL());
    }
}
