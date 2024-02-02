package com.hspedu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yangda
 * @description:  过滤器不会拦截服务器内部的请求转发，但是如果是拦截器interceptor 也会拦截服务器内部的请求转发路径
 * @create 2023-06-20-22:46
 */
public class TopicFilter implements Filter {
    //属性-> 存放禁用词
    private String[] forbiddenWords = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取禁用词
        String forbiddenWord = filterConfig.getInitParameter("forbidden");
        forbiddenWords = forbiddenWord.split(",");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 查看评论是否有禁用词
        // 拿到评论内容
        // 解决从topic.jsp 提交的中文乱码问题
        servletRequest.setCharacterEncoding("utf-8");  // 确实只设置服务器端在请求中取得的数据的编码方式，不影响对浏览器界面汉字的显示
        // 如下两行代码测试：setCharacterEncoding 是否对浏览器汉字显示有影响 结论：没影响，看错界面了
        //servletRequest.setAttribute("errorInfo", "有禁用词=");
        //servletRequest.getRequestDispatcher("/topic.jsp").forward(servletRequest, servletResponse);
        //判断评论是不是有禁用词
        String content = servletRequest.getParameter("content");
        System.out.println("过滤器拿到的评论内容=" + content);
        //循环遍历一把，看看有没有禁用词
        for (String forbiddenWord : forbiddenWords) {
            if (content.contains(forbiddenWord)) {
                //评论中有禁用词 转发到topic.jsp
                System.out.println("评论中有禁用词=" + forbiddenWord + " 转发到topic.jsp");
                servletRequest.setAttribute("errorInfo", "有禁用词=" + forbiddenWord);
                servletRequest.getRequestDispatcher("/topic.jsp").forward(servletRequest, servletResponse);
                return;//返回
            }
        }
        //评论中没有禁用词 放行 访问对应的资源 //继续到目标
        System.out.println("评论中没有禁用词 放行 访问对应的资源");
        //继续访问目标资源
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
