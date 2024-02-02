package com.hsp.reader_;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author yangda
 * @description:
 * @create 2022-11-27-15:53
 */
public class BufferedReader_ {
    public static void main(String[] args) throws Exception {
        String filePath = "e:/a.txt";
        //创建bufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        //读取
        String line;//按行读取，效率高
        //说明
        //1. bufferedReader.readLine() 是按行读取文件
        //2. 当返回null 时，表示文件读取完毕
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }

        //关闭外层处理流即可，传入的 new FileReader(filePath) 会在底层自动关闭
        bufferedReader.close();

        //源码：
        /*
         1.public void close() throws IOException {
            synchronized (lock) {
                if (in == null)
                    return;
                try {
                    in.close(); //in 就是 fileReader对象
         */
    }
}
