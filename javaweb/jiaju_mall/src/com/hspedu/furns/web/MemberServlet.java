package com.hspedu.furns.web;

import com.google.gson.Gson;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.MemberService;
import com.hspedu.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author yangda
 * @description:
 * @create 2023-07-16-1:44
 */
public class MemberServlet extends BasicServlet {
    // 提供MemberService 属性
    private MemberService memberService = new MemberServiceImpl();

    //protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    System.out.println("MemberServlet 被调用...");
    //
    //    //if (action != null){
    //    //    if ("login".equals(action)){
    //    //        // 请求转发到 loginServlet
    //    //        request.getRequestDispatcher("/loginServlet").forward(request,response);
    //    //    }else {
    //    //        // 请求转发到 registerServlet
    //    //        request.getRequestDispatcher("/registerServlet").forward(request,response);
    //    //    }
    //    //}else {
    //    //    System.out.println("request 中 通过表单提交的 name=action 属性不存在");
    //    //}
    //
    //    // 获取action
    //    String action = request.getParameter("action");
    //    System.out.println("action= " + action);
    //
    //    // 判断，调用不同的方法
    //    if ("login".equals(action)){
    //        login(request, response);
    //    }else if ("register".equals(action)){
    //        register(request, response);
    //    }else {
    //        System.out.println("请求参数action有误");
    //        // 提示信息
    //        response.getWriter().write("请求参数action有误");
    //    }
    //
    //
    //}

    //protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    doPost(request, response);
    //}


