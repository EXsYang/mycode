package com.hsp.exer;

import javafx.beans.binding.When;
import jdk.nashorn.internal.ir.WhileNode;

import java.io.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-14:04
 */
public class Homework02 {
    public static void main(String[] args) throws IOException {

        String filePath = "e:/a.txt";
        String filePath2 = "e:/xxxx.txt";
        String readLine = "";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        //这里new后就会新建a.txt!!!  相当于进入while之前就成新建后的文件了，复制文件，路径不能相同
        // ,,,这里不使用追加，默认是覆盖操作
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath2));
        BufferedReader br1 = new BufferedReader(new FileReader(filePath2));

        System.out.println(br.readLine());//第一行
        System.out.println(br1.readLine());//null,此时br1 还在内存中，只有流关闭之后在读，才会写入磁盘！！！

        int line = 0;
        while ((readLine = br.readLine()) != null){//第二行开始
            ++line;
//            System.out.println("line=" + line);
            bw.write(line + readLine);//每读一行，往里面写一行，读完第一行后进行覆盖
            bw.newLine();
//            System.out.println(br.readLine());//null,怎么会读出null?,最后一次br.readLine()返回null,相当于调用了两次啊

            System.out.println(readLine);//边读边写边打印，读完readLine 变成null了
        }
        System.out.println(br.readLine());//读到结尾变成null,并没有重置

//        System.out.println("a.txt 读取如下：");
//        while ((readLine = br.readLine()) != null){//此时 br.readLine() = null;
//            System.out.println(readLine);
//        }
        System.out.println("xxxx.txt 读取如下：");

        //!!!!!!!!!!!//null,此时br1 还在内存中，只有流关闭之后在读，才会写入磁盘！！！
        System.out.println(br1.readLine());//null
        while ((readLine = br1.readLine()) != null){
            System.out.println(readLine);
        }


        bw.close();
        br.close();


        System.out.println("bw流关闭之后：");
        System.out.println(br1.readLine());//null
        while ((readLine = br1.readLine()) != null){
            System.out.println(readLine);
        }
        System.out.println("写入磁盘之后进行读取 ，成功！！！");

        br1.close();
        System.out.println("读取完成！");
    }
}
