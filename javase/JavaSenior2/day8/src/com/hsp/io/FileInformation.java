package com.hsp.io;

// import org.junit.Test; //JUnit 4
import org.junit.jupiter.api.Test; //JUnit 5

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * @author yangda
 * @description: 文件信息类
 * @create 2022-11-26-10:40
 */
public class FileInformation {
    public static void main(String[] args) {
        // 1.得到类的加载器 使用任何一个类的.class 都可以得到类的加载器
        ClassLoader classLoader = FileInformation.class.getClassLoader();

        // 2.通过类的加载器获取到要扫描的包的资源 url
        // classLoader.getResource("com/hspedu/spring/component"); 默认是按照斜杠 / 来间隔各级文件目录的

        /*
         * 下面返回为null 在这里 即不可以写绝对路径 也不可写/   在手写hsptomcat 时 写的是
         * String path = HspTomcatV3.class.getResource("/").getPath();// 得到的是工作目录，而不是源码目录
         * System.out.println("path= " + path);
         * 这里的区别是
         * 手写tomcat时 是用 class对象.getResource() 返回的是也是URL对象
         * 类加载器.getResource() 返回的是URL对象
         * 但是用法不同 加载器中的 不可以写 "/" 和 绝对路径
         * */
        URL resource = classLoader.getResource("/"); // null
        URL resource2 = classLoader.getResource("day8/com/hsp/io"); // null
        URL resource3 = classLoader.getResource("/com/hsp/io"); // null
        URL resource4 = classLoader.getResource("com/hsp/io"); //
        URL resource5 = classLoader.getResource("com/hsp/io/FileInformation.java"); // null
        URL resource6 = classLoader.getResource("com/hsp/io/FileInformation.class"); //
        URL resource7 = classLoader.getResource("day8/com/hsp/io/FileInformation.class"); // null
        System.out.println("resource= " + resource);// null
        System.out.println("resource2= " + resource2);// null
        System.out.println("resource3= " + resource3);// null
        System.out.println("resource4= " + resource4);// resource4= file:/D:/Java_developer_tools/javase/JavaSenior2/out/production/day8/com/hsp/io
        System.out.println("resource5= " + resource5);// resource5= null
        System.out.println("resource6= " + resource6);// resource6= file:/D:/Java_developer_tools/javase/JavaSenior2/out/production/day8/com/hsp/io/FileInformation.class
        System.out.println("resource7= " + resource7);// resource7= null
        /*
        * 结论：默认情况下 是把 out module 的根目录 当作默认的类加载路径 即out/production/day8/
        * */

        //String resourcePath = classLoader.getResource("/").getPath(); // NullPointerException


        System.out.println("=========================");

        URL classResource = FileReader.class.getResource("/"); // classResource= file:/D:/Java_developer_tools/javase/JavaSenior2/out/production/day8/
        URL classResource2 = FileReader.class.getResource("src/com/hsp/io/FileInformation"); // classResource2= null
        // 绝对路径 放进去 得不到 返回null 下面这两种方式的绝对路径 都不行
        URL classResource3 = FileReader.class.getResource("file:/D:\\Java_developer_tools\\javase\\JavaSenior2\\day8\\src\\com\\hsp\\io"); // classResource3= null
        //URL classResource3 = FileReader.class.getResource("D:\\Java_developer_tools\\javase\\JavaSenior2\\day8\\src\\com\\hsp\\io"); // classResource3= null

        URL classResource4 = FileReader.class.getResource("/com/hsp/io/FileInformation.java"); // classResource5= null
        URL classResource5 = FileReader.class.getResource("/com/hsp/io/FileInformation.class"); // 这个格式正确！！ classResource4= file:/D:/Java_developer_tools/javase/JavaSenior2/out/production/day8/com/hsp/io/FileInformation.class
        URL classResource6 = FileReader.class.getResource("com/hsp/io/FileInformation.class"); // 这个格式不对 需要使用/ 开头 解析成项目真实的运行路径
        URL classResource7 = FileReader.class.getResource("day8/com/hsp/io/FileInformation.class"); // classResource6= null
        System.out.println("classResource= " + classResource); // file:/D:/Java_developer_tools/javase/JavaSenior2/out/production/day8/
        System.out.println("classResource2= " + classResource2); // null
        System.out.println("classResource3= " + classResource3); // null
        System.out.println("classResource4= " + classResource4); // null
        System.out.println("classResource5= " + classResource5); // file:/D:/Java_developer_tools/javase/JavaSenior2/out/production/day8/com/hsp/io/FileInformation.class
        System.out.println("classResource6= " + classResource6); // classResource6= null
        System.out.println("classResource7= " + classResource7); // classResource7= null
        /*
         * 结论：默认情况下 是把 out module 的根目录 当作默认的类加载路径 即out/production/day8/
         * 但是Class.getResource() 可以只放一个斜杠 / class.getResource("/") 来获取项目当前的真实的
         * 运行路径 根路径
         * 而classLoader.getResource("/") 不可以放入一个斜杠 会返回一个Null
         * classLoader.getResource("/") 这种方式只可以写相对路径 路径前不可以写斜杠 而是从
         * 运行路径下直接写 即 classLoader.getResource("com/hsp/io/FileInformation.class")
         *
         * 而 Class.getResource() 需要在路径前提供一个斜杠才能解析到
         *  class.getResource("/com/hsp/io/FileInformation.class")
         * 不可以写相对路径前面不加斜杠
         *
         * */

    }
    @Test
    public void test1(){
        //先创建文件对象
        File file = new File("e:\\news1.txt");

        //调用相应的方法，得到对应的信息
        System.out.println("文件名：" + file.getName()); // 文件名：news1.txt
        System.out.println("文件绝对路径：" + file.getAbsolutePath());
        System.out.println("文件父级目录：" + file.getParent());
        System.out.println("文件大小(字节)：" + file.length());
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("文件是否可以删除成功: " + file.delete());
        System.out.println("是否为文件：" + file.isFile());
        System.out.println("是否是一个目录:" + file.isDirectory());//文件夹


        // 下面类似的方式 在hsptomcat 中也写了 还有jiaju_mall项目中也有少量应用

        /*
         * 下面返回为null 在这里 即不可以写绝对路径 也不可写/   在手写hsptomcat 时 写的是
         * String path = HspTomcatV3.class.getResource("/").getPath();// 得到的是工作目录，而不是源码目录
         * System.out.println("path= " + path);
         * 这里的区别是
         * 手写tomcat时 是用 class对象..getResource() 返回的是String字符串对象
         * 而这里使用的是 类加载器.getResource() 返回的是URL对象
         *
         * jiaju_mall 项目中的使用方式是类的加载器.getResourceAsStream()
         *  //因为我们是web项目，他的工作目录在out, 文件的加载，需要使用类加载器
            //找到我们的工作目录
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
         * */

        // 1.得到类的加载器 使用任何一个类的.class 都可以得到类的加载器
        ClassLoader classLoader = FileInformation.class.getClassLoader();

        // 2.通过类的加载器获取到要扫描的包的资源 url
        // classLoader.getResource("com/hspedu/spring/component"); 默认是按照斜杠 / 来间隔各级文件目录的
        URL resource = classLoader.getResource("/"); // null
        // String resourcePath = classLoader.getResource("/").getPath(); //该环境下不可以使用斜杠开头
        String resourcePath = classLoader.getResource("").getPath();
        System.out.println("resource= " + resource);
        System.out.println("resourcePath= " + resourcePath);

        //File file2 = new File(resource.getPath());
        //        //if (file2.isDirectory()){
        //        //    File[] files = file2.listFiles();
        //        //    for (File f : files) {
        //        //        System.out.println("============");
        //        //        System.out.println(f.getAbsolutePath());
        //        //    }
        //        //}
    }

