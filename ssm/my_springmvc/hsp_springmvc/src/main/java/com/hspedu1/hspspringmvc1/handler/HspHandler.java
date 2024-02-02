package com.hspedu1.hspspringmvc1.handler;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * @author yangda
 * @create 2023-10-07-19:48
 * @description: 该类存放url和目标方法的映射关系
 */

public class HspHandler {

    // 记录Controller目标方法上的 value 中的url
    private String url;
    // 保存Controller对象
    private Object Controller;
    // 保存目标方法
    private Method method;

    public HspHandler() {
    }

    public HspHandler(String url, Object controller, Method method) {
        this.url = url;
        Controller = controller;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getController() {
        return Controller;
    }

    public void setController(Object controller) {
        Controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "HspHandler{" +
                "url='" + url + '\'' +
                ", Controller=" + Controller +
                ", method=" + method +
                '}';
    }
}
