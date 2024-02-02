package com.hsp.exer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author yangda
 * @description: TCP 编程作业
 * @create 2022-12-03-17:13
 */
public class Homework01Server {
    public static void main(String[] args) throws IOException {

        //服务器端在 端口8888 等待连接
        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("服务器端等待连接...");
        //没有连接会一直阻塞在这
        Socket socket = serverSocket.accept();
        System.out.println("服务器连接成功...");

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean flag = true;
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        int count = 0;
        while (flag){
            System.out.println("readLine上");
            String line = bufferedReader.readLine();//在这里卡住了，这里 没有读到结束标记 会卡住，读到结束标记(接收一行消息,
            //没读到一行(换行符)不会往下走 )才会往下走
//            String line2 = bufferedReader.readLine();//在这里卡住了，这里 没有读到结束标记 会卡住，读到结束标记才会往下走
            System.out.println(line);
            System.out.println("readLint下");
            //回复信息
            if (line.equals("name") || line.equals("hobby")){
                //返回我是nova
                if (line.equals("name")){
                    bufferedWriter.write("我是nova");
                    //禁用此套接字的输出流。 对于TCP套接字，任何先前写入的数据将被发送，
                    // 随后是TCP的正常连接终止序列。 如果在套接字上调用shutdownOutput（）之后写入套接字输出流，则流将抛出IOException。

                    //第二次while循环时抛异常,因为第一次循环时把套接字的输出流禁用了
                    bufferedWriter.flush();//Cannot send after socket shutdown: socket write error
                    socket.shutdownOutput();
//                    bufferedWriter.newLine();//设置结束标记 客户端读不到结束标记也会卡住

//                    bufferedWriter.flush();//套接字关闭后不能发送:套接字写错误 Cannot send after socket shutdown: socket write error
                    //将数据刷新到数据通道中// Software caused connection abort: socket write error
                }else {
                    bufferedWriter.write("编写java程序");
                    bufferedWriter.newLine();//设置结束标记
                    bufferedWriter.flush();//将数据刷新到数据通道中
                }
            }else{
                bufferedWriter.write("你说啥呢？");
                bufferedWriter.newLine();//设置结束标记
                bufferedWriter.flush();//将数据刷新到数据通道中
            }
            System.out.println("服务器正在跑"+ count++);
            if ("再见".equals(line)){
                flag = false;
            }
        }


        //关闭IO流
        bufferedReader.close();
        bufferedWriter.close();
        //关闭资源
        socket.close();
        serverSocket.close();
        //
        System.out.println("服务器端退出");



    }
}
