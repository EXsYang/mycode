package com.hsp.outputstream;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author yangda
 * @create 2024-11-24-20:24
 * @description: 文件编码测试类
 */
public class FileOutputStreamAndFileWriterTest {

    public static void main(String[] args) {
        // 测试用的中文内容
        String content = "Hello 世界！中文测试 123 测试换行符\n新的一行";

        // 1. FileOutputStream 测试不同编码方式
        testFileOutputStream(content);

        // 2. FileWriter 测试不同编码方式
        testFileWriter(content);

        // 3. 测试控制台输出编码
        testConsoleOutput(content);


        /**
         * // 对于Java 11及以上版本：(jdk8中FileWriter的构造方法不能在第二个参数位置直接指定字符编码)
         * try (FileWriter writer = new FileWriter("writer_utf8.txt", StandardCharsets.UTF_8)) {
         *     writer.write(content);
         *     System.out.println("FileWriter UTF-8编码写入完成");
         * }
         *
         * // 对于较旧的Java版本，可以使用以下替代方案：
         * // 方案1：使用OutputStreamWriter
         * try (OutputStreamWriter writer = new OutputStreamWriter(
         *         new FileOutputStream("writer_utf8.txt"), StandardCharsets.UTF_8)) {
         *     writer.write(content);
         *     System.out.println("FileWriter UTF-8编码写入完成");
         * }
         *
         * // 方案2：使用BufferedWriter
         * try (BufferedWriter writer = new BufferedWriter(
         *         new OutputStreamWriter(new FileOutputStream("writer_utf8.txt"), "UTF-8"))) {
         *     writer.write(content);
         *     System.out.println("FileWriter UTF-8编码写入完成");
         * }
         */
    }

    // 测试 FileOutputStream 的不同写入方式
    private static void testFileOutputStream(String content) {
        System.out.println("\n=== FileOutputStream 测试 ===");
        
        // 0. 编码验证测试
        try (FileOutputStream fos = new FileOutputStream("encoding_test.txt")) {
            String testStr = "测试中文";
            byte[] bytes = testStr.getBytes();
            fos.write(bytes);
            // 打印编码信息
            System.out.println("默认编码：" + System.getProperty("file.encoding"));
            System.out.println("字节数：" + bytes.length);
            // 如果输出12个字节，说明是UTF-8编码（每个中文3字节）
            // 如果输出8个字节，说明是GBK编码（每个中文2字节）
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * 1. 在IDEA中：
         *    - 默认编码通常是UTF-8
         *    - "测试中文"四个字占12字节，证实是UTF-8编码
         *    - IDEA打开文件时也用UTF-8，所以显示正常
         * 
         * 2. 在不同环境下：
         *    - Windows系统默认可能是GBK（每个中文占2字节）
         *    - Linux/Mac默认通常是UTF-8（每个中文占3字节）
         *    - 所以不同环境下字节数可能不同
         * 
         * 3. 潜在问题：
         *    - 用记事本打开可能乱码（ANSI编码）
         *    - 跨平台时可能出现乱码
         *    
         * 4. 建议：
         *    - 始终使用 content.getBytes(StandardCharsets.UTF_8)
         *    - 明确指定UTF-8编码，避免依赖默认编码
         */

        // 1. 默认编码写入
        try (FileOutputStream fos = new FileOutputStream("test_default.txt")) {
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            fos.write(bytes);
            System.out.println("UTF-8编码写入完成，字节数：" + bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * - UTF-8编码是最通用的编码方式
         * - 一个中文字符在UTF-8中占3个字节
         * - 文件可以被各种编辑器正确打开
         */

        // 2. GBK编码写入
        try (FileOutputStream fos = new FileOutputStream("test_gbk.txt")) {
            byte[] bytes = content.getBytes("GBK");
            fos.write(bytes);
            System.out.println("GBK编码写入完成，字节数：" + bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * - GBK主要用于中文Windows系统
         * - 一个中文字符在GBK中占2个字节
         * - 在非中文系统上打开可能会显示乱码
         */
    }

    // 测试 FileWriter 的不同写入方式
    private static void testFileWriter(String content) {
        System.out.println("\n=== FileWriter 测试 ===");

        // 1. 默认编码
        try (FileWriter writer = new FileWriter("writer_default.txt")) {
            writer.write(content);
            System.out.println("FileWriter默认编码写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * - FileWriter使用系统默认编码
         * - 不建议在跨平台应用中使用
         */

        // 2. 指定UTF-8编码（兼容性写法）
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream("writer_utf8.txt"), 
                    StandardCharsets.UTF_8))) {
            writer.write(content);
            System.out.println("FileWriter UTF-8编码写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * - 这种写法在所有Java版本中都能正常工作
         * - BufferedWriter提供了更好的写入性能
         * - 可以明确控制编码方式
         */

        // 3. 使用OutputStreamWriter写入GBK编码
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("writer_gbk.txt"), 
                "GBK")) {
            writer.write(content);
            System.out.println("FileWriter GBK编码写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * - OutputStreamWriter可以直接指定编码
         * - 比BufferedWriter的写法更简单
         * - 但没有缓冲区，性能可能较差
         */
    }

    // 测试控制台输出编码
    private static void testConsoleOutput(String content) {
        System.out.println("\n=== 控制台输出测试 ===");
        
        // 1. 默认输出
        System.out.println("默认输出：" + content);
        /* 测试结论：
         * - 控制台输出使用系统默认编码
         * - IDE中可能与操作系统默认编码不同
         */

        // 2. 使用PrintStream设置UTF-8
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.out.println("UTF-8设置后输出：" + content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /* 测试结论：
         * - 可以通过PrintStream改变输出编码
         * - 但这种改变是全局的，影响所有System.out的输出
         */

        // 3. 输出当前系统的编码信息
        System.out.println("系统默认编码：" + System.getProperty("file.encoding"));
        System.out.println("控制台编码：" + System.getProperty("sun.stdout.encoding", "未知"));
        /* 测试结论：
         * - file.encoding 显示JVM的默认编码
         * - sun.stdout.encoding 显示控制台的实际编码
         * - 这两个值可能不同，导致显示问题
         */
    }
}
