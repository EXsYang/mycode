package com.hsp.qqserver.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @description: 该类用于管理服务器端和客户端连接的线程
 * @create 2022-12-05-15:51
 */
public class ManageClientThreads {

    public static HashMap<String,ServerConnectClientThread> hashMap = new HashMap<>();
//    public static ConcurrentHashMap<String,ServerConnectClientThread> cHashMap = new ConcurrentHashMap<>();//线程安全的


    public static HashMap<String, ServerConnectClientThread> getHashMap() {
        return hashMap;
    }

    public static void removeServerConnectClientThread(String userId){
    hashMap.remove(userId);
}

    public static void addServerConnectClientThread(String userId,ServerConnectClientThread serverConnectClientThread){
        hashMap.put(userId,serverConnectClientThread);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hashMap.get(userId);
    }

    public static String getOnlineUsers(){
        String users = "";
        Set<String> keySet = hashMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String user = iterator.next();
             users += user + " ";//在这里写报错，
        }
//        while(true){
//            String user += " 2";//这里报错Not a statement(不是声明)原因是，变量需要先声明后使用
//        }

        return users;
    }


}
