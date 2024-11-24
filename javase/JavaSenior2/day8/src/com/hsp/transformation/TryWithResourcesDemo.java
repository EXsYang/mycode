package com.hsp.transformation;

import java.io.*;

/**
 * @author yangda
 * @create 2024-03-20
 * @description: 演示try-with-resources和传统try-catch-finally的区别
 *
 * 主要区别：
 * 资源声明位置：
 * try-catch-finally：需要在try外部声明变量
 * try-with-resources：在try括号内声明和初始化
 * 关闭资源：
 * try-catch-finally：需要手动在finally中关闭，还要处理close()可能抛出的异常
 * try-with-resources：自动关闭，不需要写关闭代码
 * 代码量：
 * try-catch-finally：需要更多的样板代码
 * try-with-resources：代码更简洁
 *
 *
 */
public class TryWithResourcesDemo {
    public static void main(String[] args) {
        String filePath = "e:/test.txt";

        System.out.println("=== 1. 传统try-catch-finally方式 ===");
        traditionalWay(filePath);

        System.out.println("\n=== 2. try-with-resources方式 ===");
        modernWay(filePath);

        System.out.println("\n=== 3. 处理多个资源的对比 ===");
        multipleResourcesComparison(filePath);
    }

    /**
     * 传统的try-catch-finally方式
     */
    private static void traditionalWay(String filePath) {
        // 1. 需要在外部声明变量
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filePath),
                            "UTF-8"
                    )
            );
            String line = bufferedReader.readLine();
            System.out.println("读取的内容: " + line);

        } catch (IOException e) {
            System.out.println("读取过程发生错误: " + e.getMessage());

        } finally {
            // 2. 需要手动关闭资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    System.out.println("资源已关闭");
                } catch (IOException e) {
                    System.out.println("关闭资源时发生错误: " + e.getMessage());
                }
            }
        }
    }

    /**
     * 现代的try-with-resources方式
     */
    private static void modernWay(String filePath) {
        // 1. 资源的声明和初始化在try的括号中
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath),
                        "UTF-8"
                ))) {
            String line = bufferedReader.readLine();
            System.out.println("读取的内容: " + line);

        } catch (IOException e) {
            System.out.println("读取过程发生错误: " + e.getMessage());
        }
        // 2. 不需要手动关闭资源，会自动关闭
    }

    /**
     * 多个资源的处理对比
     */
    private static void multipleResourcesComparison(String filePath) {
        System.out.println("传统方式处理多个资源：");
        traditionalMultipleResources(filePath);

        System.out.println("\ntry-with-resources处理多个资源：");
        modernMultipleResources(filePath);
    }

    /**
     * 传统方式处理多个资源
     */
    private static void traditionalMultipleResources(String filePath) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            // 1. 需要分别声明和初始化
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);

            String line = br.readLine();
            System.out.println("读取的内容: " + line);

        } catch (IOException e) {
            System.out.println("读取过程发生错误: " + e.getMessage());

        } finally {
            // 2. 需要按照反向顺序分别关闭每个资源
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("关闭BufferedReader时发生错误: " + e.getMessage());
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    System.out.println("关闭InputStreamReader时发生错误: " + e.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println("关闭FileInputStream时发生错误: " + e.getMessage());
                }
            }
        }
    }

    /**
     * try-with-resources方式处理多个资源
     */
    private static void modernMultipleResources(String filePath) {
        // 1. 多个资源在try括号中声明，用分号分隔
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
             BufferedReader br = new BufferedReader(isr)) {

            String line = br.readLine();
            System.out.println("读取的内容: " + line);

        } catch (IOException e) {
            System.out.println("读取过程发生错误: " + e.getMessage());
        }
        // 2. 资源会按照声明的相反顺序自动关闭
    }

    /* try-with-resources的优势：
     * 1. 代码更简洁：
     *    - 不需要显式的finally块
     *    - 不需要手动关闭资源
     *
     * 2. 更安全：
     *    - 自动关闭资源，不会遗漏
     *    - 即使发生异常也能确保资源关闭
     *    - 多个资源会按照正确的顺序关闭
     *
     * 3. 异常处理更好：
     *    - 保留原始异常
     *    - 如果close()时发生异常，会被抑制并添加到原始异常中
     *    - 可以通过Throwable.getSuppressed()获取被抑制的异常
     *
     * 4. 使用限制：
     *    - 资源必须实现AutoCloseable接口
     *    - Java 7及以上版本支持
     */
}