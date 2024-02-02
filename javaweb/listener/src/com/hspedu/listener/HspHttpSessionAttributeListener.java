package com.hspedu.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author yangda
 * @description:
 * @create 2023-06-19-18:51
 */
public class HspHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("HspHttpSessionAttributeListener 监听到session添加属性: " +
                httpSessionBindingEvent.getName()+"="+httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("HspHttpSessionAttributeListener 监听到session对象删除属性: " +
                httpSessionBindingEvent.getName()+"="+httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("HspHttpSessionAttributeListener 监听到session对象修改属性: " +
                httpSessionBindingEvent.getName()+"="+httpSessionBindingEvent.getValue());
    }
}
