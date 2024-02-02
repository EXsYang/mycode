// package com.hspedu.springboot.filter;
//
// import org.springframework.stereotype.Component;
//
// import javax.servlet.*;
// import java.io.IOException;
//
// /**
//  * @author yangda
//  * @create 2023-12-11-17:13
//  * @description:
//  * 这种方式配置的过滤器，如果没有做任何配置，默认会拦截所有的http请求
//  * 而且也没有办法配置url-pattern
//  * 注意该过滤器需要注入到容器中才会生效，即需要一个@Component注解
//  */
// @Component
// public class HspFilter implements Filter {
//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {
//
//     }
//
//     @Override
//     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//         System.out.println("HspFilter doFilter()被调用...");
//         //放行，如果不放行后面是看不到的,即会被该过滤器拦截
//         filterChain.doFilter(servletRequest,servletResponse);
//     }
//
//     @Override
//     public void destroy() {
//
//     }
// }
