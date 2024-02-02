package com.hsp.socket_;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author yangda
 * @description: 客户端 TCP编程
 * @create 2022-12-02-17:02
 */
public class SocketTCP02Client {
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
//        outputStream.write("hello,server".getBytes());
//        //设置结束标记!!! 不然对方不知道写没写完
//
////        outputStream 直接写，需要写结束标记，不然两边都卡住，不往下执行
//        socket.shutdownOutput(); //关上试试 两边都卡住

        //测试：其他写法会不会卡着  bufferedOutputStream.write 也会卡住
        //测试：和对面读取方式有关吗，此时对面使用最普通的inputStream.read(buf) 直接读的
        // 对面使用bufferedInputStream.read(buf) 进行读取还是会卡住  为什么 下载文件作业3，不写shutdown 不会卡住呢
        System.out.println("正在测试噢");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write("Hi!".getBytes());
        bufferedOutputStream.flush();//卡不卡和刷不刷新没关系

        socket.shutdownOutput();// 测试关闭中、、、




        //4.获取和socket关联的输入流，读取数据(字节)并显示
        InputStream inputStream = socket.getInputStream();  //客户端读取数据测试
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,readLen));
        }



        //5. 关闭输出流对象和socket 必须关闭
        outputStream.close();

        //关闭socket
        socket.close();

        System.out.println("客户端退出");





    }
}
