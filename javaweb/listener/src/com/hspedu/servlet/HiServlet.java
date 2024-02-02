package com.hspedu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yangda
 * @description: 监听器相关Servlet
 *  还有两个不常用的Listener 2个感知监听器 一对一 不需要配置在web.xml中
 *  HttpSessionBindingListener  Session里面绑定的对象在钝化(持久化到本地磁盘)之前 它能够监听到
 *  HttpSessionActivationListener Session里面绑定的对象在将被钝化(持久化到本地磁盘)或者是将被激活的时候 它能够监听到
 * @create 2023-06-19-18:09
 */
public class HiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HiServlet 被调用...");
        System.out.println("HttpServletRequest request：" + request);
        System.out.println("HttpServletRequest request运行类型：" + request.getClass());
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("age",100);
        servletContext.setAttribute("age",400);
        servletContext.removeAttribute("age");

        // 获取session对象 不写这句话就不会创建session对象，即使在监听器类写了一个httpSessionEvent.getSession();
        //httpSessionEvent.getSession();这句话并不会创建session对象！！！！
        // 还没有访问hiServlet 默认访问首页面http://localhost:8080/listener/  默认访问的是web目录下的index.jsp
        // 就创建了两个Session对象 是系统生成的 和我们没有关系！不想看清理掉output,重新发布项目就没有了！
        HttpSession session = request.getSession();
        session.setAttribute("name","老韩");
        session.setAttribute("name","老张");
        session.removeAttribute("name");

        request.setAttribute("job","老师");
        request.setAttribute("job","程序员");
        request.removeAttribute("job");

        System.out.println("HiServlet 处理完毕...");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
