package com.hsp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**

 * @author yangda
 * @description: UDP接收端
 * @create 2022-12-03-15:28
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1.创建一个 DatagramSocket 对象，准备在9999接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //2.构建一个 DatagramPacket 对象，准备接收数据
        // UDP协议，一个数据包/数据报 最大 64k
        byte[] buf = new byte[1023];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //3.调用 接收方法 将通过网络传输的 DatagramPacket 对象
        //  填充到 packet 对象
        //
        //当有数据包发送到 本机的9999端口时，就会接收到数据
        // 如果没有数据包发送到 本机的9999端口，就会阻塞等待
        System.out.println("接收端A 等待接收数据...");
        datagramSocket.receive(packet);


        //4.可以把packet 进行拆包，取出数据并显示
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data = packet.getData();//接收到数据
        System.out.println(data.length);//1023 用的是自己这边的buf缓冲数组盛装的
        System.out.println(length);

        String s = new String(data, 0, length);
        System.out.println(s);

        //5.回复 “好的，晚上见”
        byte[] data1 = "好的，晚上见".getBytes();
        DatagramPacket packet1 = new DatagramPacket(data1, data1.length, InetAddress.getByName("192.168.1.3"), 9998);
        datagramSocket.send(packet1);



        //5.关闭资源
        datagramSocket.close();
        System.out.println("A端退出");


    }
}
