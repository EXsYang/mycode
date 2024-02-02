package com.hspedu.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description: 
 *  * ServletContext(实现类的对象)对象
 *  * 1. 可以被多个Servlet共享
 *  * 2. 数据存储形式 k-v,类似于Map
 *  * 3. 可以实现多个Servlet的通信
 *  * 4. 注意数据在内存
 *
 * @create 2023-05-24-21:50
 */
public class ServletContext_ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取web.xml的context-parameter

        //1. 获取ServletContext对象
        ServletContext servletContext = getServletContext();

        //3. 获取工程路径
        String contextPath = servletContext.getContextPath();
        //2. 获取website
        String website = servletContext.getInitParameter("website");
        String company = servletContext.getInitParameter("company");
        //获取项目发布后，真正的工作路径
        //  /表示我们的项目(发布后)的 根路径 D:\Java_developer_tools\javaweb\servlet\out\artifacts\servlet_war_exploded
        String realPath = servletContext.getRealPath("/");


        System.out.println("website :" + website);
        System.out.println("company :" + company);
        //3. 获取工程路径
        System.out.println("项目路径：" + contextPath); // /servlet
        //4. 项目发布后的绝对路径
        System.out.println("项目发布后的绝对路径=" + realPath);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}
