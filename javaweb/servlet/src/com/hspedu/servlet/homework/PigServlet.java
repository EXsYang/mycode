package com.hspedu.servlet.homework;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-24-3:05
 */
@WebServlet(urlPatterns = {"/pig1","/pig2"},loadOnStartup = 1)
public class PigServlet extends HttpServlet {
    private int countGet = 0;
    private int countPost = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("PigServlet init被调用");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //访问时分开进行统计
        countGet++;
        System.out.println("PigServlet doGet... 调用" + countGet + "次");
        //输出浏览器/客户端ip
        System.out.println("访问的浏览器的IP " + req.getRemoteAddr());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        countPost++;
        System.out.println("PigServlet doPost... 调用" + countPost + "次");
        System.out.println("访问的浏览器的IP " + req.getRemoteAddr());
    }


}
