package com.hsp.properties_;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-11:02
 */
public class Properties02 {
    public static void main(String[] args) throws IOException {
        //使用Properties类 来读取 mysql.properties 配置文件

//      1.创建Properties 对象
        Properties properties = new Properties();
//      2.加载指定配置文件
//        properties.load(new FileReader("D:\\Java_developer_tools\\JavaSenior2\\day8\\src\\mysql.properties"));
        properties.load(new FileReader("D:\\Java_developer_tools\\javase\\JavaSenior2\\day8\\src\\mysql.properties"));
//      3.把 k-v 显示到控制台
        properties.list(System.out);//这里直接标准输出到显示器(控制台)
        System.out.println("*******************");
//      4.根据 key 获取对应的值
        System.out.println("user=" + properties.getProperty("user"));
        System.out.println("pwd=" + properties.getProperty("pwd"));


    }
}
