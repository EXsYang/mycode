package com.hspedu.session.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-06-09-14:16
 */
public class ManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManageServlet 被调用...");
        // 直接访问ManageServlet 直接转发到login.html
        // 使用Session 解决！！
        /*思路： 登录成功后分配一个Session，保存用户名和密码 ，在LoginCheckServlet登录成功后创建Session
                给Session 设置属性，用户名和密码
                ManageServlet检查Session是否有密码属性，密码是否为"666666" */

        // 上来先进行Session检查，查看Session属性是否为null/"666666"
        HttpSession session = request.getSession();
        System.out.println("ManageServlet 当前sid= " + session.getId());
        Object pwd = session.getAttribute("pwd");

        // 获得填入的用户名
        String username = request.getParameter("username");//用户名什么都不填，直接提交，获得的是一个空串""
        System.out.println("填入的用户名：username= " + username); //用户名什么都不填，直接提交，获得的是一个空串""
        // 直接访问，没有input标签中的name="username" 属性，getParameter()返回的是 null
        if (pwd == null) { // session不存在 pwd属性 即在ManageServlet request.getSession();时生成的session对象
            // 转发到登录界面login.html
            //request.getRequestDispatcher("/Login.html").forward(request, response);

            // 重定向到登录界面login.html
            response.sendRedirect(request.getContextPath() + "/Login.html");
            System.out.println("没有登录，没设置Session pwd属性");
        } else if (username == null) {
            // 转发到登录界面login.html
            //request.getRequestDispatcher("/Login.html").forward(request, response);

            // 重定向到登录界面login.html
            response.sendRedirect(request.getContextPath() + "/Login.html");
            System.out.println("直接访问，没有input标签中的name=\"username\" 属性，getParameter()返回的是 null");
        } else if ("666666".equals(pwd) && username != null) { // 这里直接填入了一个Object类型，
            // 没有转换为String 为什么可以？测试是不是一直为false 结果：不是，可以正常运行 去看运行类型那一节
            // instanceOf 比较操作符，用于判断对象的运行类型是否为 XX 类型或 XX 类型的子类型

            //3. 给浏览器返回信息
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("<h1>登录成功！</h1>");
            writer.println("<h1>登录成功！</h1>");
            writer.println("<h1>欢迎您 管理员  " + username + "</h1>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
