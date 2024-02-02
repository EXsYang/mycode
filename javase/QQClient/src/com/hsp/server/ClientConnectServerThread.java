package com.hsp.server;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author yangda
 * @description: Connect连接
 * @create 2022-12-05-12:27
 */
public class ClientConnectServerThread extends Thread {

    //持有socket 的线程
    private Socket socket;
//    private String userId;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    //提供get方法便于管理
    public Socket getSocket() {
        return socket;
    }

//    public void setUserId(String userId) {//设置UserId 便于管理
//        this.userId = userId;
//    }

    @Override
    public void run() {
        //一直在数据通道中读取数据，保持数据通信
        while (true) {
            try {
                System.out.println("客户端线程，等待读取，从服务器端发送来的Message");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();//如果没有读到数据，这里会阻塞
                //这里还没有用到msg
                //接收服务器端返回的在线用户列表信息
                if (MessageType.MESSAGE_RET_ONLINE_FRIEND.equals(msg.getMesType())) {
                    //规定用户列表用空格间隔
                    String[] s = msg.getContent().split(" ");
                    System.out.println();
                    System.out.println("========用户列表========");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户：" + s[i]);
                    }
                } else if (MessageType.MESSAGE_TO_ALL_MES.equals(msg.getMesType())) {
                    System.out.println("\n" + msg.getSender() + " 对大家说：" + msg.getContent());
                } else if (MessageType.MESSAGE_COMM_MES.equals(msg.getMesType())) {
                    if (msg.getGetter() == null){
                        System.out.println("离线中...  "+ msg.getHint());
                    }else {
                        System.out.println("\n" + msg.getSender() + " 对 " + msg.getGetter() + "说：" + msg.getContent());
                    }
                } else if (MessageType.FILE_MESSAGE_TO_ONE.equals(msg.getMesType())) {

                    if (msg.getGetter() == null){
                        System.out.println("离线中...  "+ msg.getHint());
                    }else {
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(msg.getDest()));
                        bos.write(msg.getFile());
//                    bos.flush();
                        bos.close(); //测试不关可以写进去吗？没有关时，文件确实生成了，但是没有写进去是空的，一定要关闭或刷新

                        System.out.println("\n" + msg.getSender() + " 给 " + msg.getGetter() + "发送文件：" + msg.getSrc() +
                                "到我的电脑目录 " + msg.getDest());
                        System.out.println("保存文件 OK");
                    }
                }
//                }else if (MessageType.GETTER_CHECK.equals(msg.getMesType())){
//                    String getterCheck = msg.getContent();
//                    UserClientService.getterCheck = getterCheck; //将检查的结果返回给客户端
//
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
