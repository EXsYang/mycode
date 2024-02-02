package com.hsp.upload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.Buffer;

/**
 * @author yangda
 * @description: 文件上传客户端  TCP编程 客户端会随机分配一个端口
 * @create 2022-12-02-21:11
 */
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {
        //客户端连接到服务器端，发送一张图片 "e:A.webp"
    //将磁盘中的图片读入到程序中，放在一个字节数组中
        String filePath = "e:/A.webp";//自己试一下，边读边写完成，一边读一边写入通道中？？
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fileInputStream);

//        BufferedOutputStream bos = new BufferedOutputStream(new ByteArrayOutputStream());
        Socket socket = new Socket(InetAddress.getLocalHost(),8888);
        //得到socket相关的输出流
        System.out.println("客户端打印socket对象：" + socket);//Socket[addr=F2/192.168.1.3,port=8888,localport=63707]
        //localport 随机分配的
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);



        int readLen = 0;
        byte[] buf = new byte[1024];//缓冲
        while ((readLen = bis.read(buf)) != -1){
            //一边读一边 写入管道中 字节流，bufferedOutputStream需要关闭才能真正写入
            bufferedOutputStream.write(buf,0,readLen);//BufferedOutputStream不关流写不进去
            System.out.println("客户端正在写入数据通道");
        }
        bufferedOutputStream.flush();//必须刷新一下，不然写不进去   Cannot send after socket shutdown: socket write error 需要放在前面
        socket.shutdownOutput();//终止标记 需要放在后面

        //接收服务器端的回复信息
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());//读取到结束标记 返回一个null


        bis.close();
        bufferedOutputStream.close();
        bufferedReader.close();
        socket.close();
        System.out.println("客户端已退出");
    }
}
