package com.hsp.writer_;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2022-11-26-22:03
 */
public class FileWriter_ {
    public static void main(String[] args) {
        String filePath = "e:/note.txt";
        //创建FileWriter对象
        FileWriter fileWriter = null;
        char[] buf = {'a','b','c'};

        try {
            //创建FileWriter对象
            fileWriter = new FileWriter(filePath);//默认覆盖操作
            fileWriter = new FileWriter(filePath,true);//追加操作
            //写入，字符输出流
            //write(int):写入单个字符
            fileWriter.write('H');

            //write(char[]):写入指定数组
            fileWriter.write(buf);

            //write(char[],off,len):写入指定数组的指定部分
            fileWriter.write(buf,0,2);

            //write(String):写入整个字符串
            fileWriter.write("杨达在写程序");
            fileWriter.write("雨过之后，终见彩虹");

            //write(String,off,len):写入字符串的指定部分
            fileWriter.write("北京你好",0,2);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //相当于最终的写入操作在fileWriter.flush()、fileWriter.close()中
            //字符输出流最终一定要关闭资源,不然写不进去

            /*
               fileWriter.flush()源码：

             1.void implFlushBuffer() throws IOException {
                if (this.bb.position() > 0) {
                    this.writeBytes();
                }
              }
             2. private void writeBytes() throws IOException {
                this.bb.flip();
                int var1 = this.bb.limit();
                int var2 = this.bb.position();

                assert var2 <= var1;

                int var3 = var2 <= var1 ? var1 - var2 : 0;
                if (var3 > 0) {
                    if (this.ch != null) {
                        assert this.ch.write(this.bb) == var3 : var3;
                    } else {
                        //这里是最终写入文件操作，这个out,底层是FileOutputStream
                        this.out.write(this.bb.array(), this.bb.arrayOffset() + var2, var3);
                    }
                }

                this.bb.clear();
            }



             */


//            fileWriter.flush();


            try {
                fileWriter.close();//最好用close()
              /*
                 close()底层如下所示：最终也会进入writeBytes();

                1. void implClose() throws IOException {
                        this.flushLeftoverChar((CharBuffer)null, true);

                        try {
                            while(true) {
                                CoderResult var1 = this.encoder.flush(this.bb);
                                if (var1.isUnderflow()) {
                                    if (this.bb.position() > 0) {
                                        this.writeBytes();//在这进去
                                    }*/
                /*2. private void writeBytes() throws IOException {
                    this.bb.flip();
                    int var1 = this.bb.limit();
                    int var2 = this.bb.position();

                    assert var2 <= var1;

                    int var3 = var2 <= var1 ? var1 - var2 : 0;
                    if (var3 > 0) {
                        if (this.ch != null) {
                            assert this.ch.write(this.bb) == var3 : var3;
                        } else {
                            this.out.write(this.bb.array(), this.bb.arrayOffset() + var2, var3);
                        }
                    }

                    this.bb.clear();
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
