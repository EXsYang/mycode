package com.hspedu.tomcat.handler;

import com.hspedu.tomcat.HspTomcatV3;
import com.hspedu.tomcat.http.HspRequest;
import com.hspedu.tomcat.http.HspResponse;
import com.hspedu.tomcat.servlet.HspCalServlet;
import com.hspedu.tomcat.servlet.HspHttpServlet;
import com.hspedu.tomcat.utils.WebUtils;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/**
 * 老师解读
 * 1. HspRequestHandler 对象是一个线程对象
 * 2. 处理一个http请求的
 */
public class HspRequestHandler implements Runnable {
    // 要操作socket相关的，需要此类持有Socket属性
    private Socket socket = null;

    // 提供带参构造器，将socket传过来
    public HspRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        //InputStream inputStream = null;
        //OutputStream outputStream = null;
        //接收浏览器请求


        System.out.println("当前线程： " + Thread.currentThread().getName());
        try {
            // 得到socket相关的输入流
            //InputStream inputStream = socket.getInputStream();

            //// 得到包装输入流
            //BufferedReader bufferedReader =
            //        new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            //String buf = "";
            //while ((buf = bufferedReader.readLine()) != null) {
            //    if (buf.length() == 0) { //即用户什么都没输入
            //        break;
            //    }
            //    System.out.println(buf);
            //}

            //这里我们先死后活
            HspRequest hspRequest = new HspRequest(socket.getInputStream());
            //String num1 = hspRequest.getParameter("num1");
            //String num2 = hspRequest.getParameter("num2");
            //System.out.println("请求的参数num1=" + num1);
            //System.out.println("请求的参数num2=" + num2);
            //System.out.println("hspRequest=" + hspRequest);


            // 得到socket相关的输出流
            //outputStream = socket.getOutputStream();
            //// 构建http响应头，进行相应数据
            //String respHeader = "HTTP/1.1 200 OK\r\n" +
            //        "Content-Type: text/html;charset=utf-8\r\n\r\n";
            //String resp = respHeader + "<h1>hello world HspTomcatV2</h1>";

            //这里我们可以测试一下，用HspResponse对象，返回数据给浏览器/客户端
            HspResponse hspResponse = new HspResponse(socket.getOutputStream());
            //OutputStream outputStream = hspResponse.getOutputStream();
            //String resp = HspResponse.respHeader + "<h1>hspResponse 返回信息 hi 你好</h1>";

            //outputStream.write(resp.getBytes());
            //outputStream.flush();
            //outputStream.close();
            //System.out.println("回送的数据：" + resp);

            // 创建HspCalServlet对象，一会用反射来做
            //HspCalServlet hspCalServlet = new HspCalServlet();
            //hspCalServlet.doGet(hspRequest,hspResponse);

            // 使用反射动态创建servlet实例
            // 得到浏览器请求中的uri
            String uri = hspRequest.getUri();

            //有了filter机制，可以理解在调用servlet之前，先匹配filter
            //1.根据request对象封装的uri
            //2.到filterUrlMapping 去匹配
            //3.如果匹配上就调用 filterMapping 对应的filter对象doFilter()
            //4.如果没有匹配上，就直接走servlet/jsp/html等



            // 通过uri 去获取servletName
            String servletName = HspTomcatV3.servletUrlMapping.get(uri);
            //=====================新增业务逻辑==========
            //(1) 判断uri是什么资源 => 工具方法
            //(2) 如果是静态资源,就读取该资源，并返回给浏览器 content-type text/html
            //(3) 因为目前老师并没有起到tomcat, 不是一个标准的web项目
            //(4) 把读取的静态资源放到 target/classes/cal.html
            //过滤，拦截 , 权限等待 => Handler.... => 分发
            if (!WebUtils.identifyResources(uri)) {
                if (servletName == null) {// 如果根据用户输入的uri 没有找到servletName返回一个空
                    servletName = "";// 置成空串,HspTomcatV3.servletMapping.get(servletName);
                    // ConcurrentHashMap的 get( ) 方法中不可以传入null值
                }
                // 通过servletName 获取对应实例
                HspHttpServlet hspHttpServlet = HspTomcatV3.servletMapping.get(servletName);
                // 调用servlet 的service方法,将hspRequest和hspResponse传进去
                if (hspHttpServlet != null) {
                    hspHttpServlet.service(hspRequest, hspResponse);
                } else {
                    String respHeader = HspResponse.respHeader;
                    String resp = respHeader + "<h1>404 Not Found</h1>";
                    OutputStream outputStream = hspResponse.getOutputStream();
                    outputStream.write(resp.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
                //inputStream.close();
                socket.close();
            } else {// 是静态资源
                String respHeader = HspResponse.respHeader;
                String respHtml = WebUtils.readHtml(uri);
                String resp = respHeader + respHtml;
                System.out.println("回送数据为: \n" + resp);
                OutputStream outputStream = hspResponse.getOutputStream();
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();
                socket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

}