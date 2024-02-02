package com.hsp.upload;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author yangda
 * @description: 文件上传服务器端
 * @create 2022-12-02-21:11
 */
public class TCPFileUploadServer {
    public static void main(String[] args) throws IOException {
    //服务器端在8888端口监听
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器端 8888 端口正在监听 等待连接.....");
        Socket socket = serverSocket.accept();

        System.out.println("客户端已连接");
        InputStream inputStream = socket.getInputStream();
        int readLen = 0;
        byte[] buf = new byte[1024];

        FileOutputStream fileOutputStream = new FileOutputStream("src/A.webp");

//        while ((readLen = inputStream.read(buf,0,readLen)) != -1){ //read(buf,0,0) readLen一直是0，陷入死循环
        while ((readLen = inputStream.read(buf)) != -1){
            //输入流，从数据通道读取数据到程序，直接写入文件中
            fileOutputStream.write(buf,0,readLen);//fileOutputStream 可以不用刷新/关闭流就可以写进去，但是最后也需要关
            System.out.println("服务器端正在写入服务器磁盘");//  这里死循环了（已解决）
        }
        //设置终止标记 这里不是往数据通道写入数据，不用设置终止标记，如果设置了，
        // 后面socket.OutputStream就不能用了,但是socket.InputStream还可以用
//        socket.fileOutputStream();


//        测试：
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//        fileOutputStream、bufferedOutputStream 同样只有三个write() 方法


//        fileOutputStream.flush();//手动刷新  这里是字节流，可能不需要刷新？
        System.out.println("文件已上传到服务器计算机磁盘");
        //回复给客户端，收到图片
        OutputStream outputStream = socket.getOutputStream();//得到socket相关的输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("收到图片");
        bufferedWriter.newLine();//设置结束标记 字符流有newLine() 字节流没有
        //字符流，需要手动刷新
        bufferedWriter.flush();//把内容刷新到数据通道



        inputStream.close();
        fileOutputStream.close();
        bufferedWriter.close();//关闭外层流

        serverSocket.close();
        socket.close();
        System.out.println("服务器已退出");


    }
}
