package com.hsp.download;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangda
 * @description: TCP 服务器端
 *
 * 注意：在服务器端读取不到结束标记时会卡住，客户端读取不到结束标记不会卡住
 *
 * @create 2022-12-03-21:28
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器端等待连接...");
        Socket socket = serverSocket.accept();
        System.out.println("已连接...");

        //查看客户端发的是什么
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();//服务器端读取不到结束标记会卡住

        //将本地文件读到程序中，放在一个byte数组中
        String filePath = "e:/高山流水.mp3";
        String defaultFilePath = "e:/无名.mp3";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int readLen = 0;
        byte[] buf = new byte[1024];
        while ((readLen = bufferedInputStream.read(buf)) != -1){
            byteArrayOutputStream.write(buf,0,readLen);//写入到字节数组
        }

        byte[] musicArray = byteArrayOutputStream.toByteArray();//返回一个存放所有数据的字节数组

        //********将 无名.mp3 读入数组   *******
        FileInputStream fileInputStream1 = new FileInputStream(defaultFilePath);
        BufferedInputStream bufferedInputStream1 = new BufferedInputStream(fileInputStream1);
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();

        int readLen1 = 0;
        byte[] buf1 = new byte[1024];
        while ((readLen1 = bufferedInputStream1.read(buf1)) != -1){
            byteArrayOutputStream1.write(buf1,0,readLen1);//写入到字节数组
        }
        byte[] musicArray1 = byteArrayOutputStream1.toByteArray();//返回一个存放所有数据的字节数组


        if ("高山流水".equals(line)){
            System.out.println("发送高山流水.mp3");
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            //使用转换流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("高山流水111");
            bufferedWriter.newLine();//这里设置了结束标记 不设置出一堆乱码 把后面写入的数组也被客户端第一次读取时给读了
            bufferedWriter.flush();

            bufferedOutputStream.write(musicArray);//发送高山流水数组  //这里，加结束标记 //这里是服务器端在写数据
            bufferedOutputStream.flush();//刷新到数据通道

            //禁用此套接字的输出流。 对于TCP套接字，任何先前写入的数据将被发送，
            // 随后是TCP的正常连接终止序列。 如果在套接字上调用shutdownOutput（）之后写入套接字输出流，则流将抛出IOException。
            socket.shutdownOutput();//设置结束标记 设置结束标记

            System.out.println("***");


        }else {
            System.out.println("发送默认文件");
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            //使用转换流
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("无名");
            bufferedWriter.newLine();
            bufferedWriter.flush();


            bufferedOutputStream.write(musicArray1);//发送无名数组
            bufferedOutputStream.flush();//刷新到数据通道
            socket.shutdownOutput();//设置结束标记

        }


        bufferedInputStream.close();
        bufferedInputStream1.close();
        bufferedReader.close();
        byteArrayOutputStream.close();
        byteArrayOutputStream1.close();
        System.out.println("$$$$");
        socket.close();
        serverSocket.close();

        System.out.println("服务端退出");

    }
}
