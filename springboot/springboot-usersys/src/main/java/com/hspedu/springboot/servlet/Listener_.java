package com.hspedu.springboot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author yangda
 * @create 2023-12-12-22:05
 * @description:
 */
@Slf4j
// @WebListener
public class Listener_ implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //这里可以加入项目初始化的相关业务代码
        log.info("Listener_ contextInitialized 项目初始化ok!");
    }

    // 要触发该方法需要在，主程序的main方法中，显示的调用ioc.stop()
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //这里可以加入相应的代码...
        log.info("Listener_ contextInitialized 项目销毁");
    }
}
