package com.hspedu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-06-20-16:00
 */

/**
 * 老韩解读
 * 1. filter在web项目启动时, 由tomcat 来创建filter实例, 只会创建一个
 * 2. 会调用filter默认的无参构造器, 同时会调用 init方法, 只会调用一次
 * 3. 在创建filter实例时，同时会创建一个FilterConfig对象,并通过init方法传入
 * 4. 通过FilterConfig对象，程序员可以获取该filter的相关配置信息
 * 5. 当一个http请求和该filter的url-patter匹配时，就会调用doFilter方法
 * 6. 在调用doFilter方法时,tomcat会同时创建ServletRequest 和 ServletResponse 和 FilterChain对象
 * , 并通过doFilter传入.
 * 7. 如果后面的请求目标资源(jsp,servlet..) 会使用到request，和 response，那么会继续传递 和目标资源的request,response是同一个
 * 8. 老师的提醒:到javaweb - ssm - springboot , 有 浏览器和 web服务器(tomcat)参与, 而这两个部分不是我们
 *    程序员自己写，所以理解起来比 java se要困难!!!
 * 9. 过滤器不会拦截服务器内部的请求转发，但是如果是拦截器interceptor 也会拦截服务器内部的请求转发路径
 */
public class ManageFilter implements Filter {
    private int count = 0;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //当Tomcat 创建 Filter创建，就会调用该方法，进行初始化
        //老韩提醒：回忆我们自己实现tomcat底层机制+servlet程序， 就会了然
        System.out.println("ManageFilter init被调用...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //当每次调用该filter时，doFilter就会被调用
        System.out.println("ManageFilter doFilter被调用..." + " 第" + (++count) + "次");
        System.out.println("ManageFilter_servletRequest=" + servletRequest);

        //到每次调用该filter时，doFilter就会被调用

        //如果这里，没有调用继续请求的方法，则就停止
        //如果继续访问目标资源-> 等价于放行

        //老师说明：在调用过滤器前，servletRequest对象=request已经被创建并封装
        //所以：我们这里就可以通过servletRequest获取很多信息, 比如访问url , session
        //比如访问的参数 ... 就可以做事务管理，数据获取，日志管理等
        //获取到session
        //可以继续使用 httpServletRequest 方法.
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("输入密码=" + httpServletRequest.getParameter("password"));
        //获取username session对象, 还可以继续使用
        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");
        if (username != null){
            //老韩解读filterChain.doFilter(servletRequest, servletResponse)
            //1. 继续访问目标资源url
            //2. servletRequest 和 servletResponse 对象会传递给目标资源/文件
            //3. 一定要理解filter传递的两个对象，再后面的servlet/jsp 是同一个对象(指的是在一次http请求)
            System.out.println("servletRequest=" + servletRequest);
            System.out.println("日志信息==");
            System.out.println("访问的用户名=" + username.toString());
            System.out.println("访问的url=" + httpServletRequest.getRequestURL());
            System.out.println("访问的getServletPath url=" + httpServletRequest.getServletPath());
            System.out.println("访问的IP=" + httpServletRequest.getRemoteAddr());

            System.out.println("登录的用户名是=" + username);
            System.out.println("sid=" + session.getId());

            //继续访问目标资源 如果没有这句话filterChain.doFilter还是访问不到
            /**
             * GPT的回答：
             * 如果在过滤器中没有调用filterChain.doFilter(servletRequest, servletResponse);
             * ，那么请求将会被截断，不会继续传递给后续的过滤器链或目标资源。这就意味着后面的
             * 静态资源或其他请求处理流程将无法执行，客户端也将不会收到任何响应。
             *
             * 调用filterChain.doFilter(servletRequest, servletResponse);的目的
             * 是继续将请求传递给下一个过滤器（如果有的话）或目标资源。如果这行代码没有被执行，
             * 请求处理链就会停滞在当前过滤器，不会继续往下执行。
             *
             * 在过滤器中，通常在适当的时候调用filterChain.doFilter，以确保请求能够继续往后传递。
             * 如果在过滤器中不调用该方法，整个请求处理链就会被中断，后续的处理逻辑将不会执行。
             *
             * 要注意的是，如果不调用filterChain.doFilter，可能导致请求无法到达目标资源，
             * 浏览器会一直处于等待状态，直到达到超时或其他错误。因此，在编写过滤器时，
             * 确保适时地调用filterChain.doFilter是很重要的。
             */

            System.out.println("filterChain.doFilter 继续访问目标资源");
            filterChain.doFilter(servletRequest, servletResponse);
        }else {//说明没有登录过..回到登录页面
            // 转发到登录界面
            System.out.println("session的username属性为空 说明没有登录过..回到登录页面");
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        //当 filter被销毁时，destroy就会被调用
        System.out.println("ManageFilter destroy被调用...");
    }
}
