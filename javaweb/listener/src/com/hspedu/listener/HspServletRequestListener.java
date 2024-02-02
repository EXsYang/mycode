package com.hspedu.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author yangda
 * @description:
 * @create 2023-06-19-19:00
 */
public class HspServletRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("HspServletRequestListener 监听到request对象被创建");
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println("记录访问日志...");
        System.out.println("访问IP= " + servletRequest.getRemoteAddr());
        System.out.println("访问的资源是= " + ((HttpServletRequest)servletRequest).getRequestURL());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("HspServletRequestListener 监听到request对象被销毁");
    }
}
