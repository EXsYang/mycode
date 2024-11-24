package com.hsp.outputstream;

import java.io.*;

/**
 * @author yangda
 * @description: 使用BufferedInputStream 和 BufferedOutputStream 拷贝  字节处理(包装)流
 *                可以完成二进制文件拷贝  也可以操作文本文件，字符也是二进制 一个汉字三个字节构成，一个字节一个字节读取 完全可以
 *
 *
 * @create 2022-11-27-17:17
 */
public class BufferedCopy02 {
    public static void main(String[] args) {

        String srcFilePath = "e:/A.webp";
        String destFilePath = "e:/A3.webp";

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        byte[] buf = new byte[1024];//注意：不要用空数组，会抛异常
        int readLength = 0;
        try {
            //因为 FileInputStream 是 InputStream 子类 可以传进来
            bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFilePath));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFilePath));

            //循环读取文件，并写入到 destFilePath
            while ((readLength = bufferedInputStream.read(buf)) != -1) {//.NullPointerException
                bufferedOutputStream.write(buf, 0, readLength);
            }
//            bufferedOutputStream.flush();//直接关闭 也可以 关闭就会写进去了 不用刷新
            System.out.println("拷贝完成");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }

                //        fileOutputStream、bufferedOutputStream 同样只有三个write() 方法

                //BufferedWriter BufferedOutputStream  &&  OutputStreamWriter  字符输出流 不关闭就写不进去
                //FileOutputStream ObjectOutputStream这个字节输出流不关闭可以写进去

                //BufferedWriter BufferedOutputStream.close(); 这里不关闭就写入不进去！！！！
                //写入时一定要记得关闭流，只关外层流就行，底层会自动关闭传进来的流  new FileOutputStream(destFilePath)
                if (bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
                System.out.println("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
