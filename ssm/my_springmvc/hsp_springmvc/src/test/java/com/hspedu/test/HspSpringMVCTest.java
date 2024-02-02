package com.hspedu.test;

import com.hspedu.hspspringmvc.context.HspWebApplicationContext;
import com.hspedu.hspspringmvc.xml.XMLParser;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

/**
 * @author yangda
 * @create 2023-10-01-14:40
 * @description:
 */
public class HspSpringMVCTest {

    @Test
    public void readXML(){

        String beanPackage = XMLParser.getBeanPackage("hspspringmvc.xml");
        System.out.println("beanPackage= " + beanPackage);
        //beanPackage= com.hspedu.controller
    }
    @Test
    public void readXML1(){

        String beanPackage = com.hspedu1.hspspringmvc1.xml.XMLParser.parser("hspspringmvc.xml");
        // 这里可以扫描到hspspringmvc1.xml文件 说明方法classLoader.getResourceAsStream(xmlFile);
        // 不管运行环境在哪里都会去找类的加载路径 /D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/test-classes/
        //
        System.out.println("beanPackage= " + beanPackage);
        //类加载路径path= /D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/test-classes/
        //beanPackage= com.hspedu1.controller
    }

    @Test
    public void scanPackage(){

        //HspWebApplicationContext hspWebApplicationContext = new HspWebApplicationContext();
        //hspWebApplicationContext.init();
        //classPath= null
        //要扫描的包的工作目录下的url= null

        //URL resource = this.getClass().getClassLoader().getResource("/");
        //System.out.println("resource= " + resource);
        //resource= null

        //URL classGetResource = this.getClass().getResource("/");
        //System.out.println("classGetResource= " + classGetResource);
        //// url.getPath() / url.getFile() 可以去除前缀file:
        //String classGetResourcePath = classGetResource.getPath();
        //System.out.println("classGetResourcePath= " + classGetResourcePath);
        //String classGetResourceFile = classGetResource.getFile();
        //System.out.println("classGetResourceFile= " + classGetResourceFile);

        //classGetResource=
//file:/D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/test-classes/
    }


    @Test
    public void testGetResource(){

        // 首先通过类的加载器拿到要扫描的包 在工作目录下的真实的绝对路径
        URL classPath = this.getClass().getClassLoader().
                getResource("");
        System.out.println("classPath= " + classPath);
        //classPath= file:/D:/Java_developer_tools/ssm/my_springmvc/hsp_springmvc/target/test-classes/

        URL classPath1 = this.getClass().getClassLoader().
                getResource("/");
        System.out.println("classPath1= " + classPath1);
        //classPath1= null 这里拿到的是null 但是在tomcat环境下 拿到的是类的加载路径！！

    }
    @Test
    public void testGetResourceAsStream(){

        // 首先通过类的加载器拿到要扫描的包 在工作目录下的真实的绝对路径
        // getClassLoader().getResourceAsStream() 默认是在类的加载路径下获取资源[文件输入流]
        InputStream resourceAsStream = this.getClass().getClassLoader().
                getResourceAsStream(""); // 瞎写一个文件名 拿回来是null
        //classLoader.getResourceAsStream() 不能以斜杠开头 拿到的是null
        InputStream resourceAsStream1 = this.getClass().getClassLoader().
                getResourceAsStream("/"); // 不能以斜杠开头 拿到的是null 在此基础上瞎写一个文件名 "/x" 拿回来是null
        // 下面这种形式 默认是从该类HspSpringMVCTest.class的同文件夹下获取资源
        InputStream resourceAsStream2 = this.getClass().
                getResourceAsStream(""); // 瞎写一个文件名 拿回来是null
        // 下面这种形式 默认是从该类的加载路径classpath下获取资源
        InputStream resourceAsStream3 = this.getClass().
                getResourceAsStream("/");// 瞎写一个文件名 拿回来是null
        System.out.println("resourceAsStream= " + resourceAsStream);
        System.out.println("resourceAsStream1= " + resourceAsStream1);
        System.out.println("resourceAsStream2= " + resourceAsStream2);
        System.out.println("resourceAsStream3= " + resourceAsStream3);

 /*       // 首先通过类的加载器拿到要扫描的包 在工作目录下的真实的绝对路径
        // getClassLoader().getResourceAsStream() 默认是在类的加载路径下获取资源[文件输入流]
        InputStream resourceAsStream = this.getClass().getClassLoader().
                getResourceAsStream("hspspringmvc1.xml"); // 瞎写一个文件名 拿回来是null
        InputStream resourceAsStream1 = this.getClass().getClassLoader().
                getResourceAsStream("/"); // 不能以斜杠开头 拿到的是null 在此基础上瞎写一个文件名 "/x" 拿回来是null
        // 下面这种形式 默认是从该类HspSpringMVCTest.class的同文件夹下获取资源
        InputStream resourceAsStream2 = this.getClass().
                getResourceAsStream("hspspringmvc.xml"); // 瞎写一个文件名 拿回来是null
        // 下面这种形式 默认是从该类的加载路径classpath下获取资源
        InputStream resourceAsStream3 = this.getClass().
                getResourceAsStream("/hspspringmvc1.xml");// 瞎写一个文件名 拿回来是null
        System.out.println("resourceAsStream= " + resourceAsStream);
        System.out.println("resourceAsStream1= " + resourceAsStream1);
        System.out.println("resourceAsStream2= " + resourceAsStream2);
        System.out.println("resourceAsStream3= " + resourceAsStream3);*/



    }






}
