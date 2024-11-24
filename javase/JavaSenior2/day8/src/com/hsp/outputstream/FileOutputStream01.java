package com.hsp.outputstream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-18:01
 */
public class FileOutputStream01 {
    public static void main(String[] args) {

    }

    @Test
    public void writeFile(){


         // 设置控制台输出编码为GBK
        //  System.setOut(new PrintStream(System.out, true, "GBK")); //在Cursor中使用这个，将Cursor的Terminal（PowerShell）设置为GBK

         // System.setOut(new PrintStream(System.out, true, "utf-8"));  //在idea或者Windsurf中使用这个
 


        //创建 FileOutputStream 对象
        String filePath = "e:\\a.txt";
        FileOutputStream fileOutputStream = null;

        try {
            //得到 FileOutputStream 对象
            //说明：
            //1. new FileOutputStream(filePath); 创建方式，当写入内容时，会覆盖原来的内容
            //2. new FileOutputStream(filePath,true); 创建方式，当写入内容时，追加到原来的内容后面

            //创建对象时自动生成a.txt文件，不需要再去调用createNewFile
           fileOutputStream = new FileOutputStream(filePath);//这里创建的输出流对象是覆盖的形式写入
           //  fileOutputStream = new FileOutputStream(filePath,true); //这里创建的输出流对象是追加的形式写入
            //写入一个字节
//            fileOutputStream.write('G');
            // 要写入的字符串
            String str = "yangda,world！！！！！！！the world of 天堂制作~";
            //str,getBytes() 可以把 字符串-> 字节数组


            // 查看字节数组情况
            byte[] bytes = str.getBytes();
            // 注意：str.length()返回的是字符数，而不是字节数
            // 特别是对于中文字符，在UTF-8编码下一个中文字符占3个字节
            System.out.println("字符串的长度= " + str.length());
            // 建议使用bytes.length获取实际的字节数
            System.out.println("字节数组的长度= " + bytes.length);
            System.out.println("java.version: " + System.getProperty("java.version"));

            System.out.println("字节数组： "  + bytes);
            // System.out.println("字节数组输出如下");
            // for (byte aByte : bytes) {
            //     System.out.println(aByte); //输出为单个字符对应的ASCII码
            // }

            System.out.println("java.version: " +System.getProperty("java.version"));

//            fileOutputStream.write(str.getBytes());


             /*
            write(byte[] b,int off, int len) 将 len字节从位于偏移量 off 的指定字节数组写入此文件输出流
             */
            // 写入文件的几种方式：
            // 1. 写入单个字节
            // fileOutputStream.write('G');

            // 2. 写入字节数组（使用默认编码）
            // fileOutputStream.write(str.getBytes());

            // 3. 写入指定编码的字节数组（推荐，编码明确）
            // fileOutputStream.write(str.getBytes("UTF-8"));

            // 4. 写入字节数组的部分内容
            // 注意：当写入部分内容时，应使用字节数组的长度而不是字符串长度
            // 错误示例：fileOutputStream.write(bytes, 0, str.length()); // 可能导致中文字符截断
            // 正确示例：
            fileOutputStream.write(bytes, 0, bytes.length); // 从0开始，写入整个字节数组

            // 从第0号位开始写入，写入字符串的长度str.length()个字节。如果有需要使用多个字节保存的字符则可能会截断！
            // 比如在UTF-8 编码中，一个中文占3个字节，一个英文占1个字节，所以中文字符可能会截断。
            // fileOutputStream.write(str.getBytes(),0,str.length()); // 这种方式保存的文件是ANSI格式的，显示的是乱码


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileOutputStream.close();//这里不关闭流可以写进去
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
