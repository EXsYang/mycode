package com.hsp.server;

import java.util.HashMap;

/**
 * @author yangda
 * @description: 该类管理客户端连接到服务器端线程的类
 * @create 2022-12-05-12:37
 */
public class ManageClientConnectServerThread {
    //把多个线程放在集合中进行管理
    private static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();

    //提供静态的方法，将线程放入到集合中
    public static void addManageClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread){
        hm.put(userId,clientConnectServerThread);
    }

    //通过userId 获得对应的线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }


}
