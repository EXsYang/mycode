package com.hspedu.servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-24-2:47
 */
public class DogServlet extends HttpServlet {
    private int countGet = 0;
    private int countPost = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //访问时分开进行统计
        countGet++;
        System.out.println("DogServlet doGet... 调用" + countGet + "次");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        countPost++;
        System.out.println("DogServlet doPost... 调用" + countPost + "次");
    }
}
