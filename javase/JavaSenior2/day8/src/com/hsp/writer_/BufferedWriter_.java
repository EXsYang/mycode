package com.hsp.writer_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-27-16:03
 */
public class BufferedWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "e:/c.txt";
        //创建BufferedWriter
        //说明：
        //1.new FileWriter(filePath, true) 表示追加的方式写入
        //2.new FileWriter(filePath) 表示覆盖的方式写入
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write("杨达在学习");
        bufferedWriter.newLine();//插入一个和系统相关的换行符，用这个是最好的
        bufferedWriter.write("杨达在学习");
//        bufferedWriter.newLine();
        bufferedWriter.write("\r");//回车符号，也换行了
//        bufferedWriter.write("\n");//换行符，也换行了
//        bufferedWriter.write("\r\n");//回车换行符，也换行了还会空着一行

        bufferedWriter.write("杨达在学习2");
//        bufferedWriter.newLine();

        //说明：关闭外层流即可， 传入的 new FileWriter(filePath),会在底层关闭
        bufferedWriter.close();

    }
}
