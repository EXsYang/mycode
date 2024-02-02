package com.hspedu.springboot.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @create 2023-12-12-20:42
 * @description:
 * 1.通过继承 HttpServlet 来开发原生的Servlet
 * 2.@WebServlet 标识将Servlet_ 对象/bean注入到容器中 是按照key="com.hspedu.springboot.servlet.Servlet_"进行注入的
 * 3.urlPatterns = {"/servlet01","/servlet02"},对servlet配置了url-pattern
 * 4.提示: http://localhost:8080/servlet01
 *   注入的原生的Servlet_ , 不会被springboot的拦截器拦截
 *   原生的Servlet_ 和 前端控制器 DispatcherServlet 是同一个级别的, 一个请求打过来时
 *   ，如果和原生的Servlet_ 的url-pattern匹配上了，就不会再走springmvc的机制 ，
 *   不会经过DispatcherServlet，即也不会被拦截
 *
 * Servelt路径匹配  优先级遵守: 精确路径 > 目录路径 > 扩展名路径 > /* > /
 * Servlet_ url-pattern="/servlet01","/servlet02"
 * DispatcherServlet url-pattern="/"
 * 多个 servlet 都能处理到同一层路径, 精确优先原则/最长前缀匹配原则
 * Servlet_ 和 DispatcherServlet 同时存在时，http://localhost:8080/servlet01
 * ,匹配Servlet_ => "/servlet01"
 *
 * 5.对于开发的原生的Servlet，需要使用 @ServletComponentScan 指定原生Servlet所在包/子包
 * ，才会注入到spring容器中。
 * 即需要@WebServlet + @ServletComponentScan组合使用
 * 注解@ServletComponentScan 需要标注在主程序所在的类上,并且指定要扫描的包
 //要求扫描 "com.hspedu.springboot" 包/子包下的原生方式注入的Servlet 如下：
 @ServletComponentScan(basePackages = "com.hspedu.springboot")
 @SpringBootApplication
 public class Application {}
 *
 */

// @WebServlet(urlPatterns = {"/servlet01","/servlet02"})
public class Servlet_ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //给浏览器返回一句话"hello,Servlet_!"
        resp.getWriter().write("hello,Servlet_!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
