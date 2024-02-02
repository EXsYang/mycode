package com.hspedu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-22-20:17
 */
/**
 * 老韩解读
 * 1. 开发一个Servlet 需要 实现Servlet接口
 * 2. 实现Servlet接口的方法5个
 */
public class HelloServlet implements Servlet {

    private int count = 0;

    /*
     * @description:
     * 1.初始化 servlet
     * 2.当创建HelloServlet 实例时，会调用init方法
     * 3. 该方法只会被调用一次
     *  Servlet 重新装载时(比如 tomcat 进行 redeploy【redeploy
     *  会销毁所有的 Servlet 实例】)，浏览器再向 Servlet 发送请求的第 1 次
     *
     * @author: yangda
     * @date: 2023/5/22 22:18
     * @param: [servletConfig]
     * @return: void
     **/
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init() 方法被调用");
    }

    /*
     * @description: 返回ServletConfig 也就是返回Servlet的配置
     * @author: yangda
     * @date: 2023/5/22 22:19
     * @param: []
     * @return: javax.servlet.ServletConfig
     **/
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
     * @description:
     * 1. service方法处理浏览器的请求(包括get/post)
     * 2. 当浏览器每次请求Servlet时，就会调用一次service
     * 3. 当tomcat调用该方法时，会把http请求的数据封装成实现ServletRequest接口的request对象
     * 4. 通过servletRequest 对象，可以得到用户提交的数据
     * 5. servletResponse 对象可以用于返回数据给tomcat->浏览器
     * @author: yangda
     * @date: 2023/5/22 22:20
     * @param: [servletRequest, servletResponse]
     * @return: void
     **/
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        count++;
        //如果count的值，在不停的累计，说明HelloServlet是单例的
        System.out.println("hi HelloServlet~ count= " + count);
        //Tomcat每处理一次http请求，就生成一个新的线程
        System.out.println("当前线程id= " + Thread.currentThread().getId());

        //思考->从servletRequest对象来获取请求方式->
        //1. ServletRequest 没有得到提交方式的方法
        //2. ServletRequest 看看ServletRequest子接口有没有相关方法
        //3. 老师小技巧：ctrl+alt+b => 可以看到接口的子接口和实现子类
        //4. 把servletRequest转成 HttpServletRequest引用 //HttpServletRequest 是 ServletRequest的子接口 接口之间向下转型
        //5. 仍然是Java基础的OOP
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        System.out.println("打印出form表单的提交方式：" + method);
        if ("GET".equals(method)){
            doGet(); //用doGet() 处理GET请求
        }else if ("POST".equals(method)){
            doPost(); //用doPost() 处理GET请求
        }

    }

    /**
     * 用于相应get请求
     */
    public void doGet(){
        System.out.println("doGet() 方法被调用、、、");
    }
    /**
     * 用于相应post请求
     */
    public void doPost(){
        System.out.println("doPost() 方法被调用、、、");
    }








    /*
     * @description:
     * 返回servlet信息，使用较少
     * @author: yangda
     * @date: 2023/5/22 22:20
     * @param: []
     * @return: java.lang.String
     **/
    @Override
    public String getServletInfo() {
        return null;
    }

    /*
     * @description: destroy : 摧毁;毁灭;破坏;(因动物有病或不再需要而)杀死，消灭，人道毁灭
     * 1. 该方法是在servlet销毁时，被调用
     * 2. 只会调用一次
     * @author: yangda
     * @date: 2023/5/22 22:21
     * @param: []
     * @return: void
     **/
    @Override
    public void destroy() {

    }
}
