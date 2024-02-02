package com.hspedu.tomcat.servlet;

import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;
import com.hspedu.tomcat.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yangda
 * @description:
 * @create 2023-05-30-17:31
 */
public class Hsp6CalServlet extends HspHttpServlet {


    @Override
    public void doGet(HspRequest request, HspResponse response) {

        int num1 = WebUtils.parseInt(request.getParameter("num1"), 0);
        int num2 = WebUtils.parseInt(request.getParameter("num2"), 0);

        int sum = num1 * num2;

        // 返回计算结果给浏览器
        // outputStream 和 当前的socket 关联
        OutputStream outputStream = response.getOutputStream();
        String resp = HspResponse.respHeader + "<h1>" + num1 + " * " + num2 + " = " + sum + " TomcatV3 Hsp6CalServlet 反射方式创建</h1>";

        try {
            outputStream.write(resp.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void doPost(HspRequest request, HspResponse response) {
        doGet(request, response);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }
}
