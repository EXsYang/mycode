package com.hspedu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yangda
 * @description:  过滤器不会拦截服务器内部的请求转发，但是如果是拦截器interceptor 也会拦截服务器内部的请求转发路径
 * FilterChain 注意事项和细节
 * 1. 多个 filter 和目标资源在一次 http 请求，在同一个线程中
 * 2. 当一个请求 url 和 filter 的 url-pattern 匹配时, 才会被执行, 如果有多个匹配上，就会
 *    顺序执行，形成一个 filter 调用链(底层可以使用一个数据结构搞定)
 * 3. 多个 filter 共同执行时,因为是一次 http 请求, 使用同一个 request 对象
 * 4. 多个 filter 执行顺序，和 web.xml 配置顺序保持一致. 5. chain.doFilter(req, resp)方法 将执行下一个过滤器的 doFilter
 *    方法, 如果后面没有过滤器，则执行目标资源。
 * 6. 小结：注意执行过滤器链时, 顺序是(用前面的案例分析) Http请求 -> A 过滤器 dofilter()
 *    -> A 过滤器前置代码 -> A 过滤器 chain.doFilter() -> B 过滤器 dofilter() -> B 过滤器前置代
 *    码 -> B过滤器 chain.doFilter() -> 目标文件 -> B过滤器后置代码 -> A过滤器后置代码 ->
 *    返回给浏览器页面/数据
 *
 *
 *
 * @create 2023-06-20-22:28
 */
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("AFilter---> 线程id=" +
                Thread.currentThread().getId());

        System.out.println("AFilter doFilter 的前置代码...");
        System.out.println("执行 AFilter doFilter()");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("AFilter doFilter 的后置代码...");
    }

    @Override
    public void destroy() {

    }
}
