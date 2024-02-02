package com.hsp.qqserver.service;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: 服务器端连接客户端的线程(子线程,负责保持和客户端进行通信)
 * @create 2022-12-05-15:50
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;//该线程持有socket
    private String userId;

    public Socket getSocket() {
        return socket;
    }

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true) {
            //不停的从数据通道中读取数据
            try {
                System.out.println("服务器和客户端" + userId + "保持通信，读取数据");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject(); //这里如果读不到，会阻塞
                //
                if (MessageType.MESSAGE_GET_ONLINE_FRIEND.equals(msg.getMesType())) {
                    //返回一个客户列表
                    //调用得到用户列表的方法
                    System.out.println(msg.getSender() + "要客户列表");
                    String onlineUsers = ManageClientThreads.getOnlineUsers();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    Message message = new Message();
                    message.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message.setGetter(msg.getSender());
                    message.setContent(onlineUsers);
                    oos.writeObject(message);

                } else if (MessageType.MESSAGE_TO_ALL_MES.equals(msg.getMesType())) {
                    //得到客户端发来的群聊消息，将此消息进行转发给所有人
                    //获得所有人 的socket 对象 ，将信息转发过去
                    Message message = new Message();
                    message.setContent(msg.getContent());
                    message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
                    message.setSender(msg.getSender());

                    HashMap<String, ServerConnectClientThread> hashMap = ManageClientThreads.getHashMap();
                    Set<String> keySet = hashMap.keySet();
                    Iterator<String> usersId = keySet.iterator();
                    while (usersId.hasNext()) {
                        String userId = usersId.next();//得到每一个在线用户的Id，如果用户只有自己，什么都不发生
                        if (!msg.getSender().equals(userId)) {//排除自己，不发给自己
                            OutputStream outputStream = hashMap.get(userId).getSocket().getOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                            oos.writeObject(message);
                        }
                    }
                    //这不能加break
                } else if (MessageType.MESSAGE_COMM_MES.equals(msg.getMesType())) {
                    //得到客户端发来的私聊消息，将此消息进行转发
                    //获得接收者 的socket 对象 ，将信息转发过去
                    //先判断用户在不在线
                    //先判断用户在不在线
                    Message message = new Message();
                    if (msg.getSender().equals(msg.getGetter())) {//不能给自己发送消息
                        message.setHint("不能给自己发消息");
                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        ObjectOutputStream ois1 = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(msg.getSender()).getSocket().getOutputStream());
                        ois1.writeObject(message);
                    } else if (QQServer.validUsers.get(msg.getGetter()) == null) {//该用户不存在
                        message.setHint("该用户不存在");
                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        ObjectOutputStream ois1 = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(msg.getSender()).getSocket().getOutputStream());
                        ois1.writeObject(message);
                    } else if (ManageClientThreads.getServerConnectClientThread(msg.getGetter()) == null) {//用户不在线
                        //留言可能不止一条
                        //单开 一个线程/方法,监控用户是否登录
                        ConcurrentHashMap<String, ArrayList<Message>> db = QQServer.db;
                        //~~判断集合中是否有getterId作为key~~不用判断,HashMap不允许重复key,直接往里放就行
                        //判断该getterId对应的db的value是否为空,为空new一个
                        //不为空直接添加,Map集合存放的是键值对,不存在只填key不填value
                        if (db.get(msg.getGetter()) == null) {
                            ArrayList<Message> msgs = new ArrayList<Message>();
                            msgs.add(msg);
                            db.put(msg.getGetter(), msgs);
                        } else {
                            ArrayList<Message> msgs = db.get(msg.getGetter());
                            msgs.add(msg);
                            db.put(msg.getGetter(), msgs);
                        }


                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        message.setHint("用户不在线,已留言");
                        //这里没设置getGetter
                        ObjectOutputStream ois1 = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(msg.getSender()).getSocket().getOutputStream());
                        ois1.writeObject(message);

                    } else {
                        message.setContent(msg.getContent());
                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        message.setSender(msg.getSender());
                        message.setGetter(msg.getGetter());
                        OutputStream outputStream = ManageClientThreads.getServerConnectClientThread(msg.getGetter()).getSocket().getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                        oos.writeObject(message);
                        //离线留言功能
                    }


