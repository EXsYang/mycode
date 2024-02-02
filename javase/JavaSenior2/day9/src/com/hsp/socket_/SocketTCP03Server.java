package com.hsp.socket_;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangda
 * @description: TCP编程，服务器端  一定先启动服务器端
 * @create 2022-12-02-17:00
 */
public class SocketTCP03Server {
    public static void main(String[] args) throws IOException {

        //1. 在本机 的9999端口监听，等待连接
        // 细节:  要求在本机没有其他服务在监听9999
        // ServerSocket 可以通过accept() 返回多个Socket[多个客户端连接服务器的并发]   accept(接受)
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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        System.out.println(line);



        //5.获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        //使用字符输出流的方式回复信息
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello client 字符流 后面用的newLine()");
        //插入一个换行符，表示回复内容的结束
        bufferedWriter.newLine();//设置结束标记 对方也需要使用readLine() 进行读取
        // 才可以读取到结束标记(使用了inputStream.read(byte[])直接读,
        // 会把插入的newLine()换行符也算入文本中，而不是视为结束标记！)  这个结束标记可以在flush以前
        bufferedWriter.flush();//需要手动刷新一下

//        //设置结束标记 这里会关闭outputStream
//        socket.shutdownOutput();


        //6.关闭IO流
        bufferedWriter.close();
        bufferedReader.close();
        //关闭socket
        socket.close();
        //关闭ServerSocket,否则造成资源浪费
        serverSocket.close();
        System.out.println("服务器端退出");

    }
}
