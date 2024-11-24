package com.hsp.transformation;

import java.io.*;
import java.nio.Buffer;

/**
 * @author yangda
 * @description: InputStreamReader是字符流，是Reader的子类
 *
 * 使用 InputStreamReader 转换流解决中文乱码问题
 * 将字节流 FileInputStream 转成字符流 InputStreamReader,指定编码 gbk/utf-8
 * 字节流转换为字符流，转换流可以指定编码
 * 
 *
 * @create 2022-11-28-16:19
 */
public class InputStreamReader_ {
    public static void main(String[] args) throws IOException {


        
        /* 编码处理的关键点：
        * 1. 字节流转字符流的过程：
        *    FileInputStream（字节流）
        *    -> InputStreamReader（转换流，可指定编码）
        *    -> BufferedReader（缓冲字符流，提升性能）
        *
        * 2. 编码指定的位置：
        *    在创建 InputStreamReader 时指定，如：
        *    new InputStreamReader(new FileInputStream(filePath), "gbk")
        *    
        * 3. 为什么要使用转换流：
        *    - 字节流不能直接指定编码
        *    - 必须通过转换流作为桥梁来处理编码
        *    - 可以解决中文乱码问题
        */



        String filePath = "e:/a.txt";
        String readLen = null;
        //1.将 FileInputStream 转换成 InputStreamReader
        //2.指定编码 "gbk"
//        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath),"gbk");
        //3.将InputStreamReader 传入 BufferedReader
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //
        //可以将2、3合在一起
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"gbk"));

        while ((readLen = bufferedReader.readLine()) != null){
            System.out.println(readLen);
        }

         // 关闭流（只需要关闭最外层的流）
        bufferedReader.close();

    }
}
