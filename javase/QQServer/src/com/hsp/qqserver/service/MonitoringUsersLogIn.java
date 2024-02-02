package com.hsp.qqserver.service;

import com.hsp.qqcommon.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: 监控用户登录的线程
 * @create 2022-12-06-21:22
 */
public class MonitoringUsersLogIn extends Thread {
    @Override
    public void run() {
        ConcurrentHashMap<String, ArrayList<Message>> db = QQServer.db;
        ArrayList<Message> msgList = QQServer.msgList;
        HashMap<String, ServerConnectClientThread> hashMap = ManageClientThreads.hashMap;
        ConcurrentHashMap.KeySetView<String, ArrayList<Message>> offLines = db.keySet();
        //if (db.size() != 0) {//说明存的有离线文件,就需要跑起来,也可以让它直接跑
        while (true) {
            //让该线程每登录一个用户就读一次用户Id


            //下面是客户端上线追踪代码:
//1.创建 DatagramSocket 对象，准备在9998端口接收数据
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket(9998);
            } catch (SocketException e) {
                e.printStackTrace();
            }

//                //2.将需要发送的数据，封装到 DatagramPacket对象
//                byte[] data = "今晚去吃火锅".getBytes();
//                int length = data.length;
//                //说明：封装的 DatagramPacket对象 data 内容字节数组，data.length,主机（IP）,端口
//                DatagramPacket packet = null;
//                try {
//                    packet = new DatagramPacket(data,length, InetAddress.getByName("192.168.1.3"),9999);
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    datagramSocket.send(packet);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("packet.getLength()" + packet.getLength());

            //接收B端回复的数据 使用缓冲接收数据
            byte[] buf = new byte[1024];
            DatagramPacket packet1 = new DatagramPacket(buf, buf.length);
            try {
                datagramSocket.receive(packet1);//接收包
            } catch (IOException e) {
                e.printStackTrace();
            }
            //拆包
            int length1 = packet1.getLength();
            byte[] data1 = packet1.getData();
//                System.out.println(data1.length);//1024
//                System.out.println(length1);//1024
            //根据数据的长度和数据的内容构建String
            String userId = new String(data1, 0, length1);//构建了长度1024的字符串
//                System.out.println(userId);
            //3.关闭资源
            datagramSocket.close();
//                System.out.println("B端退出");


            //遍历用户线程集合
            //当db集合有值时 , db的key 就是需要监控的用户id
            //获取所有的key,如果key不为空 即size 不为零 跑起来
//                for (String offLine : offLines) {//取出所有的离线用户Id,和所有的在线用户集合比较
//                    if (hashMap.get(offLine) != null) {//不为空,说明在线
            //取出离线信息,发送给这个offLine用户
//                        ArrayList<Message> messages = db.get(offLine);

            if (db.get(userId) != null) {
                //遍历消息集合,一条一条,发送给用户
                ArrayList<Message> messages = db.get(userId);//这里共用一个ArrayList
                ObjectOutputStream oos = null;
                for (Message message : messages) {
                    try {
                        //数据通道中,每个和socket相关的OutputStream对象,与InputStream对象
                        //发送消息次数,即写入(write)次数,与从数据通道中读取(read)的次数 要相等
                        //每个对象的写读次数都要保持一致,不能写入的对象写了两次,读取了一次
                        oos = new ObjectOutputStream(hashMap.get(userId).getSocket().getOutputStream());
                        oos.writeObject(message);//发送了两次

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //发送完就从数组中移除
                db.remove(userId);

                //发完离线消息,不能退出,继续监控其他离线消息
//                break;
            }
        }

        //}
    }
}
