package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;

import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-30-17:31
 */
public abstract class HspHttpServlet implements HspServlet{
    @Override
    public void service(HspRequest request, HspResponse response) throws IOException {
        if ("Get".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else if ("Post".equalsIgnoreCase(request.getMethod())){
            doPost(request, response);
        }


    }

    public abstract void doGet(HspRequest request,HspResponse response);
    public abstract void doPost(HspRequest request,HspResponse response);
}
