package com.hsp.inputstream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-16:26
 */
public class FileInputStream_ {
    public static void main(String[] args) {
        //FileInputStream("String pathName")
        // 在main方法中,此种方式读取文件位置默认为Project目录下
        // 在@Test方法中,此种方式读取文件位置默认为Module目录下
//        FileInputStream is = new FileInputStream("JDBC\\src\\Resource\\druid.properties");


        String filePath = "e:\\hello.txt";
        FileInputStream fileInputStream = null;//局部变量噢，显式赋值一下
        int readData = 0;
        try {
            //创建 FileInputStream 对象，用于 读取文件
            fileInputStream = new FileInputStream(filePath);
            //read()  从该输入流读取一个字节的数据，如果没有输入可用，此方法将阻止
            //如果返回-1，表示读取完毕
            System.out.println("main:");
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData);//转成char显示 ===>引出疑问，直接将int类型转换成char,会不会变成一个字符？
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    使用read() 读取文件 ，一个字节一个字节读取
     */
    @Test
    public void test1() {

        String filePath = "e:\\hello.txt";
        FileInputStream fileInputStream = null;//局部变量噢，显式赋值一下
        int readData = 0;
        try {
            //创建 FileInputStream 对象，用于 读取文件
            fileInputStream = new FileInputStream(filePath);
            //read()  从该输入流读取一个字节的数据，如果没有输入可用，此方法将阻止
            //如果返回-1，表示读取完毕
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData);//转成char显示 ===>引出疑问，直接将int类型转换成char,会不会变成一个字符？
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /*
        使用 read(byte[] b) ,读取文件提高效率
     */
    @Test
    public void test2() {
        //字节输入流
        String filePath = "e:\\hello.txt";
        FileInputStream fileInputStream = null;//局部变量噢，显式赋值一下
        int readLen = 0;
        //字节数组
        byte[] buf = new byte[8];//一次读取八个字节

        try {
            //创建 FileInputStream 对象，用于 读取文件
            fileInputStream = new FileInputStream(filePath);
            //read(byte[] b) 从该输入流读取最多b.length字节的数据到字节数组，此方法将阻塞，直到某些输入可用
            //如果返回-1，表示读取完毕
            //如果读取正常，返回实际读取的字节数
            while ((readLen = fileInputStream.read(buf)) != -1) {
                //从0开始，构建length几个
//                System.out.println("readLen :" + readLen);
                System.out.print(new String(buf, 0, readLen));//hello,world   转成char显示 ===>引出疑问，直接将int类型转换成char,会不会变成一个字符？
//                hello,world
//                System.out.print(new String(buf,0,2));//从0开始算，读两个buf中的字节构建字符串 herl

//                System.out.print((char)97);/a
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件流，释放资源
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    @Test
    public void test3() {
        //字节输入流
        String filePath = "D:\\Java_developer_tools\\javase\\JavaSenior2\\day8\\src\\mysql.properties";
        FileInputStream fileInputStream = null;//局部变量噢，显式赋值一下
        int readLen = 0;
        //字节数组
        byte[] buf = new byte[8];//一次读取八个字节


            //创建 FileInputStream 对象，用于 读取文件
        try {
            fileInputStream = new FileInputStream(filePath);
            System.out.println(fileInputStream);
            Properties properties = new Properties();
            properties.load(fileInputStream); // 这里传进去一个InputStream或者Reader类型
            System.out.println("==========");
            properties.list(System.out);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
