package com.hsp.io;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author yangda
 * @description:  File file = new File("E:\\demo02");//文件目录，也是特殊的文件
 * new File() 创建File对象时会拿掉最后一个斜杠！！
 * @create 2022-11-26-11:08
 */
public class Directory_ {
    public static void main(String[] args) {
    //mkdir创建一级目录，mkdirs创建多级目录，delete删除空目录或文件
    //java.io.File.mkdir()：只能创建一级目录，且父目录必须存在，否则无法成功创建一个目录。
    //java.io.File.mkdirs()：可以创建多级目录，父目录不一定存在。
    }

    @Test
    public void test1(){
        //判断 d:\\news1.txt 是否存在，如果存在就删除

        File file = new File("E:\\news1.txt");

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
                System.out.println("文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test2(){
        //判断 e:\\demo02 是否存在，如果存在就删除,否则提示不存在
        File file = new File("E:\\demo02");//文件目录，也是特殊的文件

        if (file.exists()){
            if (file.delete()){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("该文件不存在");
            //file.mkdir();//创建一级目录 创建的是目录文件 即目录
            try {
                file.createNewFile();//没有后缀名也可以创建成功 创建的是文件 即文件 文件类型为.
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("目录创建成功");
        }
    }


    @Test
    public void test3(){
        //判断 e:\\demo\\a\\b\\c 是否存在，如果存在就删除,否则提示不存在
        File file = new File("E:\\demo\\a\\b\\c");//文件目录，也是特殊的文件
//        File file1 = new File("E:\\demo\\a\\b\\c\\c.txt");//文件目录，也是特殊的文件
//        try {
//            file1.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (file.exists()){//判断文件是否存在
            if (file.delete()){//删除空目录或文件,目录里有文件时，删除失败，没有文件时，删除最后一层目录(文件夹)
                //只要最后一层文件目录是空的，就可以删除成功
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("该文件不存在");
            file.mkdirs();//创建多级目录、返回一个布尔值，可以放在if(file.mkdirs)
//                file.createNewFile();//没有后缀名也可以创建成功
            System.out.println("目录创建成功");
        }
    }




}
