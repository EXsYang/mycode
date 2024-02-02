package com.hspedu.tomcat;

import com.hspedu.tomcat.handler.HspRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangda
 * @description:
 * @create 2023-05-30-15:34
 */
public class HspTomcatV2 {
    public static void main(String[] args) throws IOException {
        // 在8080端口进行监听，获取http请求
        ServerSocket serverSocket = new ServerSocket(8080);

        // serverSocket没有关闭就一直监听
        while (!serverSocket.isClosed()){
            System.out.println("========HspTomcatV2 正在监听8080端口=======");
            Socket socket = serverSocket.accept();
            System.out.println("==========连接成功===========");




            new Thread(new HspRequestHandler(socket)).start();
        }





    }




}
