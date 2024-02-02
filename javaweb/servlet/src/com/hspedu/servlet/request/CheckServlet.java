package com.hspedu.servlet.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-25-19:38
 */
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckServlet 被调用 ... ");
        //根据用户名，确认用户身份
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");

        if ("tom".equals(username)){
            request.setAttribute("role","管理员");//setAttribute()设置 request对象的属性
            // request是域对象,把数据带给其他web资源
        }else{
            request.setAttribute("role","普通用户");
        }
        // 获取分发器  这个类传进来的request 与/manageServlet 的 request是同一个对象，因为是同一个请求

        //请求转发注意事项和细节
        //1. 浏览器地址不会变化(地址会保留在第 1 个 servlet 的 url)
        //2. 在同一次 HTTP 请求中，进行多次转发，仍然是一次 HTTP 请求
        //3. 在同一次 HTTP 请求中，进行多次转发，多个 Servlet 可以共享 request 域/对象的数据(因
        //   为始终是同一个 request 对象)
        //4. 可以转发到 WEB-INF 目录下(后面做项目使用)
        //5. 不能访问当前 WEB 工程外的资源


        RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("/manageServlet");
        //老韩解读  1. /manageServlet写的是 要转发的servlet的url
        //        2. / 会被解析成 /servlet
        //        3. forward(request, response) 表示把当前servlet的request对象和response对象，传递给下一个servlet使用
                    // 在 RequestDispatcher 接口中，forward() 方法可以实现请求转发
                    // API中的描述：将请求从一个 servlet 转发到服务器上的另一个资源（servlet、JSP 文件或 HTML 文件）。
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
