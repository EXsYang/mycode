package com.hsp.exer;

import java.io.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-15:12
 */
public class Homework03 {
    public static void main(String[] args) throws IOException {
        String filePath2 = "e:/xxxx.txt";
        String readLine = "";

        //这里new后就会新建a.txt!!!  相当于进入while之前就成新建后的文件了，复制文件，路径不能相同
        // ,,,这里不使用追加，默认是覆盖操作


        BufferedReader br1 = new BufferedReader( new InputStreamReader(new FileInputStream(filePath2),"gbk"));
        int line = 0;

//        System.out.println("a.txt 读取如下：");
//        while ((readLine = br.readLine()) != null){//此时 br.readLine() = null;
//            System.out.println(readLine);
//        }
        System.out.println("xxxx.txt 读取如下：");
        System.out.println(br1.readLine());//null
        while ((readLine = br1.readLine()) != null){
            System.out.println(readLine);
        }
        br1.close();
        System.out.println("读取完成！");

    }
}
