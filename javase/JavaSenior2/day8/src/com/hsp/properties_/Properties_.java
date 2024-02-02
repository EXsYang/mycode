package com.hsp.properties_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-10:33
 */
public class Properties_ {
    public static void main(String[] args) throws IOException {
        //读取mysql.properties 文件，并得到 ip user pwd
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Java_developer_tools\\javase\\JavaSenior2\\day8\\src\\mysql.properties"));
        String line = "";
        while ((line = bufferedReader.readLine()) != null){//循环读取
            String[] split = line.split("=");
            //split如果 按照xxx 进行分割 找不到要分割的值xxx 分割失败 split.length=1
            int length = split.length;
            System.out.println("length= " + length);

            //System.out.println(split[0] + "的值：" +split[1]);
        }

        bufferedReader.close();

        String content = "";
        String[] split = content.split(",");
        int length = split.length;
        System.out.println("空串使用逗号分割后的length= " + length);
        //空串使用逗号分割后的length= 1


    }
}
