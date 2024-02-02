package com.hsp.exer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author yangda
 * @description: UDP 编程
 * @create 2022-12-03-19:56
 */
public class Homework02SenderB {
    public static void main(String[] args) throws IOException {
        //发送端B 在端口7777 等待接收数据
        DatagramSocket datagramSocket = new DatagramSocket(7777);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请提出问题：");
        String question = scanner.next();//四大名著是哪些
        byte[] data = question.getBytes();
        DatagramPacket packet = new DatagramPacket(data,data.length, InetAddress.getByName("192.168.1.3"),8888);
        datagramSocket.send(packet);

        byte[] buf = new byte[1024];

        DatagramPacket packet1 = new DatagramPacket(buf, buf.length);//
//        datagramSocket.receive(packet); //接收错了，自己的是空包，长度按自己缓冲byte数组的长度
        datagramSocket.receive(packet1);

        //拆包
        int length = packet1.getLength();
        byte[] data2 = packet1.getData();
//        System.out.println(packet.getLength());
//        System.out.println(data2);
        String s = new String(data2, 0, length);
        System.out.println(s);

        datagramSocket.close();
        System.out.println("B端退出");

    }
}