//                    break;//这不能加break
                } else if (MessageType.FILE_MESSAGE_TO_ONE.equals(msg.getMesType())) {
                    //得到客户端发来的文件消息，将此消息进行转发
                    //获得接收者 的socket 对象 ，将信息转发过去
//                    Message message = new Message();
//                    message.setMesType(MessageType.FILE_MESSAGE_TO_ONE);
//                    message.setSender(msg.getSender());
//                    message.setGetter(msg.getGetter());
//                    message.setFile(msg.getFile());
//                    message.setFileLength(msg.getFileLength());
//                    message.setSendTime(msg.getSendTime());
//                    message.setSrc(msg.getSrc());
//                    message.setDest(msg.getDest());


                    Message message = new Message();
                    if (msg.getSender().equals(msg.getGetter())) {//不能给自己发送消息
                        message.setHint("不能给自己发文件");
                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        ObjectOutputStream ois1 = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(msg.getSender()).getSocket().getOutputStream());
                        ois1.writeObject(message);
                    } else if (QQServer.validUsers.get(msg.getGetter()) == null) {//该用户不存在
                        message.setHint("该用户不存在");
                        message.setMesType(MessageType.MESSAGE_COMM_MES);
                        ObjectOutputStream ois1 = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(msg.getSender()).getSocket().getOutputStream());
                        ois1.writeObject(message);
                    } else if (ManageClientThreads.getServerConnectClientThread(msg.getGetter()) == null) {//用户不在线
                        //留言可能不止一条
                        //单开 一个线程/方法,监控用户是否登录
                        ConcurrentHashMap<String, ArrayList<Message>> db = QQServer.db;
                        //~~判断集合中是否有getterId作为key~~不用判断,HashMap不允许重复key,直接往里放就行
                        //判断该getterId对应的db的value是否为空,为空new一个
                        //不为空直接添加,Map集合存放的是键值对,不存在只填key不填value
                        if (db.get(msg.getGetter()) == null) {
                            ArrayList<Message> msgs = new ArrayList<Message>();
                            msgs.add(msg);
                            db.put(msg.getGetter(), msgs);
                        } else {
                            ArrayList<Message> msgs = db.get(msg.getGetter());
                            msgs.add(msg);
                            db.put(msg.getGetter(), msgs);
                        }
                        message.setMesType(MessageType.FILE_MESSAGE_TO_ONE);
                        message.setHint("用户不在线,离线文件已上传");
                        ObjectOutputStream ois1 = new ObjectOutputStream(ManageClientThreads.getServerConnectClientThread(msg.getSender()).getSocket().getOutputStream());
                        ois1.writeObject(message);
                    }
                    else {

                        OutputStream outputStream = ManageClientThreads.getServerConnectClientThread(msg.getGetter()).getSocket().getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//                    oos.writeObject(message);
                        oos.writeObject(msg);//直接转发
                    }


                } else if (MessageType.MESSAGE_CLIENT_EXIT.equals(msg.getMesType())) {
                    //关闭socket 此时这个类中的socket 就是对应的那个socket
                    //从集合中移除socket
                    ManageClientThreads.removeServerConnectClientThread(msg.getSender());
                    socket.close();//关闭的是主线程接收到的某一个客户端的 socket连接管道 与其他客户端连接管道无关
                    System.out.println(msg.getSender() + "退出");

                    break;//退出本线程 直接跳出本次循环,不会再次执行到上面的 new ObjectInputStream(socket.getInputStream());管道关闭也没事
                } else {

                    System.out.println("暂时不处理");
            }
        } catch(Exception e){
            e.printStackTrace();
        }


    }


}
}
