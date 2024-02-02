package com.hspedu.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 韩顺平
 * @version 1.0
 * 请求重定向注意事项和细节
 * 1. 最佳应用场景：网站迁移，比如原域名是 www.hsp.com 迁移到 www.hsp.cn ，但
 *    是百度抓取的还是原来网址.
 * 2. 浏览器地址会发生变化，本质是两次 http 请求.
 * 3. 不能共享 Request 域中的数据，本质是两次 http 请求，会生成两个 HttpServletRequest对象
 * 4. 不能重定向到 /WEB-INF 下的资源
 * 5. 可以重定向到 Web 工程以外的资源， 比如 到 www.baidu.com 【在前面的案例演示】
 * 6. 重定向有两种方式, 推荐使用第 1 种
 *
 */
public class DownServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         System.out.println("DownServlet 被调用");
        // response.setContentType("application/x-tar;charset=utf-8");
        // PrintWriter writer = response.getWriter();
        // writer.print("hi");
        // writer.flush();
        // writer.close();

        //完成了自己业务
        //发出请求重定向-> DownServletNew
        //听老师解读
        //1. sendRedirect 本质就会 返回 302 状态码 Location: /servlet/downServletNew
        //2. 因此 302和 /servlet/downServletNew 是浏览器解析，而不是服务器
        //3. 浏览器在解析 /servlet/downServletNew => http://localhost:8080/servlet/downServletNew
        //4. 动态获取到application context
        String contextPath = getServletContext().getContextPath();
        System.out.println("contextPath= " + contextPath);
        //response.sendRedirect("/servlet/downServletNew");
        response.sendRedirect(contextPath + "/downServletNew");
        //response.sendRedirect("http://www.baidu.com");

        //第二种重定向的写法
        // System.out.println("第二种方式重定向...");
        // response.setStatus(302); //设置http响应的状态码
        // //设置http响应的 Location: /servlet/downServletNew
        // response.setHeader("Location", "/servlet/downServletNew");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
