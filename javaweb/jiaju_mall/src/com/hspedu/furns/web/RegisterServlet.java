//package com.hspedu.furns.web;
//
//import com.hspedu.furns.entity.Member;
//import com.hspedu.furns.service.MemberService;
//import com.hspedu.furns.service.impl.MemberServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author yangda
// * @description:
// * @create 2023-07-13-21:27
// */
//public class RegisterServlet extends HttpServlet {
//    //    //定义一个属性MemberService
//    private MemberService memberService = new MemberServiceImpl();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("RegisterServlet 被调用...");
//        //接收用户注册信息-> 一定要去看前端页面字段..
//        //用户名
//        // 获取服务器端数据
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        if (username != null){
//            System.out.println("username= " + username);
//
//        }
//        if (password != null){
//            System.out.println("password= " + password);
//
//        }
//        if (email != null){
//            System.out.println("email= " + email);
//
//        }
//
//        // 浏览器的用户数据进行注册操作
//
//        // //判断这个用户名是不是可用
//        if (!memberService.userExistsByUsername(username)){
//            // 用户名不存在可以进行注册
//            System.out.println("用户名 " + username + " 不存在,可以进行注册");
//
//            // 构建Member对象
//            Member member = new Member(null, username, password, email);
//            if(memberService.register(member)){
//                System.out.println("注册成功");
//                // 请求转发到 register_ok.jsp   在服务器端
//                request.getRequestDispatcher("/views/member/register_ok.jsp").forward(request,response);
//
//            }else{
//                System.out.println("注册失败");
//                // 请求转发到 register_fail.jsp
//                request.getRequestDispatcher("/views/member/register_fail.jsp").forward(request,response);
//
//            }
//        }else{
//            // 用户名存在 不可以进行注册      //返回注册页面
//            System.out.println("用户名 " + username + " 存在,不可以进行注册");
//            // 请求转发到 login.jsp //后面可以加入提示信息...
//            request.getRequestDispatcher("/views/member/login.jsp").forward(request,response);
//
//        }
//
//
//
//
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
//}
