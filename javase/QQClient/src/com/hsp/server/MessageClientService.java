package com.hsp.server;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author yangda
 * @description: 用户私聊，群聊 ，提供消息相关的服务方法
 * @create 2022-12-05-22:25
 */
public class MessageClientService {


    //
    //写一个方法，给服务器端发送一条消息，服务器端将消息转发给其他的客户端
    public void sendMessageToOne(String senderId,String getterId,String content) {

        //调用一个方法,判断对方在不在线，如果在线继续走下面的代码，如果不在线，就走另外一套代码


        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + " 对 " + getterId + "说:" + content);

        //获取到socket 对象
        try {
            OutputStream outputStream = ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void sendMessageToAll(String senderId, String content) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + " 对大家说:" + content);

        //获取到socket 对象
        try {
            OutputStream outputStream = ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