    @Test
    public void t2(){

        // 如果这里这样访问=> new File("mbg.xml");
        // 总结: 在maven生成的项目的test环境下的junit测试环境下
        // 使用 new File("mbg2.xml"); 不带斜杠 默认读取的路径是在项目下
        // 但是如果是在普通的Java项目 module 下的普通的junit单元测试环境下
        // 读取文件的路径是在module下

        File file2 = new File("mbg2.xml");

        // 如果这里这样访问=> new File("mbg.xml");
        // 在这里读取 需要将 mbg.xml 文件直接放在module下
        File file = new File("mbg.xml");


        if (file.exists()){
            if (file.delete()){
                System.out.println("test删除成功");
            }else {
                System.out.println("test删除失败");
            }
        }else {
            System.out.println("test该文件不存在");
            try {
                file.createNewFile();
                System.out.println("test文件绝对路径：" + file.getAbsolutePath());
                //文件绝对路径：D:\testFile
                System.out.println("test文件创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//调用相应的方法，得到对应的信息
        System.out.println("文件名：" + file.getName()); // 文件名：news1.txt
        System.out.println("文件绝对路径：" + file.getAbsolutePath());
        System.out.println("文件父级目录：" + file.getParent());
        System.out.println("文件大小(字节)：" + file.length());
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("是否为文件：" + file.isFile());
        System.out.println("是否是一个目录:" + file.isDirectory());//文件夹


    }
}
