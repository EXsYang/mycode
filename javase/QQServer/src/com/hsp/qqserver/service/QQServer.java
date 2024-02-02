package com.hsp.qqserver.service;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;
import com.hsp.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description:
 * @create 2022-12-05-15:50
 */
public class QQServer {
    ServerSocket serverSocket = null;
    Socket socket = null;

    public static HashMap<String, User> validUsers = new HashMap<>();
    public static ConcurrentHashMap<String, ArrayList<Message>> db = new ConcurrentHashMap<>(); //线程安全的,存放离线留言信息
    public static ArrayList<Message> msgList = new ArrayList<>();
    static {//在静态代码块 初始化
        validUsers.put("1", new User("1", "1"));
        validUsers.put("2", new User("2", "1"));
        validUsers.put("3", new User("3", "1"));
        validUsers.put("孙悟空", new User("孙悟空", "1"));
        validUsers.put("紫霞仙子", new User("紫霞仙子", "123456"));
        validUsers.put("菩提老祖", new User("菩提老祖", "123456"));
        validUsers.put("如来佛祖", new User("如来佛祖", "123456"));
        validUsers.put("张无忌", new User("张无忌", "123456"));
    }


    public QQServer() {
        try {
            System.out.println("服务器在9999端口监听...");
            new Thread(new SendNewsToAllService()).start();//放在最外面

            //客户端上线追踪方式二:
            new MonitoringUsersLogIn().start();

            serverSocket = new ServerSocket(9999);
            //服务器端在9999 端口 循环监听
            while (true) {

                socket = serverSocket.accept();//如果没有客户端连接将阻塞
                //获取客户端发送来的User对象进行验证

                //客户端上线追踪方式一:
                //System.out.println(socket.getRemoteSocketAddress() + ": 上线啦");


                System.out.println("客户端进行连接...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User u = (User) ois.readObject();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = new Message();
                //验证用户账号密码
                if (checkUser(u.getUserId(), u.getPasswd())) {
                    //验证成功，回复给客户端Message
                    System.out.println("用户：" + u.getUserId() + "\t登录成功");
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
                    //启动一个线程 保持通信
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUserId());
                    serverConnectClientThread.start();
                    //将该线程放入到集合中
                    ManageClientThreads.addServerConnectClientThread(u.getUserId(), serverConnectClientThread);


                } else {//验证失败，回复给客户端Message
                    System.out.println("用户：" + u.getUserId() + "\t登录失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
                //将message写入数据通道中

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    //验证客户账户是否有效的fangfa
    public boolean checkUser(String userId, String pwd) {
        User user = validUsers.get(userId);//首先判断是否存在
        if (user == null) {//
            return false;
        }

        return user.getPasswd().equals(pwd);


    }


}
