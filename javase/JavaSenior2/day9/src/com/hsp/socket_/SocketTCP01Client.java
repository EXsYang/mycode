package com.hsp.socket_;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author yangda
 * @description: 客户端 TCP编程
 * @create 2022-12-02-17:02
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {

        //1. 连接服务器(ip,端口)
        //  连接本机的 9999端口，如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端连接服务器端，端口号9999");
        System.out.println("客户端 socket返回= " + socket);
        System.out.println(socket.getClass());
        //2.连接上后，生成Socket，通过socket.getOutputStream()
        //  得到 和 socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();//得到socket相关的输出流

        //3.通过输出流，往数据通道写入数据
        outputStream.write("hello world".getBytes());

        //4. 关闭输出流对象和socket 必须关闭
        outputStream.close();

        //关闭socket
        socket.close();

        System.out.println("客户端退出");





    }
}
