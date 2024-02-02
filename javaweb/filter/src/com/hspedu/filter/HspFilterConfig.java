package com.hspedu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author yangda
  老师解读： 演示FilterConfig使用
 * @create 2023-06-20-19:54
 */
public class HspFilterConfig implements Filter {
    private String ip;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HspFilterConfig init() 被调用..");
        // 128.12 禁止，返回登录界面
        //通过filterConfig 获取相关的参数
        String filterName = filterConfig.getFilterName();
        ip = filterConfig.getInitParameter("ip");
        ServletContext servletContext = filterConfig.getServletContext();
        //可以获取到该filter所有的配置参数名
        Enumeration<String> initParameterNames =
                filterConfig.getInitParameterNames();

        //遍历枚举
        while (initParameterNames.hasMoreElements()) {
            System.out.println("初始化参数名字= " + initParameterNames.nextElement());
        }
        System.out.println("ip= " + ip);
        System.out.println("filterName= " + filterName);
        System.out.println("servletContext= " + servletContext);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HspFilterConfig doFilter 被调用...");
        //获取浏览器ip
        //String remoteAddr = servletRequest.getRemoteAddr();
        //System.out.println("浏览器ip=" + remoteAddr);
        //if (remoteAddr.startsWith(ip)){ // 如果请求地址以 init-param 中配置的IP开头，打回登录界面
        //    System.out.println("doFilter 转发到login.jsp");
        //    servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        //
        //}

        //通过forbidden ip 来进行控制
        //先获取到访问ip
        String remoteAddr = servletRequest.getRemoteAddr();
        if(remoteAddr.contains(ip)) {
            System.out.println("封杀该网段..");
            servletRequest.getRequestDispatcher("/login.jsp").
                    forward(servletRequest,servletResponse);
            return; //直接返回
        }

        //继续访问目标资源
        filterChain.doFilter(servletRequest,servletResponse);
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


    }

    @Override
    public void destroy() {

    }
}
