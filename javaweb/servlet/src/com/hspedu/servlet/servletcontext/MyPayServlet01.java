package com.hspedu.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-05-24-22:45
 */
public class MyPayServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 网站计数器
        // 所有的Servlet 共享 ServletContext
        ServletContext servletContext = getServletContext();
        System.out.println("MyOrderServlet servletContext= " + servletContext +
                " 运行类型=" + servletContext.getClass());

        //Object visit_count = servletContext.getAttribute("visit_count");
        //
        //if (visit_count == null) {
        //    servletContext.setAttribute("visit_count", 1);
        //    visit_count = 1;
        //} else {
        //    visit_count = Integer.parseInt(visit_count + "") + 1;
        //    servletContext.setAttribute("visit_count", visit_count);
        //}

        Integer visit_count = WebUtil.visitCount(servletContext);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>My网站被访问次数:" + visit_count + "</h1>");

        writer.flush();
        writer.close();





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
