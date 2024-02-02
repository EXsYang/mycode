package com.hsp.printstream_;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-10:15
 */
public class PrintWriter_ {
    public static void main(String[] args) throws IOException {

//        PrintWriter printWriter = new PrintWriter(System.out);
        PrintWriter printWriter = new PrintWriter(new FileWriter("e:/ttt.txt"));
        printWriter.println("正在写代码啊ssdsdsssss");
//        printWriter.flush();    //
        printWriter.close();// flush + 关闭流 ,才会将数据写入到文件
    }
}
