package com.hspedu.springboot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yangda
 * @create 2023-12-12-21:27
 * @description:
 *
 * 开发Filter 并注入 注意实现的是 javax.servlet.Filter;
 * 老韩解读
 * 1. @WebFilter 表示Filter_是一个过滤器，并注入容器
 * 2. urlPatterns = {"/css/*", "/images/*"} 当请求
 *    /css/目录资源或者 /images/目录下资源的时候，会经过该过滤器
 * 3. 老师是直接放行后，在经过拦截器, 拦截器是否拦截要根据拦截器的拦截规则
 *
 * 注意: 过滤器配置的 urlPatterns 也会经过 Spring-Boot 拦截器(根据拦截器的规则)
 * 所以为了看到效果，请在拦截器配置放行 /css/**
 * 在 servlet 匹配全部是 /*
 * , 在 Spring-Boot 是/**
 */
@Slf4j
// @WebFilter(urlPatterns = {"/css/*","/images/*"})
public class Filter_ implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--Filter_ init--");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("--Filter_ doFilter--");
        //为了方便观察过滤器处理的资源，输出url
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("过滤器处理的uri={}",httpServletRequest.getRequestURI());
        //直接放行 如果不放行，就停止了，不往后走了
        //如果在过滤器中没有调用filterChain.doFilter(servletRequest, servletResponse);
        //，那么请求将会被截断，不会继续传递给后续的过滤器链或目标资源。这就意味着后面的
        //静态资源或其他请求处理流程将无法执行，客户端也将不会收到任何响应。
        //实际开发中，根据业务决定如何处理
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("--Filter_ destroy--");
    }
}
