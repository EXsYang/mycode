package com.hsp.server;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;
import com.hsp.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author yangda
 * @description: 用户客户端服务 ， 验证服务端返回的message 为多少 用户登录验证，用户注册等功能
 * @create 2022-12-05-9:26
 */
public class UserClientService {
    private User u = new User();

    Socket socket;
    public static String getterCheck;

    //检查客户端输入的id pwd 是否合法
    public boolean checkUser(String userId, String pwd) {
        //将客户端输入的信息设置给User对象
        boolean b = true;
        u.setUserId(userId);
        u.setPasswd(pwd);

        //将User对象传给服务器端
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            //
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);//将数据写入到数据通道中

            //接收服务器端返回的数据
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) ois.readObject();

            if (MessageType.MESSAGE_LOGIN_SUCCEED.equals(msg.getMesType())) {
                //登录成功
                //创建一个线程类，持有socket,启动线程，保持通信
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();

                //将启动起来的线程放入到集合中
                ManageClientConnectServerThread.addManageClientConnectServerThread(u.getUserId(), clientConnectServerThread);

            } else {
                //登录失败
                socket.close();
                b = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    //向服务器端请求在线用户列表
    public void onlineFriendList() {
        //首先向服务器端发送请求
        try {

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setSender(u.getUserId());
            //设置信息类型，获取在线好友类型
            message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
            //将message写入数据通道中
            oos.writeObject(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //无异常退出
    public void logout() {
        //向客户端发送一个message
        try {
            Message message = new Message();
            message.setSender(u.getUserId());
            message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    //判断私聊时输入的getterId 接收者Id 是否存在
//    public boolean idExist(String senderId,String getterId){
//        boolean b = false;
//        //给服务器端发送一条信息 在服务器端进行 keySet 遍历查询 或者直接get(getterId) 判断是否为null
//        //如果在存放用户数据的集合中存在  但是线程集合中不存在，说明不在线，提示该用户不在线，判断是否进行留言
//        //如果对方在线返回true
//
//        //测试同一个类中，有没有其他类的线程加入本类集合,客户端线程集合中只有当前登录的线程
////        System.out.println("idExist: " + ManageClientConnectServerThread.getClientConnectServerThread(getterId).getName());
//
//        Message message = new Message();
//        //使用content 将需要验证的信息发过去
//        message.setContent(getterId);
//        message.setSender(senderId);
//        //设置检查类型，检查在不在线
//        message.setMesType(MessageType.GETTER_CHECK);
//
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
//            oos.writeObject(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //getterCheck 这里是null
//        Message msg = null;
//        try {
//            //相当于读了两次,不行!
//            ObjectInputStream ois = new ObjectInputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getInputStream());
//            msg = (Message) ois.readObject();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (msg.equals(MessageType.GETTER_EXIST)){//在线
//            b = true;
//        }
//        System.out.println(getterId + ": 在线");
//        return b;
//
//
//    }
    //判断私聊时输入的getterId 接收者Id 是否在线
    public boolean Exist(String getterId){

        return true;
    }




}
