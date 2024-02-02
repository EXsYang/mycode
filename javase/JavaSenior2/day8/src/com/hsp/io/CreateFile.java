package com.hsp.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author yangda
 * @description:
// 1. 创建文件时 路径中的正斜杠"/"和两个反斜杠"\\"效果是一样的，都可以创建成功！！
   如： File uploadPhoto = new File(file.getAbsolutePath() + "\\" + originalFilename);
        File uploadPhoto2 = new File(file.getAbsolutePath() + "/" + originalFilename);

   2.  new File() 创建File对象时会拿掉最后一个斜杠！！
        new File("d:/2023/10/10/");
        file.getAbsolutePath()="d:/2023/10/10"
 * @create 2022-11-26-10:23
 */
public class CreateFile {
    public static void main(String[] args) {

        // 测试 在main方法中 进行文件的创建 会创建到哪个目录下呢？
        // 前面带斜杠
        String filePath = "/testMainFile";

        File file = new File(filePath);

        System.out.println("file= " + file);// file= \testMainFile
        //直接输出file 对象 默认调用 toString()
        //File 的 toString() 重写过了
        /*
        public String toString() {
        return getPath();
        }
        */

        if (file.exists()){
            if (file.delete()){
                System.out.println("main删除成功");
            }else {
                System.out.println("main删除失败");
            }
        }else {
            System.out.println("main该文件不存在");
            try {
                file.createNewFile();
                System.out.println("main文件绝对路径：" + file.getAbsolutePath());
                //文件绝对路径：D:\testFile
                System.out.println("main文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 前面不带斜杠
        String filePath2 = "testMainFile2";

        File file2 = new File(filePath2);

        if (file2.exists()){
            if (file2.delete()){
                System.out.println("main删除2成功");
            }else {
                System.out.println("main删除2失败");
            }
        }else {
            System.out.println("main该文件2不存在");
            try {
                file2.createNewFile();
                System.out.println("main文件2绝对路径：" + file2.getAbsolutePath());
                // 文件2绝对路径：D:\Java_developer_tools\javaweb\apache-tomcat-8.0.50-windows-x64\apache-tomcat-8.0.50\bin\txestFile2
                System.out.println("main文件2创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void test1(){
//        方式一：
        File file = new File("e:\\hello.txt");
        try {
            //createNewFile() 方法有boolean类型的返回值
            file.createNewFile();
            System.out.println("创建文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        方式二：
        File parentFile = new File("e:\\");
        File file1 = new File(parentFile, "news2.txt");

        try {
            file1.createNewFile();
            System.out.println("创建文件2成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file2 = new File("e:/", "news3.txt");
        try {
            file2.createNewFile();
            System.out.println("create news3");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
//
        // 这里只是创建了一个文件对象 不会生成文件 还需要调用生成文件的方法才会真正的生成文件！
        File file = new File("e:\\hello.txt");
        System.out.println("ddd");
    }
    @Test
    public void test3() {
//
        // 测试 如果按照如上的书写方式 进行文件的创建 会创建到哪个目录下呢？
        // 前面带斜杠
        String filePath = "/testFile";

        File file = new File(filePath);

        if (file.exists()){
            if (file.delete()){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("该文件不存在");
            try {
                file.createNewFile();
                System.out.println("文件绝对路径：" + file.getAbsolutePath());
                //文件绝对路径：D:\testFile
                System.out.println("文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 前面不带斜杠
        String filePath2 = "txestFile2";

        File file2 = new File(filePath2);

        if (file2.exists()){
            if (file2.delete()){
                System.out.println("删除2成功");
            }else {
                System.out.println("删除2失败");
            }
        }else {
            System.out.println("该文件2不存在");
            try {
                file2.createNewFile();
                System.out.println("文件2绝对路径：" + file2.getAbsolutePath());
                // 文件2绝对路径：D:\Java_developer_tools\javaweb\apache-tomcat-8.0.50-windows-x64\apache-tomcat-8.0.50\bin\txestFile2
                System.out.println("文件2创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
