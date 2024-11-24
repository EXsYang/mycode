package com.hsp.transformation;

import java.io.*;

/**
 * @author yangda
 * @description: 演示字节流和转换流在处理编码时的区别
 * @create 2024-03-20
 */
public class EncodingExampleDemo {
    public static void main(String[] args) {
        String filePath = "e:/encoding_test.txt";
        String content = "你好，世界！Hello World!";

        // 1. 字节流处理编码的方式
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // 字节流只能在转换为字节数组时指定编码
            byte[] bytes = content.getBytes("GBK"); // 编码发生在这里
            fos.write(bytes); // 字节流只负责写入字节，不处理编码
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取时也需要在转换字节数组为字符串时指定编码
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] bytes = new byte[1024];
            int len = fis.read(bytes);
            // 解码必须与写入时使用相同的编码，否则会乱码
            String result = new String(bytes, 0, len, "GBK"); // 解码发生在这里
            System.out.println("字节流读取结果：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 转换流处理编码的方式
        try (OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(filePath), "GBK")) { // 在转换流层面指定编码
            osw.write(content); // 直接写入字符串，转换流自动处理编码
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取时也在转换流层面指定编码
        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream(filePath), "GBK")) { // 指定相同的编码
            char[] chars = new char[1024];
            int len = isr.read(chars);
            String result = new String(chars, 0, len);
            System.out.println("转换流读取结果：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/* 关键点总结：
 * 1. 字节流的特点：
 *    - 本身不处理编码
 *    - 编码/解码发生在字符串与字节数组的转换时
 *    - 需要手动处理编码转换
 *    - 容易出现乱码问题
 *
 * 2. 转换流的优势：
 *    - 在流的层面指定编码
 *    - 自动处理字符编码转换
 *    - 更方便处理文本文件
 *    - 减少乱码问题
 *
 * 3. 最佳实践：
 *    - 处理文本文件时优先使用转换流
 *    - 始终明确指定编码，不使用默认编码
 *    - 确保读写使用相同的编码
 *    - 建议使用UTF-8编码
 */