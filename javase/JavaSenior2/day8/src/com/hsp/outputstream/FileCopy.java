package com.hsp.outputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-19:11
 */
public class FileCopy {
    public static void main(String[] args) {
        //完成文件拷贝， 将 e:\\A.webp 拷贝e:\\A.webp2
        //思路分析
        //1.创建文件的输入流，将文件读入到程序
        //2.创建文件的输出流，将读取到的文件数据，写入到指定的文件
        String srcPath = "e:/A.webp";
//        String srcPath = "e:/A.webp2";//后缀写错了，竟然也能打开？？？
        String destPath = "e:/A2.webp";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;



        try {
            fileInputStream = new FileInputStream(srcPath);
            fileOutputStream = new FileOutputStream(destPath);
            //定义一个字节数组，提高读取效率
            byte[] b = new byte[1024];
            int Length = 0;
            while ((Length = fileInputStream.read(b)) != -1){
                //读取到后，就写入到文件 通过fileOutputStream
                //即，是一边读，一边写
            fileOutputStream.write(b,0,Length);//一定要使用这个方法,防止文件损失，有可能写多了
            }
            System.out.println("拷贝成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭输入流和输出流，释放资源
                if (fileInputStream != null){
                    fileInputStream.close();
                }
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
