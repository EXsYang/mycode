package com.hspedu.hspspringboot;

import com.hspedu.hspspringboot.config.HspConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author yangda
 * @create 2023-12-01-19:23
 * @description: Initializer：初始化器
 * <p>
 * 1. 创建我们的Spring容器
 * 2. 加载/关联Spring容器的配置-按照注解的方式
 * 3. 完成Spring容器配置的bean的创建，依赖注入
 * 4. 创建前端控制器 DispatcherServlet，并让其持有Spring容器
 * 【这样前端控制器才可以完成分发请求，才知道注入了哪些Controller 进行分发
 * 5. 当DispatcherServlet持有容器，就可以进行分发映射，回忆SpringMVC底层机制
 * 6. 这里onStartup()是由Tomcat调用，并把ServletContext传入
 */
public class HspWebApplicationInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //ServletContext 就是应用上下文

        System.out.println("startup......");
        //加载Spring web application configuration => 相当于就是一个容器
        //在前面实现Spring容器底层机制时 自己已经实现过Spring容器 HspSpringApplicationContext
        AnnotationConfigWebApplicationContext ac =
                new AnnotationConfigWebApplicationContext();

        //在ac中注册配置类HspConfig.class 可以理解为将配置类加入容器到中 最关键的一句话
        ac.register(HspConfig.class);

        //完成bean的创建和配置 同时也会将配置类HspConfig.class中配置的bean加载到容器中
        ac.refresh();

        //1. 创建非常重要的前端控制器 DispatcherServlet
        //2. 让DispatcherServlet持有容器
        //3. 这样就可以映射分发，回忆一下SpringMVC的机制[自己实现过]
        // 实现springmvc底层机制时自己实现过 HspDispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);

        //把dispatcherServlet加入到了servlet上下文 就可以被访问到
        //这样tomcat才可以找到dispatcherServlet
        // registration： 返回的是一个动态注册的对象 ServletRegistration.Dynamic
        // 返回这个对象的目的就是要进行注册
        ServletRegistration.Dynamic registration =
                servletContext.addServlet("app", dispatcherServlet);

        //当tomcat启动时，加载 dispatcherServlet
        //dispatcherServlet最终是交给tomcat维护和管理的
        //如果没有这句话，就会发现dispatcherServlet并没有被加载
        //会导致所有的请求都没有走到这边来
        registration.setLoadOnStartup(1);

        //拦截所有请求并进行分发处理
        //  / 和 /* 的区别  JavaWeb 8.6.2.5 讲过
        registration.addMapping("/");


    }
}
