package com.hsp.transformation;

import java.io.*;
import java.nio.Buffer;

/**
 * @author yangda
 * @description:
 * 使用 InputStreamReader 转换流解决中文乱码问题
 * 将字节流 FileInputStream 转成字符流 InputStreamReader,指定编码 gbk/utf-8
 *
 * @create 2022-11-28-16:19
 */
public class InputStreamReader_ {
    public static void main(String[] args) throws IOException {

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

        bufferedReader.close();

    }
}
