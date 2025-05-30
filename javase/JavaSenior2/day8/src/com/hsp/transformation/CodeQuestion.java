package com.hsp.transformation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author yangda
 * @description:
 * @create 2022-11-28-16:10
 */
public class CodeQuestion {
    public static void main(String[] args) throws Exception {
        //读取e:/a.txt 文件到程序
        //思路
        //1. 创建字符输入流 BufferedReader [处理流]
        //2. 使用 BufferedReader 对象读取a.txt
        //3. 默认情况下，读取文件是按照 utf-8 编码
        String filePath = "e:/a.txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String s = br.readLine();

        // 如果将文件的保存方式改为ANSI，那么这里这段使用FileReader读取文件的代码，也就会读取成中文乱码。
        //默认是按照utf-8 解析 ,文件如果是ANSI(gbk)解析会出现中文乱码
        System.out.println("读取到的内容：" + s);//读取到的内容：�����д��ҵ��zehgndshfad
        br.close();





    }
}
