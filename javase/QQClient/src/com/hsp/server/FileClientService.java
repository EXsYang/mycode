package com.hsp.server;

import com.hsp.qqcommon.Message;
import com.hsp.qqcommon.MessageType;

import java.io.*;
import java.util.Date;

/**
 * @author yangda
 * @description: 该类提供文件相关的服务
 * @create 2022-12-06-10:02
 */
public class FileClientService {
public static int count = 0;
    //发送文件相关方法
    public void sendFileToOne(String senderId, String getterId) {
        Message message = new Message();

        String filePath = "e:/A.webp";
        String destPath = "e:/" + getterId + (++count) +".webp";
        byte[] array = null;

        byte[] buf = new byte[1024];
//        byte[] buf1 = new byte[(int)new File(filePath).length()];
        int readLen = 0;

        //将文件读取到字节数组中
        BufferedInputStream bis = null;
        ByteArrayOutputStream byteArrayOutputStream = null;



        try {
            bis = new BufferedInputStream(new FileInputStream(filePath));
            byteArrayOutputStream = new ByteArrayOutputStream();//将文件写入到字节数组
            while ((readLen = bis.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, readLen);
            }
            array = byteArrayOutputStream.toByteArray();//返回一个存放所有数据的数组
//            byteArrayOutputStream.flush();//刷新

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();//这个不关闭也可以写入进去
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        message.setSender(senderId);
        message.setGetter(getterId);
        message.setMesType(MessageType.FILE_MESSAGE_TO_ONE);
        message.setSrc(filePath);
        message.setDest(destPath);
        message.setSendTime(new Date().toString());
        message.setFile(array);//设置文件
        message.setFileLength(array.length);

        //将文件发送到服务器
        try {
            OutputStream outputStream = ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);
//            oos.flush();
            System.out.println("\n" + senderId + " 给 " + getterId + "发送文件：" + filePath +
                    "到对方电脑目录 " + destPath);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
