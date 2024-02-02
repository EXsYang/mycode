package com.hspedu.listener; /**
 * @description:
 * @author yangda
 * @create 2023-06-19-18:05
 */

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class HspServletContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("ServletContextAttributeListener监听到添加属性" + servletContextAttributeEvent.getName()
                + " = " + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("ServletContextAttributeListener监听到删除属性" + servletContextAttributeEvent.getName() +
                " = " + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("ServletContextAttributeListener监听到替换修改属性" + servletContextAttributeEvent.getName()
                + " = " + servletContextAttributeEvent.getValue());
    }
}
