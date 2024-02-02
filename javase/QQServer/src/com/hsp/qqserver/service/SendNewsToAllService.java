package com.hsp.qqserver.service;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;
import com.hsp.utils.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yangda
 * @description: 单开一个线程，从服务端 给客户端推送新闻
 * @create 2022-12-06-11:41
 */
public class SendNewsToAllService implements Runnable{


    @Override
    public void run() {
        //推送新闻

        while (true){
            System.out.println("请输入要推送的信息(exit 退出推送)：");
            String s = Utility.readString(50);
            //
            if ("exit".equals(s)){
                break;
            }
            Message message = new Message();
            message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
            message.setContent(s);
            message.setSendTime(new Date().toString());
            message.setSender("服务器");

            System.out.println("服务器对所有人说：" + message.getContent());
            //拿到所有的用户线程集合
            HashMap<String, ServerConnectClientThread> hashMap = ManageClientThreads.hashMap;
            Set<String> usersId = hashMap.keySet();

            Iterator<String> iterator = usersId.iterator();
            while (iterator.hasNext()){
                String userId = iterator.next();
                //根据userId 给所有在线用户推送
                try {
                    OutputStream outputStream = hashMap.get(userId).getSocket().getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("服务器推送消息线程结束");



    }
}
