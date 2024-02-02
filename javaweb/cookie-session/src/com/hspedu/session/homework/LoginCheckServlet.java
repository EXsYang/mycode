package com.hspedu.session.homework;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description: 遗留问题：登录过一次后，再次直接访问ManageServlet 竟然可以访问到
 * @create 2023-06-09-14:14
 */
public class LoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginCheckServlet 被调用...");

        /*只要密码666666，就登录成功，用户名不限制，如果验证成功，进入manageServlet，否则error.html*/
        //验证成功 => manageServlet
        String pwd = request.getParameter("pwd");
        System.out.println("pwd= " + pwd); // 这里不是通过form表单提交得到的数据，返回的是null
        // src/com/hspedu/servlet/response/homework/MyPayServlet.java  里通过表单提交的数据，返回空串 和input标签中的name属性有关
        //首先，如果出现null值的现象，说明jsp页面中input没有该name属性或没有给该属性赋值，在servlet中利用request.getparameter("username")获取值时就会出现null值的现象。
        //1、当name属性存在,但是没有值的时候后台用request.getParameter(“name”)获得的是空字符串""。
        //2、当没有name属性或者属性值与后台的getParameter()中的参数不一致时,request.getParameter(“name”)获取的值是null。
        if (pwd == null || "".equals(pwd)){
            System.out.println("不可以输入空串或null值");
        }
        if ("666666".equals(pwd)){
            System.out.println("验证成功");
            // 获取Session
            HttpSession session = request.getSession();
            System.out.println("LoginCheckServlet sid= " + session.getId());
            // 给Session存放数据
            session.setAttribute("pwd","666666");
            System.out.println("创建/操作Session成功！");


            //请求转发
            System.out.println("转发到manageServlet");
            request.getRequestDispatcher("/manageServlet").forward(request,response);

            //重定向 是浏览器在解析！！！
            //response.sendRedirect("/manageServlet");
        }else {
            //验证失败 => error.html
            //请求转发
            //老韩解读  1. /manageServlet写的是 要转发的servlet的url
            //        2. / 会被解析成 /servlet
            //        3. forward(request, response) 表示把当前servlet的request对象和response对象，传递给下一个servlet使用
            // 在 RequestDispatcher 接口中，forward() 方法可以实现请求转发
            // API中的描述：将请求从一个 servlet 转发到服务器上的另一个资源（servlet、JSP 文件或 HTML 文件）。
            //request.getRequestDispatcher("/error.html"); // 跳转失败
            //request.getRequestDispatcher("/error.html").forward(request,response); // 跳转成功

            //重定向 是浏览器在解析！！！
            //获取项目路径 Application context
            //ServletContext servletContext = request.getServletContext();
            //String contextPath = servletContext.getContextPath(); 与下面的写法等价！！！

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/error.html"); // 浏览器地址栏：http://localhost:8080/error.html
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
