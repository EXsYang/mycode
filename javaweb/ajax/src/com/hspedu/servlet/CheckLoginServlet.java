package com.hspedu.servlet;

import com.google.gson.Gson;
import com.hspedu.javabean.User;
import com.hspedu.service.UserService;
import com.hspedu.utils.JDBCUtilsByDruid;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-06-28-19:38
 */
public class CheckLoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckLoginServlet 被调用...");
        // 接收浏览器发送过来的数据
        //request.setCharacterEncoding("utf-8");
        //request.getRequestDispatcher("").forward(request,response);

        String uname = request.getParameter("uname");
        //String uname1 = request.getParameter("uname1"); //不存在的uname1
        // 原生 ajax 发过来的是处理过的中文，不用设置
        System.out.println("uname =" + uname); //uname ="" getParameter存在uname时，前端什么都不填返回空串""
        //System.out.println("uname运行类型 =" + uname.getClass()); // class java.lang.String
        //System.out.println("uname1 =" + uname1); //uname1 =null getParameter不存在uname1时，返回null
        //System.out.println("uname1运行类型 =" + uname1.getClass()); // 报错

        if (uname != null){

            //String path = JDBCUtilsByDruid.class.getResource("/").getPath();// 得到的是工作目录，而不是源码目录
            //System.out.println("JDBCUtilsByDruid path= " + path);

            // 到数据库去查询 将ajax发送来的数据传入 进行校验
            //List<User> users = userService.selectAll();
            // 遍历数据库取到的数据进行连接测试
            //for (User user : users) {
            //    System.out.println("遍历数据库数据："+user);
            //}

            //if ("king".equals(uname)){

            //到DB查询
            //如果有就返回user对象，否则，返回的是null
            //if (!userService.selectUserName(uname)){
            User user = userService.selectUser(uname);

            if (user != null){//说明用户名存在..,返回该user的json格式数据字符串

                //System.out.println("该用户被注册");
                System.out.println("数据库验证方式该用户被注册");

                // 返回用户信息  这里可以使用selectUser的返回值进行处理
                //User king = new User(100, "king", "666", "404040@qq.com");
                // 将被注册了的用户的信息返回到浏览器界面！！



                // 创建gson对象
                Gson gson = new Gson();
                // 将JavaBean对象 => json字符串
                //String strKing = gson.toJson(king);
                String strUser = gson.toJson(user);

                // 将json字符串 返回给前端页面
                // 设置编码方式 解决中文乱码问题
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                //writer.write(strKing);
                writer.write(strUser);
                writer.flush();
                writer.close();

            }else {
                //System.out.println("该用户可以使用");
                System.out.println("数据库验证方式该用户可以使用");
            // 返回空字符串
                // 将json字符串 返回给前端页面
                // 设置编码方式 解决中文乱码问题
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write("");
                writer.flush();
                writer.close();


            }

        }


        //测试表单提交的数据是否为乱码 email
        // 结果：表单提交 服务器端如果没设置request.setCharacterEncoding("utf-8"); 中文出现乱码
        // 而ajax请求 服务器端没设置request.setCharacterEncoding("utf-8");不会出现中文乱码
        //String email = request.getParameter("email");
        //System.out.println("email =" + email); //uname =""email =youé????±
        //System.out.println("email运行类型 =" + email.getClass()); // email运行类型 =class java.lang.String


        //==================老韩代码如下：=========================================
/*        //System.out.println("CheckUserServlet 被调用....");

        //接收ajax提交的数据
        String uname = request.getParameter("uname");
        System.out.println("uname= " + uname);

        response.setContentType("text/html;charset=utf-8");
        //到DB查询
        //如果有就返回user对象，否则，返回的是null
        User user = userService.getUserByName(uname);
        if (user != null) {//说明用户名存在..,返回该user的json格式数据字符串
            Gson gson = new Gson();
            String strUser = gson.toJson(user);
            response.getWriter().write(strUser);

        } else {
            response.getWriter().write("");
        }

        ////假定用户名为 king , 就不可用, 其它用户名可以
        //if("king".equals(uname)) {//不能使用king用户名
        //    //后面这个信息，是从DB获取
        //    User king = new User(100, "king", "666", "king@sohu.com");
        //    //把 king 转成 json字符串
        //    String strKing = new Gson().toJson(king);
        //    //返回
        //    response.getWriter().write(strKing);
        //} else {
        //    //如果用户名可以用，返回""
        //    response.getWriter().write("");
        //}
        */

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
