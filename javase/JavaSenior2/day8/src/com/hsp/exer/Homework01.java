package com.hsp.exer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-12:33
 */
public class Homework01 {
    public static void main(String[] args) throws IOException {
        String dirPath = "e:/mytemp";
        String filePath = "e:/mytemp/hello.txt";
        File dir = new File(dirPath);
        File file = new File(filePath);

        if (!dir.exists()){
            dir.mkdir();
        }
        if (!file.exists()){
            file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write("hello world");
            bw.close();


            System.out.println(file.getName() + "文件创建成功！");
        }else {
            System.out.println(file.getName() + "文件已存在，不可以重复创建");
        }





    }
}
