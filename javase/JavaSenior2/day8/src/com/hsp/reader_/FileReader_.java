package com.hsp.reader_;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-21:01
 */
public class FileReader_ {
    public static void main(String[] args) {

    }

    @Test
    public void readFile01(){
        //字符输入流
        String path = "e:/story.txt";
        int data = 0;
        FileReader fileReader = null;
//        创建文件
        File file = new File(path);
        try {
            //如果已经创建过了，或是文件名相同，目录下不会创建新文件，但是会执行
            boolean newFile = file.createNewFile();

            System.out.println("创建文件成功");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            fileReader = new FileReader(path);
            //循环读取 使用read(),单个字符读取, 读取到data中
            while ((data = fileReader.read()) != -1){//单个字符读取
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void readFile02(){
        //字符输入流
        String path = "e:/story1.txt";
        char[] buf = new char[3];
        int readLen = 0;
        FileReader fileReader = null;
//        创建文件
        File file = new File(path);

        try {
            //如果已经创建过了，或是文件名相同，目录下不会创建新文件，但是会执行
            boolean newFile = file.createNewFile();

            System.out.println("创建文件成功");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            fileReader = new FileReader(path);
            //循环读取 使用read(buf),返回的是实际读取到的字符数
            while ((readLen = fileReader.read(buf)) != -1){//返回-1，到文件结尾
                System.out.print(new String(buf));//abcededae ,这样写会读多了
//                System.out.print(new String(buf,0,readLen));//abcededa
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
