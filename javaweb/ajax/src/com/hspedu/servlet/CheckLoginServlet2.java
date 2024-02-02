package com.hspedu.servlet;

import com.google.gson.Gson;
import com.hspedu.javabean.User;
import com.hspedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2023-07-04-8:19
 */
public class CheckLoginServlet2 extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckLoginServlet2 被调用...");

        //request.setCharacterEncoding("utf-8");  //jquery ajax 发过来的是处理过的中文，不用设置

        //data: {username:$("#uname").val(),//这里我们直接给json, 为啥我要传日期, 为了浏览器缓存
        //        date:new Date()
        //},
        //request.getParameter("username"); 参数名要和前端给的参数名一样，不然得不到数据
        String username = request.getParameter("username");
        // 发过来的是处理过的中文，不用设置request.setCharacterEncoding("utf-8");
        System.out.println("username= " + username);

        User user1 = new User(-1, "", "", "");
        Gson gson = new Gson();
        String strUser1 = gson.toJson(user1);
        //    给浏览器返回User对象的json字符串形式结果   //服务器端什么也没有返回，会导致ajax失败，会进来此函数

        //不写response.setContentType 前端控制台会出现 => XML 解析错误：格式不佳 ... 需要设置一下ContentType
        /*XML 解析错误：格式不佳
        位置：http://localhost:8080/ajax/checkLoginServlet2?username=%E6%9D%A8%E8%BE%BE&date=Tue%20Jul%2004%202023%2016%3A52%3A54%20GMT%2B0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)
        行 1，列 1：*/

        //response.setContentType("text/html;charset=utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(strUser1);
        //writer.write("");
        //writer.flush();
        //writer.close();

      /*  if (username != null){
            // 到数据库查询数据信息并返回User对象
            User user = userService.selectUser(username);
            if (user != null){//说明用户名存在..,返回该user的json格式数据字符串

                //得到User对象的字符串形式
                String strUser = gson.toJson(user);
            //    给浏览器返回User对象的字符串形式结果

                writer.write(strUser);
                writer.flush();
                writer.close();
                System.out.println("2数据库验证方式该用户不可用");

            }else {



                writer.write(strUser1);

                //前端 ajax dataType: "json"  //服务器端给一个空字符串，会导致ajax请求失败
                //writer.write("");
                writer.flush();
                writer.close();
                System.out.println("2数据库验证方式该用户可用");
            }



        }*/


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
