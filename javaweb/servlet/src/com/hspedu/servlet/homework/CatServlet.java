package com.hspedu.servlet.homework;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-23-18:22
 */
public class CatServlet implements Servlet {
    private int count = 0;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("CatServlet init被调用。。。");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        count++;
        System.out.println("CatServlet service()方法被调用 " + count + " 次");
        //HttpServletRequest 是 ServletRequest的子接口
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        System.out.println("getMethod = " + method);
        if ("GET".equals(method)){
            doGet();
        }else if ("POST".equals(method)){
            doPost();
        }

    }

    //处理get请求
    public void doGet(){
        System.out.println("CatServlet doGet()...");
    }
    //处理post请求
    public void doPost(){
        System.out.println("CatServlet doPost()...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
