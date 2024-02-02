package com.hsp.download;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author yangda
 * @description: 客户端
 * @create 2022-12-03-21:29
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        //客户端 输入一个音乐 文件名，高山流水

        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        int count = 0;
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);


        System.out.println("请输入需要下载的文件：");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        byte[] bytes = next.getBytes();

        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();//刷新到数据通道 一定要先刷新，再shutdown 不然报错
        socket.shutdownOutput();//设置结束标记

        //接收 服务器端返回的数据
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        //获取文件名称
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println("<<<<<<<<<<<<");
        String fileName = bufferedReader.readLine();//读不到结束标记会阻塞 会卡在这
        System.out.println("********");
        System.out.println(fileName);
        System.out.println("{"+fileName.length()+"}");
//        String fileName = "高山";


        String filePath = "e:/"+ fileName + ++count +".mp3";
        BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream(filePath));

        byte[] buf = new byte[1024];
        int readLen = 0;
        //写入本地文件 ,采用的边读编写
        while ((readLen = bufferedInputStream.read(buf)) != -1){//客户端读取
            bufferedOutputStream1.write(buf,0,readLen);//bufferedOutputStream1 不关闭写不进去
        }

        System.out.println("写入文件成功");

        //关闭IO流
        bufferedInputStream.close();
        bufferedOutputStream.close();
        bufferedOutputStream1.close();

        //关闭资源
        socket.close();

        System.out.println("客户端退出");

    }
}
