package com.hspedu.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * @author yangda
 * @description:
 * @create 2023-06-19-19:10
 */
public class HspServletRequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("HspServletRequestAttributeListener 监听到添加属性");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("HspServletRequestAttributeListener 监听到删除属性");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("HspServletRequestAttributeListener 监听到修改属性");
    }
}
