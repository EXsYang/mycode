package com.hspedu.servlet.test;

import com.hspedu.servlet.utils.JDBCUtilsByDruid;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author yangda
 * @description:
 * @create 2023-09-22-19:03
 */
public class testServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("testServlet doPost被调用...");
        // 获取项目的context路径
        String contextPath = req.getContextPath();
        System.out.println("contextPath= " + contextPath);



        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println("connection= " + connection);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
