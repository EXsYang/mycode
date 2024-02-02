//package com.hspedu.furns.web;
//
//import com.hspedu.furns.entity.Member;
//import com.hspedu.furns.service.MemberService;
//import com.hspedu.furns.service.impl.MemberServiceImpl;
//
//import javax.print.attribute.standard.NumberUp;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author yangda
// * @description:
// * @create 2023-07-14-11:08
// */
//public class LoginServlet extends HttpServlet {
//    private MemberService memberService = new MemberServiceImpl();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("LoginServlet 被调用...");
//
//        // 从浏览器请求中获取数据
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if (username != null){
//            System.out.println("username= " + username);
//        }
//        if (password != null){
//            System.out.println("password= " + password);
//        }
//
//
//        // 根据传入的用户名和密码构建Member对象
//        Member member = new Member(null, username, password, null);
//
//        //if(memberService.login(username,password)){
//        if(memberService.login(member) != null){
//            System.out.println("登录成功！");
//            // 服务器端 请求转发 到login_ok.html
//            request.getRequestDispatcher("/views/member/login_ok.jsp").forward(request,response);
//
//        }else{
//            System.out.println("登录失败,请重新登录");
//            // 请求转发到login.html 重新登录
//            // 如果登录失败 将错误信息和登录会员名放入到request域中
//            request.setAttribute("username",username);
//            request.setAttribute("msg","用户名或密码错误");
//
//            //request.getRequestDispatcher("/views/member/login.html").forward(request,response);
//            request.getRequestDispatcher("/views/member/login.jsp").forward(request,response);
//
//        }
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
