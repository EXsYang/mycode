package com.hspedu.servlet.request.homework;

import javax.servlet.RequestDispatcher;
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
 * @create 2023-05-26-17:08
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterServlet 被调用");
        // 1. 接收到提交的各种数据
          /*username=3%E8%80%8C%E5%AF%B9%E6%96%B9&
            pwd1=111&
            pwd2=111&
            sport=%E8%B6%B3%E7%90%83&
            sport=%E6%89%8B%E7%90%83&
            gender=male&
            city=bj&
            info=%E5%A4%A7%E5%AE%B6%E5%A5%BD&
            myfile=*/

        // 接收浏览器发送过来的数据
        //request.setCharacterEncoding("utf-8");
        //request.getRequestDispatcher("").forward(request,response);

        //String uname = request.getParameter("uname");
        //String uname1 = request.getParameter("uname1"); //不存在的uname1
        // 原生 ajax 发过来的是处理过的中文，不用设置
        //System.out.println("uname =" + uname); //uname ="" getParameter存在uname时，前端什么都不填返回空串""
        //System.out.println("uname运行类型 =" + uname.getClass()); // class java.lang.String
        //System.out.println("uname1 =" + uname1); //uname1 =null getParameter不存在uname1时，返回null
        //System.out.println("uname1运行类型 =" + uname1.getClass()); // 报错


        // 处理接收到的中文乱码问题
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        System.out.println("用户名：" + username);
        String pwd1 = request.getParameter("pwd1");
        System.out.println("密  码：" + pwd1);
        String pwd2 = request.getParameter("pwd2");
        System.out.println("确认密码：" + pwd2);

        String[] sports = request.getParameterValues("sport");
        for (String sport : sports) {
            System.out.println("喜欢的运动：" + sport);
        }

        String gender = request.getParameter("gender");
        System.out.println("性别：" + gender);

        String city = request.getParameter("city");
        System.out.println("城市：" + city);

        String info = request.getParameter("info");
        System.out.println("自我介绍：" + info);

        // 2.将接收到的数据，返回给浏览器并显示
        // 处理相应时的中文乱码问题
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = response.getWriter();
        writer.print("<h1>Reborn studying Java now"   + "</h1><br/>");
        writer.print("<h1>用户名：" + username + "</h1><br/>");
        writer.print("<h1>密  码：" + pwd1 + "</h1><br/>");
        writer.print("<h1>确认密码：" + pwd2 + "</h1><br/>");
        writer.print("<h1 style="+"'display: inline-block'>喜欢的运动：</h1>");
        for (String sport : sports) {
            writer.print("<h1 style="+"'display: inline-block'>" + sport + "</h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
        }
        writer.print("<br/>");
        writer.print("<h1>性别：" + gender + "</h1><br/>");
        writer.print("<h1>城市：" + city + "</h1><br/>");
        writer.print("<h1>自我介绍：" + info + "</h1><br/>");


        // 显示网站访问次数：
        ServletContext servletContext = getServletContext();

        //Integer visit_num = (Integer)servletContext.getAttribute("visit_num");
        // null 可以转成任何类型的对象  但再次转成基本数据类型不行！！

        Object visit_num = servletContext.getAttribute("visit_num");

        //第一次访问：
        if (visit_num == null) {
            servletContext.setAttribute("visit_num", 1);
            visit_num = 1;
        } else {
            visit_num = Integer.parseInt(visit_num + "") + 1;
            servletContext.setAttribute("visit_num", visit_num);
        }

        writer.print("<h1>网站访问次数为：" + visit_num + "</h1><br/>");

        writer.flush();
        writer.close();
        //请求转发到ComputerServlet,进行下一步操作
        // 获得转发器   请求转发时，在第一个界面，即转发到下个页面之前不可以进行打印操作
        // 否则转发到的computerServlet代码不会运行
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/computerServlet");

        requestDispatcher.forward(request,response);

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
