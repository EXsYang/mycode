package com.hsp.qqclient.view;


import com.hsp.qqclient.utils.Utility;
import com.hsp.server.FileClientService;
import com.hsp.server.MessageClientService;
import com.hsp.server.UserClientService;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author 韩顺平
 * @version 1.0
 * 客户端的菜单界面
 */
public class QQView {

    private boolean loop = true; //控制是否显示菜单
    private String key = ""; // 接收用户的键盘输入
    UserClientService userClientService = new UserClientService();
    MessageClientService messageClientService = new MessageClientService();
    FileClientService fileClientService = new FileClientService();


    public static void main(String[] args) throws IOException {
        new QQView().mainMenu();
        System.out.println("客户端退出系统.....");
    }

    //显示主菜单
    private void mainMenu() throws IOException {

        while (loop) {

            System.out.println("===========欢迎登录网络通信系统===========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);

            //根据用户的输入，来处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.print("请输入用户号: ");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密  码: ");
                    String pwd = Utility.readString(50);
                    //这里就比较麻烦了, 需要到服务端去验证该用户是否合法
                    //这里有很多代码, 我们这里编写一个类 UserClientService[用户登录/注册]

                    //这里得到服务器返回的msg,根据返回的msgType 真或假 去判断是否进入二级菜单
                    //一个类调用一个方法，返回boolean
                    //1. 首先将输入的信息发送到服务器端 ，需要创建一个线程类，并且把输入的信息传进去，发送给
                    //   服务器端  创建线程类目的是，便于维护，让这个线程类一直run，不断地发送或是读取数据，
                    //   建立连接，



                    //启动一个线程，不停的从数据通道中往外拿数据,每一个客户端都有一个对应的socket 并且是一个线程，每发送一次数据
                    //就验证一次，并维护socket
                    //1.创建一个 DatagramSocket 对象，准备在9999接收数据

                    //2.构建一个 DatagramPacket 对象，准备接收数据
                    // UDP协议，一个数据包/数据报 最大 64k
//                    byte[] buf = new byte[1023];
//                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    //3.调用 接收方法 将通过网络传输的 DatagramPacket 对象
                    //  填充到 packet 对象
                    //
                    //当有数据包发送到 本机的9999端口时，就会接收到数据
                    // 如果没有数据包发送到 本机的9999端口，就会阻塞等待
//                    System.out.println("接收端A 等待接收数据...");
//                    datagramSocket.receive(packet);


                    //4.可以把packet 进行拆包，取出数据并显示
//                    int length = packet.getLength();//实际接收到的数据字节长度
//                    byte[] data = packet.getData();//接收到数据
//                    System.out.println(data.length);//1023 用的是自己这边的buf缓冲数组盛装的
//                    System.out.println(length);

//                    String s = new String(data, 0, length);
//                    System.out.println(s);

                    //5.回复 “好的，晚上见”
                    boolean b = userClientService.checkUser(userId, pwd);

                    DatagramSocket datagramSocket = new DatagramSocket(7777);
                    byte[] data1 = userId.getBytes();
                    DatagramPacket packet1 = new DatagramPacket(data1, data1.length, InetAddress.getByName("192.168.1.3"), 9998);
                    datagramSocket.send(packet1);
                    //5.关闭资源
                    datagramSocket.close();
//                    System.out.println("A端退出");






                    if (b) { //还没有写完, 先把整个逻辑打通....
                        System.out.println("===========欢迎 (用户 " + userId + " 登录成功) ===========");
                        //进入到二级菜单
                        while (loop) {
                            System.out.println("\n=========网络通信系统二级菜单(用户 " + userId + " )=======");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
//                                    System.out.println("显示在线用户列表");
                                    //这里老师准备写一个方法，来获取在线用户列表
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("群发消息");
                                    System.out.print("请输入群聊内容: ");
                                    String content1 = Utility.readString(50);
                                    messageClientService.sendMessageToAll(userId,content1);


                                    break;
                                case "3":
                                    System.out.println("私聊");
                                    userClientService.onlineFriendList();
                                    System.out.print("请输入用户号(在线): ");
                                    String getterId = Utility.readString(50);
                                    //写一个方法用来验证输入的用户是否在线，或者是否存在
                                    System.out.print("请输入私聊内容: ");
                                    String content = Utility.readString(50);

                                        //如果对方在线
                                        messageClientService.sendMessageToOne(userId,getterId,content);

                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    userClientService.onlineFriendList();
                                    System.out.print("请输入需要给谁发送(在线): ");
                                    String getterId1 = Utility.readString(50);
                                    fileClientService.sendFileToOne(userId,getterId1);
                                    break;
                                case "9":
                                    //调用方法，给服务器发送一个退出系统的message
                                    System.out.println("退出系统");

                                    loop = false;
                                    userClientService.logout();
                                    System.exit(0);//系统正常退出

                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + key);
                            }

                        }
                    } else { //登录服务器失败
                        System.out.println("=========登录失败=========");
                    }
                    break;
                case "9":
                    loop = false;
//                    userClientService.logout();


                    break;
            }

        }

    }
}
