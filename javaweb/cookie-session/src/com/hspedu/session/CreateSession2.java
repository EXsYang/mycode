package com.hspedu.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description: Session 生命周期
 *
 *   <!-- ==================== Default Session Configuration ================= -->
 *   <!-- You can set the default session timeout (in minutes) for all newly   -->
 *   <!-- created sessions by modifying the value below.                       -->
 *
 *     <session-config>
 *         <session-timeout>30</session-timeout>  // 默认30min
 *     </session-config>
 *
 *
 * @create 2023-06-09-12:11
 */
public class CreateSession2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CreateSession2 被调用...");
        //创建session
        HttpSession session = request.getSession();
        System.out.println("CreateSession2 sid= " + session.getId());
        // 生命周期 是放在服务器端维护的，不会因为浏览器的关闭而销毁！！！！
        //指定在 servlet 容器使此会话失效之前客户端请求之间的时间间隔，以秒为单位。负数时间指示会话永远不会超时。
        // Inactive:未激活;活动状态;不活动状态;无活动;不激活的
        // Interval : 间隔;(时间上的)间隙;间歇;(戏剧、电影或音乐会的)幕间休息;休息时间;(其他事情)穿插出现的间隙;音程
        //设置生命周期为 60s

        session.setMaxInactiveInterval(60);
        session.setAttribute("u","tom");
        session.setAttribute("pwd","666");
        session.setAttribute("pwd","777"); //这里会替换上面的666

        // 4.给浏览器发送回复信息
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("<h1>创建session成功, 设置生命周期60s</h1>");
        writer.flush();
        writer.close();








    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
