package com.hspedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-07-25-0:12
 */
public class DispatcherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DispatcherServlet 被调用...");
        //老师解读 正常情况下：servlet url-pattern书写格式为 /servlet时的规则
        //1. 在服务器端 解析第一个 /时，会被解析成 http://ip:port/项目名[application context]/
        //   老韩再补充： 项目名=> 说成 application context
        //2. "/login.html" => 被解析 http://ip:port/项目名/login.html
        //request.getRequestDispatcher("/login.html").forward(request,response);
        //3. 在服务器进行转发时, 没有 / 就按照默认的方式参考定位 http://ip:port/项目名/
        //   老师建议，仍然使用上面的



        // 特殊情况：servlet—mapping url-pattern 书写格式为 多级目录格式 时
        // 服务器端请求转发路径解析的规则如下：
        /*
         * 相关的文件 web/DispatcherTest.html
         *           web/test/login.html
         */
        // /webpath/test/login.html 没有写斜杠时会参考对应Servlet 的
        // <url-pattern>/test/dispatcherServlet</url-pattern>里写的路径
        // 拿掉‘dispatcherServlet’ 后使用前面的 /test/ 拼接 请求转发里写的地址 login.html
        // 拼接后为/test/login.html 其中第一个斜杠被解析为 项目名/
        // 最终结果被服务器端解析为 /webpath/test/login.html
        // 服务器端访问的完整是路径http://localhost:8080/webpath/test/login.html
        // 如果此servlet有多级目录 同时再转发时没写/ 相当于写成了/test/login.html
        // 会把url-pattern 中的多级目录结构最后一个斜杠后的Servlet拿掉
        // 再和没写斜杠的字符串进行拼接
        request.getRequestDispatcher("login.html").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
