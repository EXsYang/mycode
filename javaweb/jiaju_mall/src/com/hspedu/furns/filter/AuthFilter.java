package com.hspedu.furns.filter;

import com.google.gson.Gson;
import com.hspedu.furns.entity.Admin;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangda
 * @description: authority 授权验证
 * AuthFilter 权限过滤器
 * @create 2023-08-08-17:03
 */
public class AuthFilter implements Filter {

    //后面我们把要排除的url放入到excludedUrls
    private List<String> excludedUrls;

    public void init(FilterConfig config) throws ServletException {
        System.out.println("AuthFilter init() 被调用...");
        //获取到配置excludedUrls
        String strExcludedUrls = config.getInitParameter("excludedUrls");
        String[] splitUrls = strExcludedUrls.split(",");
        //将 splitUrl 转成 List
        //Java基础常用类 Arrays
        excludedUrls = Arrays.asList(splitUrls);


    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("AuthFilter doFilter() 被调用...");

        HttpServletRequest request = (HttpServletRequest) req;

        // GET /jiaju_mall/views/manage/manage_login.jsp HTTP/1.1
        // 得到url
        String url = request.getServletPath();
        System.out.println("url=" + request.getServletPath());
        // url=/views/manage/manage_login.jsp
        //System.out.println("url=" + request.getRequestURL());
        // url=http://localhost:8080/jiaju_mall/views/manage/manage_login.jsp

        //判断是否要验证
        if (!excludedUrls.contains(url)) {
            // 如果排除的urls里包含此次过滤器匹配上的url 就放行 取反就不放行进行校验
            // 即如果排除的urls 中不包含此次匹配上的url 就进行下面的校验！
            //得到session中的member对象
            Member member = (Member) request.getSession().getAttribute("member");

            //如果member不为null, 还应当判断该member是不是admin, 在根据获取到的url
            //进行相应的处理, 如果该用户不是admin, 但是他访问了后台, 就转到首页即可
            //前面听过+Java基础=>独立完成==> 自己动脑筋

            //即用户登录后 不可以访问管理员专属页面
            if (member != null) {

                if (!"admin".equals(member.getUsername())) {
                    // 登录的是会员 需要做相应的处理
                    // 如果访问的是管理员专属页面 就直接返回到首页
                    // 其他如果是访问的会员页面 不需要做处理 直接放行 就不写限制条件了
                    // 只需要对登录的会员 限制访问管理员专属页面的访问即可
                    //管理员专属页面 包括 views/manage/*.jsp 和 /manage/furnServlet

                    // 使用正则表达式 如果url 包含manage 就返回首页
                    //


                    String content = url;
                    String regStr = "/manage/";
                    //1. 先创建一个Pattern对象 ， 模式对象, 可以理解成就是一个正则表达式对象
                    Pattern pattern = Pattern.compile(regStr);
                    //2. 创建一个匹配器对象
                    //理解： 就是 matcher(匹配器) 匹配器按照 pattern(模式/样式), 到 content 文本中去匹配
                    //找到就返回true, 否则就返回false
                    Matcher matcher = pattern.matcher(content);

                    if (matcher.find()) {
                        // 找到了 说明该url 包含/manage/
                        System.out.println("该url 包含 --" + matcher.group(0) + "-- 是管理员专属页面 会员无权访问！");
                        // 直接返回首页
                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                        // 下面的代码不走了
                        return;
                    }
                    // 如果没有匹配到url 包含/manage/  就放行 不做处理


                } else {
                    // 登录的是管理员 不做处理 直接放行 管理员可以访问所有页面
                    System.out.println("欢迎您 管理员admin 可以访问管理员专属页面 ");
                }


                // 只是对象进行比较 把member对象写在前面也是可以的 因为没有 member.什么东西
                //if (member == null) {
            } else {
                // 到这说明 没有登录过


                // 判断该请求是否为ajax请求 如果是ajax请求 就返回一个ajax的json对象url
                if (!WebUtils.isAjaxRequest(request)) {
                    // 不是ajax 请求 走原来的逻辑
                    // 转发到用户登录页面
                    // 请求转发不会走过滤器！！！
                    // 直接转发到/views/member/login.jsp 返回给浏览器这个事就结束了
                    System.out.println("AuthFilter doFilter 中 转发到用户登录页面");
                    req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
                    // 重定向是会走过滤器的！！！
                    // 浏览器请求访问/views/member/login.jsp 与过滤器匹配上了 在没有登录时
                    // 会走下面的重定向 重定向后的页面又和过滤器配置的url-pattern 匹配上 进入
                    // 过滤器的doFilter 判断没有登录 再次请求转发
            /*此页面不能正确地重定向
            Firefox 检测到该服务器正在将指向此网址的请求无限循环重定向。
                有时候禁用或拒绝接受 Cookie 会导致此问题。
            */
                    //System.out.println("AuthFilter doFilter 中重定向到用户登录页面");
                    //((HttpServletResponse)resp).sendRedirect(request.getContextPath() + "/views/member/login.jsp");

                } else {
                    // 是ajax请求 需要返回一个json对象 因为前端使用的$.getJSON()接收的

                    HashMap<String, Object> resultMap = new HashMap<>();
                    resultMap.put("url", "views/member/login.jsp");

                    // 将Map 转为JSON格式
                    String resultJson = new Gson().toJson(resultMap);
                    // 返回结果
                    resp.getWriter().write(resultJson);
                }

                return;// 直接返回 不走了
            }
        }

        // 匹配上了排除的url 或者 登录过 直接放行 继续访问请求的资源
        chain.doFilter(req, resp);


        // 没有登录的直接过滤掉 返回登录界面
        // manage 管理员管理目录下的文件 用户不可以直接访问
        // 需要是管理员登录才可以访问
        // 购物车的订单页面 不可以直接访问
        //HttpSession session = ((HttpServletRequest) req).getSession();
        //String admin = (String)session.getAttribute("admin");

        //if (admin != null){
        //
        //}

        // equals() 可以传入null 返回false
        //if ("admin".equals(admin)){
        //    chain.doFilter(req, resp);
        //}else {
        //    // 直接转发到 登录界面
        //    req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
        //
        //}


    }

    public void destroy() {
        System.out.println("AuthFilter destroy() 被调用...");
    }

}
