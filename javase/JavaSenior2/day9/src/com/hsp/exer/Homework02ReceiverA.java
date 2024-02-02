package com.hsp.exer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author yangda
 * @description: UDP 编程
 * @create 2022-12-03-19:56
 */
public class Homework02ReceiverA {
    public static void main(String[] args) throws IOException {
        //接收端在8888端口等待接收数据
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        System.out.println("接收端A正在等待接收数据...");
        //
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        datagramSocket.receive(packet);


        //拆包
        int length = packet.getLength();
        byte[] data = packet.getData();

        String s = new String(data, 0, length);
        System.out.println(s);

        if ("四大名著是哪些".equals(s)){
            buf = "红楼梦，西游记，水浒传，三国演义".getBytes();//buf 地址指向新的数组
//            byte[] bytes = "红楼梦，西游记，水浒传，三国演义".getBytes();
//            System.out.println(buf.length);
//            System.out.println(bytes.length);
//            buf = bytes;
//            System.out.println(buf.length);
            packet = new DatagramPacket(buf,buf.length, InetAddress.getByName("192.168.1.3"),7777);
            datagramSocket.send(packet);
        }else {
            buf = "what?".getBytes();
            packet = new DatagramPacket(buf,buf.length, InetAddress.getByName("192.168.1.3"),7777);
            datagramSocket.send(packet);
        }

        datagramSocket.close();
        System.out.println("A端退出");
    }
}
