package com.hsp.writer_;

import java.io.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-27-16:41
 */
public class BufferedCopy_ {
    public static void main(String[] args) throws IOException {

        //说明：
        //1. BufferedReader 和 BufferedWriter 是按照字符操作
        //2. 不要去操作 二进制文件[ 声音，视频，doc,pdf ]， 可能造成文件损坏

        String srcFilePath = "e:\\c.txt";
        String destFilePath = "e:\\s.txt";
        String line = null;//用于接收按行读取的信息

        BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFilePath));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destFilePath));

        while ((line = bufferedReader.readLine()) != null){
            bufferedWriter.write(line);//每读取一行就写入
            bufferedWriter.newLine();//插入一个换行符
        }

//      关闭流
        bufferedReader.close();
        bufferedWriter.close(); //这里不关闭写不进去

    }
}
