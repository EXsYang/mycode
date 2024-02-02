package com.hspedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-06-20-11:17
 */
public class LoginCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginCheckServlet 被调用...");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username= " + username); // 不填用户名返回空串""
        if (username != null){
            if ("1234".equals(password)){
                System.out.println("用户登录成功");
                // 设置session属性
                HttpSession session = req.getSession();
                // 这里写错了"admin"
                //session.setAttribute("admin",username);
                session.setAttribute("username",username);

                // 转发到manage/admin
                // /manage/admin.jsp 因为这里是请求转发 所有不会经过过滤器！！！
                // 原因并不是因为请求转发的以前的url没有变化 即地址栏只显示第一次请求的资源的地址
                // 而是因为请求转发根本就不走过滤器！！！
                req.getRequestDispatcher("/manage/admin.jsp").forward(req,resp);

            }else {
                System.out.println("密码不正确，请重新输入");
                // 转发到manage/admin
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }else {
            System.out.println("用户名为空");
        }



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
