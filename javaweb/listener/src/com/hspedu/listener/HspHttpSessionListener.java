package com.hspedu.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author yangda
 * @description:
 * @create 2023-06-19-18:33
 */
public class HspHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession(); //这句话不会创建session对象
        // 当session创建时，我们给他设置生命周期30s
        session.setMaxInactiveInterval(30);
        System.out.println("HspHttpSessionListener监听到session被创建 用户SessionID= " +
                session.getId() + "上线");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("HspHttpSessionListener监听到session被销毁 用户SessionID= " +
                session.getId() + "离线");
    }
}
