package com.hsp.socket_;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author yangda
 * @description: 客户端 TCP编程 ( 使用字符流 )  TCP编程 客户端会随机分配一个端口
 * @create 2022-12-02-17:02
 */
public class SocketTCP03Client {
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
        //修改成字符流   转换流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello server 字符流");
        bufferedWriter.newLine();//插入一个换行符，表示写入的内容结束    对方也需要使用readLine()！！！！ 进行读取，才能读取到结束标记
        //使用了.newLine() 结束标记，就不需要socket.shutdownOutput();
        bufferedWriter.flush();//如果使用的字符流，需要手动刷新，否则数据不会写入数据通道

        //设置结束标记!!! 不然对方不知道写没写完
//        socket.shutdownOutput();

        //4.获取和socket关联的输入流，读取数据(字节)并显示
        InputStream inputStream = socket.getInputStream();

//        byte[] buf = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(buf)) != -1){
//            System.out.println("客户端：inputStream读取：" + new String(buf,0,len));
//        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        System.out.println(line);



        //5. 关闭输出流对象和socket 必须关闭
        bufferedReader.close();
        bufferedWriter.close();
//        inputStream.close();

        //关闭socket
        socket.close();

        System.out.println("客户端退出");





    }
}
