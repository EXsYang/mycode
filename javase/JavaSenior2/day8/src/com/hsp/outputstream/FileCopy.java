package com.hsp.outputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-19:11
 */
public class FileCopy {
    public static void main(String[] args) {
        //完成文件拷贝， 将 e:\\A.webp 拷贝e:\\A.webp2
        //思路分析
        //1.创建文件的输入流，将文件读入到程序
        //2.创建文件的输出流，将读取到的文件数据，写入到指定的文件
        String srcPath = "e:/A.webp";
//        String srcPath = "e:/A.webp2";//后缀写错了，竟然也能打开？？？
        String destPath = "e:/A2.webp";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;



        try {
            fileInputStream = new FileInputStream(srcPath);
            fileOutputStream = new FileOutputStream(destPath);
            //定义一个字节数组，提高读取效率
            byte[] b = new byte[1024];
            int Length = 0;
            // read(b)方法会尝试读取最多b.length个字节到字节数组b中
            // 返回值Length表示实际读取到的字节数
            // 如果返回-1，表示已经到达文件末尾
            while ((Length = fileInputStream.read(b)) != -1){
                //读取到后，就写入到文件 通过fileOutputStream
                //即，是一边读，一边写
                // Length很重要，因为最后一次读取可能不足1024字节
                // 如果不使用Length作为写入长度，可能会写入上次读取遗留在数组中的数据
                fileOutputStream.write(b,0,Length);//一定要使用这个方法,防止文件损失，有可能写多了
           
                // 详细说明：
                // fileInputStream.read(b)的工作原理：
                // 尝试从文件读取最多1024个字节（b.length）
                // 将读取到的字节存储到数组b中
                // 返回实际读取到的字节数量
                // 如果到达文件末尾，返回-1

                // -------------------------------------------------------
                // 为什么要用Length：
                // 在Java中，字节数组（byte[]）的特点是：
                // 一旦创建，其大小就固定了（比如1024字节）
                // 【数组的内容不会自动清空，只会被新数据覆盖

                byte[] bytes = new byte[1024]; // 创建一个1024字节的数组

                // 第一次读取
                Length = fileInputStream.read(bytes);  // 假设读取1024字节
                // bytes数组现在包含了1024个新读取的字节

                // 第二次读取
                Length = fileInputStream.read(bytes);  // 假设读取1024字节
                // bytes数组的内容被新的1024个字节完全覆盖

                // 第三次读取（最后一次）
                Length = fileInputStream.read(bytes);  // 假设只读取了452字节
                // bytes数组的前452个字节被新数据覆盖
                // 但位置452-1023的字节仍然保留着上次读取的数据！

                // 如果这样写：
                fileOutputStream.write(bytes); // 错误！会写入全部1024字节
                // 导致写入了452字节的新数据和572字节的旧数据

                // 正确的写法：
                fileOutputStream.write(bytes, 0, Length); // 正确！只写入452字节的新数据

                // -------------------- ------------------------------


           
            }
            System.out.println("拷贝成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭输入流和输出流，释放资源
                if (fileInputStream != null){
                    fileInputStream.close();
                }
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
