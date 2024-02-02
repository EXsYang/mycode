package com.hspedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * @author yangda
 * @description:
 * @create 2023-09-25-21:06
 */
public class TestServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TestServlet2 doPost...");

        URL resource = TestServlet2.class.getResource("/");
        System.out.println("真正的运行环境= " + resource.getPath());
        //真正的运行环境= /D:/Java_developer_tools/javaweb/hsptomcat2/target/ydtomcat/WEB-INF/classes/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
