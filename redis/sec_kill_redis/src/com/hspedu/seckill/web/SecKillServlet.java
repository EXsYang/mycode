package com.hspedu.seckill.web;

import com.hspedu.seckill.redis.SecKillRedis;
import com.hspedu.seckill.redis.SecKillRedisByLua;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author yangda
 * @create 2024-04-13-3:51
 * @description:
 */
public class SecKillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 请求时，模拟生成一个userId
        String userId = new Random().nextInt(10000) + "";

        //2. 获取用户要购买的票的编号
        String ticketNo = request.getParameter("ticketNo");

        //3. 调用秒杀方法
        // boolean isOk = SecKillRedis.doSecKill(userId, ticketNo);

        //3. 调用lua脚本完成秒杀方法
        boolean isOk = SecKillRedisByLua.doSecKill(userId,ticketNo);

        //4. 将结果返回给前端-这个地方可以根据业务需要调整
        // response.getWriter().println(isOk); //注意这里不要带ln
        // 会多出一个换行符号，转为字符串后不会为 “false” 前端的 data == "false" 永远为false
        response.getWriter().print(isOk);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
