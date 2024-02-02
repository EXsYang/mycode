package com.hsp.transformation;

import java.io.*;
import java.util.LinkedList;

/**
 * @author yangda
 * @description: OutputStreamWriter 使用
 *              把 FileOutputStream 字节流 转换成 OutputStreamWriter字符流
 *              指定处理的编码 gbk / utf-8/utf8
 * @create 2022-11-28-21:57
 */
public class OutputStreamWriter_ {
    public static void main(String[] args) throws IOException {

        String filePath = "e:/b.txt";
        String charSet = "gbk";//ANSI
        String charSet1 = "utf-8";

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "gbk");



        osw.write("杨达在学习，写代码中sss~~");

        osw.close();//这里不关写不进去！！！

        System.out.println("写入成功");


    }
}
