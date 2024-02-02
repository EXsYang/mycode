package com.hsp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author yangda
 * @description: UDP 发送端
 * @create 2022-12-03-15:28
 */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {

        //1.创建 DatagramSocket 对象，准备在9998端口接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9998);

        //2.将需要发送的数据，封装到 DatagramPacket对象
        byte[] data = "今晚去吃火锅".getBytes();
        int length = data.length;

        //说明：封装的 DatagramPacket对象 data 内容字节数组，data.length,主机（IP）,端口
        DatagramPacket packet = new DatagramPacket(data,length, InetAddress.getByName("192.168.1.3"),9999);
        datagramSocket.send(packet);
        System.out.println("packet.getLength()" + packet.getLength());

        //接收B端回复的数据 使用缓冲接收数据
        byte[] buf = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(packet1);//接收包
        //拆包
        int length1 = packet1.getLength();
        byte[] data1 = packet1.getData();
        System.out.println(data1.length);//1024
        System.out.println(length1);//1024
        //根据数据的长度和数据的内容构建String
        String s = new String(data1, 0, length1);//构建了长度1024的字符串
        System.out.println(s);


        //3.关闭资源
        datagramSocket.close();
        System.out.println("B端退出");

    }
}
