package com.hspedu.hspspringmvc.handler;

import java.lang.reflect.Method;

/**
 * @author yangda
 * @create 2023-10-02-15:33
 * @description: 用于保存映射关系 记录请求的url 和 控制器方法的映射
 */
public class HspHandler {

    private String url;
    private Object controller;
    private Method method;

    public HspHandler() {
    }

    public HspHandler(String url, Object controller, Method method) {
        this.url = url;
        this.controller = controller;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
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
                ", controller=" + controller +
                ", method=" + method +
                '}';
    }
}
