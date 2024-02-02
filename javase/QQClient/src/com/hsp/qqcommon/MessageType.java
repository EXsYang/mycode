package com.hsp.qqcommon;

/**
 * @author yangda
 * @description:
 * @create 2022-12-04-18:40
 */
public interface MessageType {

    //1. 在接口中定义一些常量
    //2. 不同的常量的值，表示不同的消息类型

    String  MESSAGE_LOGIN_SUCCEED = "1";//表示登陆成功
    String  MESSAGE_LOGIN_FAIL = "2";//表示登录失败
    String MESSAGE_COMM_MES = "3";//普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";//要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";//返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";//客户端请求退出
    String MESSAGE_TO_ALL_MES = "7";//普通信息包
    String FILE_MESSAGE_TO_ONE = "8";//发送文件
    String GETTER_EXIST = "9";//表示getter 线程存在
    String GETTER_NOT_EXIST = "10";//表示getter 线程不存在
    String GETTER_CHECK = "11";//检查用户是否在线




}
