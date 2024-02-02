package com.hsp.socket_;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangda
 * @description: TCP编程，服务器端
 * @create 2022-12-02-17:00
 */
public class SocketTCP02Server {
    public static void main(String[] args) throws IOException {

        //1. 在本机 的9999端口监听，等待连接
        // 细节:  要求在本机没有其他服务在监听9999
        // ServerSocket 可以通过accept() 返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("服务器端,在9999端口监听，等待连接...");
        //2. 当没有客户端连接9999端口时 程序会阻塞在accept()等待连接 类似于Scanner，不会往下执行
        //   如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();


//        System.out.println("服务器端，连接上了 socket=" + socket);
        System.out.println("服务器端，连接上了 socket=" + socket.getClass());

        //3. 通过socket.getInputStream() 读取客户端写入到数据通道的数据，显示
        InputStream inputStream = socket.getInputStream();
        //4. IO读取  从数据通道读取数据到服务器端
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            System.out.println("服务器得到客户端发来的数据 = " + new String(buf,0,readLen));
        }

        //5.获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        //设置结束标记
        socket.shutdownOutput();


        //6.关闭IO流
        inputStream.close();
        outputStream.close();
        //关闭socket
        socket.close();
        //关闭ServerSocket,否则造成资源浪费
        serverSocket.close();
        System.out.println("服务器端退出");

    }
}
