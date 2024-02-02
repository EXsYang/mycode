package com.hspedu1.hspspringmvc1.xml;

import com.hspedu.entity.Monster;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author yangda
 * @create 2023-10-06-13:18
 * @description: 该类为工具类 用于解析hspspringmvc1.xml文件
 */
public class XMLParser {

    // 根据传进来的文件 进行解析

    /**
     *
     * @param xmlFile xml文件名 hspspringmvc1.xml
     * @return 返回要扫描的包的全路径 没有返回""
     */
    public static String parser(String xmlFile){

        SAXReader saxReader = new SAXReader();
        //getClassLoader().getResourceAsStream() :
        // 返回用于读取指定资源的输入流。该方法"xmlFile"  前面不能加斜杠 / ！！ 否则返回null
        // 默认是从classpath 类加载路径下获取资源 运行环境变化 对应的获取资源的类加载路径也会变化
        //Class.getResourceAsStream(String path) :
        // path 不以’/'开头时，则默认是从此类所在的包下取资源，
        // 如果以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
        // path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。
        //通过得到类的加载路径 获取到spring配置文件[对应的资源流]
        //注意:
        //关于getResourceAsStream()方法的加载资源路径 和使用的类的加载路径有关
        // 使用 Monster.class.getClassLoader().getResourceAsStream() 和
        // XMLParser.class.getClassLoader().getResourceAsStream()
        // 加载资源的路径都是同一个 即target/classes
        // 使用类的加载器获取资源 要注意 使用的类所在的位置的类的加载路径在哪 默认是从classPath路径获取资源
        // 运行路径和该方法类的加载路径不同 如在test路径下运行 得到的类的加载路径 和这里通过
        // XMLParser.class.getClassLoader().getResource("") 获取到的类的真实的运行路径和
        // 默认的加载资源的路径不是同一个路径



        // 使用类的加载器 得到xml 文件相关的输入流
        // classLoader.getResourceAsStream() 返回的是一个 和文件相关的输入流
        //InputStream resourceAsStream =
        //        XMLParser.class.getClassLoader().getResourceAsStream(xmlFile);
        InputStream resourceAsStream =
                XMLParser.class.getClassLoader().getResourceAsStream(xmlFile);
        //try {
        //    String path = XMLParser.class.getClassLoader().getResource("").getPath();
        //    System.out.println("这里获得的是类的工作路径path!!= " + path);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}


        //URL resource = XMLParser.class.getResource("/");
        //String resourcePath = XMLParser.class.getResource("/").getPath();
        //URL resource2 = XMLParser.class.getClassLoader().getResource("");
        //String resourcePath2 = XMLParser.class.getClassLoader().getResource("").getFile();


        //System.out.println("真实的运行目录 工作目录是resource= " + resource);
        //System.out.println("resourcePath= " + resourcePath);
        //System.out.println("真实的运行目录 工作目录是resource2= " + resource2);
        //System.out.println("resourcePath2= " + resourcePath2);


        // 需要传入一个输入流 InputStream
        try {
            // 得到hspspringmvc1.xml文件的文档对象
            Document document = saxReader.read(resourceAsStream);
            // 获取根元素
            Element rootElement = document.getRootElement();
            // 通过根节点获取 子元素
            Element element = rootElement.element("component-scan");
            // 获取元素的base-package属性
            Attribute attribute = element.attribute("base-package");
            //String attributeValue = element.attributeValue("base-package");
            // 获取属性的值 方式一：
            String pack = attribute.getText();
            System.out.println("通过 attribute.getText() 获取到的属性的值pack= " + pack);
            //通过 attribute.getText() 获取到的属性的值pack= com.hspedu1.controller,com.hspedu1.service
            // 获取属性的值 方式二：
            // 下面这种方式也可以获取到属性的值 是一样的
            //String pack2 = element.attributeValue("base-package");
            //System.out.println("通过 element.attributeValue(\"base-package\");" +
            //        " 获取到的属性的值pack2= " + pack2);
            //通过 element.attributeValue("base-package"); 获取到的属性的值pack2= com.hspedu1.controller,com.hspedu1.service

            return pack;

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果没有扫描到 返回""
        return "";

    }

}
