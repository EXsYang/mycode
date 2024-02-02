package com.hsp.outputstream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-18:01
 */
public class FileOutputStream01 {
    public static void main(String[] args) {

    }

    @Test
    public void writeFile(){
        //创建 FileOutputStream 对象
        String filePath = "e:\\a.txt";
        FileOutputStream fileOutputStream = null;

        try {
            //得到 FileOutputStream 对象
            //说明：
            //1. new FileOutputStream(filePath); 创建方式，当写入内容时，会覆盖原来的内容
            //2. new FileOutputStream(filePath,true); 创建方式，当写入内容时，追加到原来的内容后面

            //创建对象时自动生成a.txt文件，不需要再去调用createNewFile
//            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream = new FileOutputStream(filePath,true);
            //写入一个字节
//            fileOutputStream.write('G');
            //写入一个字符串
            String str = "yangda,world！！！！！！！";
            //str,getBytes() 可以把 字符串-> 字节数组
//            fileOutputStream.write(str.getBytes());

            /*
            write(byte[] b,int off, int len) 将 len字节从位于偏移量 off 的指定字节数组写入此文件输出流
             */
            fileOutputStream.write(str.getBytes(),0,3);
            fileOutputStream.write(str.getBytes(),0,str.length());


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileOutputStream.close();//这里不关闭流可以写进去
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