    /**
     * 验证某个用户是否已经存在db中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void isExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //http://localhost:8080/jiaju_mall/memberServlet?action=isExistsUsername&username=yangda

         //下面是自己写的逻辑
        //System.out.println("MemberServlet isExistsUsername() 被调用...");
        //String username = req.getParameter("username");
        //
        //System.out.println("username=" + username);
        //
        //// 构建Gson对象
        //Gson gson = new Gson();
        //
        //
        //// 根据前端传过来的用户名 去数据库查询是否有该用户名 如果有就返回提示信息
        //if (memberService.isExistsByUsername(username)){
        //    // 返回true 用户名存在 不可以注册
        //    // 这里存在时返回了一个自己新建的Member
        //
        //    Member member = memberService.queryMemberByUsername(username);
        //    //得到Member对象的字符串形式
        //    String strMember = gson.toJson(member);
        //
        //
        //    resp.setContentType("text/json;charset=utf-8");
        //    PrintWriter writer = resp.getWriter();
        //    writer.write(strMember);
        //    writer.flush();
        //    writer.close();
        //}else{
        //    //用户名不存在 可以注册
        //    // 给浏览器返回一个 存在的对象 用于前端判断 用户名是否存在
        //    // 给浏览器返回一个 不存在的对象 用于前端判断 用户名是否存在
        //    Member member = new Member(-1, "", "", "");
        //
        //    //得到Member对象的字符串形式
        //    String strMember = gson.toJson(member);
        //
        //
        //    resp.setContentType("text/json;charset=utf-8");
        //    PrintWriter writer = resp.getWriter();
        //    writer.write(strMember);
        //    writer.flush();
        //    writer.close();
        //
        //}

    //   下面是老韩写的逻辑===================================
        //1. 获取用户名
        String username = req.getParameter("username");
        //2. 调用service
        boolean isExistUsername = memberService.isExistsByUsername(username);
        //3. 思路
        //(1). 如何返回json格式 [不要乱写, 要根据前端的需求来玩]
        //(2). 因为目前前端和后端都是我们自己写, 我们自己定
        //(3) {"isExist":false};

        //4. 先使用最最简单拼接 => 一会老师改进[可扩展], 印象就会非常深刻[一通百通]
        //String resultJson = "{\"isExist\": " + isExistUsername + "}";
        //=> 将要返回的数据=>map=>json
        Map<String, Object> resultMap = new HashMap<>();
        //isExistUsername 注意这里存进去的是一个布尔值！
        resultMap.put("isExist", isExistUsername);
        //resultMap.put("email","jack@sohu.com");
        //resultMap.put("job","java");
        String resultJson = new Gson().toJson(resultMap);

        //5. 返回
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(resultJson);







    }

    /**
     * 处理会员注册功能方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberServlet register() 被调用...");
        //接收用户注册信息-> 一定要去看前端页面字段..
        //用户名
        // 获取服务器端数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // 获取用户输入的验证码code
        String code = request.getParameter("code");

        // 从session中获取正确的验证码
        //Object token = request.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        String token = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 拿到后 立即删除session验证码->防止该验证码被重复使用
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //如果token为空，并且和用户提交的验证码一致,就继续
        //if (token != null && token.toString().equalsIgnoreCase(code)) {

        if (token != null && token.equalsIgnoreCase(code)) {
            // 说明用户发来的验证码和 session保存的验证码确实是一样
            // 继续走下面注册用户的逻辑代码

            if (username != null) {
                System.out.println("username= " + username);

            }
            if (password != null) {
                System.out.println("password= " + password);

            }
            if (email != null) {
                System.out.println("email= " + email);

            }

            // 浏览器的用户数据进行注册操作

            // //判断这个用户名是不是可用
            if (!memberService.isExistsByUsername(username)) {
                // 用户名不存在可以进行注册
                System.out.println("用户名 " + username + " 不存在,可以进行注册");

                // 构建Member对象
                Member member = new Member(null, username, password, email);
                if (memberService.register(member)) {
                    System.out.println("注册成功");
                    // 请求转发到 register_ok.jsp   在服务器端
                    request.getRequestDispatcher("/views/member/register_ok.jsp").forward(request, response);

                } else {
                    System.out.println("注册失败");
                    // 请求转发到 register_fail.jsp
                    request.getRequestDispatcher("/views/member/register_fail.jsp").forward(request, response);

                }
            } else {
                // 用户名存在 不可以进行注册      //返回注册页面
                System.out.println("用户名 " + username + " 存在,不可以进行注册");
                // 请求转发到 login.jsp //后面可以加入提示信息...
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);

            }
        }else {
            // 走到这里 是用户输入的验证码和保存在服务器端session中的验证码不一样
            // 携带错误信息 用于回显错误信息
            request.setAttribute("msg","输入的验证码有误");

            // 将错误的用户注册信息 进行回显
            request.setAttribute("username",username);
            request.setAttribute("email",email);

            // 携带信息 用于模拟点击事件时 作为判断条件
            // 在注册失败转发到login.jsp页面时 到底显示的是哪一个tab表单
            request.setAttribute("active","register");

            // 请求转发到login.jsp
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);

        }
    }

    /**
     * 处理会员登录功能的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberServlet login 被调用...");

        // 从浏览器请求中获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null) {
            System.out.println("username= " + username);
        }
        if (password != null) {
            System.out.println("password= " + password);
        }


        // 根据传入的用户名和密码构建Member对象
        Member member = new Member(null, username, password, null);

        //if(memberService.login(username,password)){
        member = memberService.login(member);
        if (member != null) {
            System.out.println("登录成功！");
            // 登录成功后 将用户信息带给前端进行显示
            //request.setAttribute("member",member);
            //request.setAttribute("username",member.getUsername());
            String memberUsername = member.getUsername();
            if ("admin".equals(memberUsername) ){
                // 如果是管理员
                System.out.println("欢迎您 管理员【"+ memberUsername +"】！");
                // 登录成功后 将member设置到session域空间中
                HttpSession session = request.getSession();
                //session.setAttribute("admin", memberUsername);
                session.setAttribute("member", member);


                // 服务器端 请求转发 到管理员专属登录成功页面 .jsp
                // 请求转发不会经过过滤器！！
                /**
                 * 过滤器过滤的是URI的请求
                 *
                 * 所以如果是请求转发的话，URI是没有变化的，也就是说没有发起一次新的请求
                 * ，所以不会再重新经过过滤器的，而将当前的转发页面过滤掉，只有你重新发起请求
                 * ，才会重新经过过滤器，从而可能会导致页面被过滤掉。
                 *
                 *  过滤器不会拦截服务器内部的请求转发，但是如果是拦截器interceptor 也会拦截服务器内部的请求转发路径
                 */
                request.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(request, response);


            }else{
                // 如果不是管理员
                // 登录成功后 将member设置到session域空间中
                HttpSession session = request.getSession();
                session.setAttribute("member", member);


                // 服务器端 请求转发 到login_ok.jsp
                request.getRequestDispatcher("/views/member/login_ok.jsp").forward(request, response);
            }



        } else {
            System.out.println("登录失败,请重新登录");
            // 请求转发到login.html 重新登录
            // 如果登录失败 将错误信息和登录会员名放入到request域中
            request.setAttribute("username", username);
            request.setAttribute("msg", "用户名或密码错误");

            //request.getRequestDispatcher("/views/member/login.html").forward(request,response);
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);

        }
    }

    /**
     * 注销用户 从session中拿掉
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MemberServlet logout() 被调用...");

        // 自己写的
        //// 获取session对象
        //HttpSession session = req.getSession();
        //// 删除member属性
        //session.removeAttribute("member");
        //
        //// 重定向到网站的入口页面 index.jsp
        //resp.sendRedirect(req.getContextPath() + "/index.jsp");

        //老韩的思路如下
        //销毁当前用户的session
        //public void invalidate() 让当前 Session 会话立即无效
        //invalidate: 使无效
        req.getSession().invalidate();
        //重定向到网站首页-> 目的是刷新首页
        //req.getContextPath() => http://localhost:8080/jiaju_mall
        resp.sendRedirect(req.getContextPath());


    }
}
