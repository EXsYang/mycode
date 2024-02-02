package com.hspedu.hspspringmvc.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author yangda
 * @create 2023-09-30-23:07
 * @description: 用于解析spring配置文件
 */
public class XMLParser {


    public static String getBeanPackage(String xmlFile) {

        SAXReader saxReader = new SAXReader();

        //getClassLoader().getResourceAsStream() :
        // 返回用于读取指定资源的输入流。该方法"xmlFile"  前面不能加斜杠 /
        // 默认是从classpath 类加载路径下获取资源 maven项目下 放在src/main/resources下
        // 的文件 加载时默认放在类的加载路径target/classes下
        //Class.getResourceAsStream(String path) :
        // path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。
        //通过得到类的加载路径 获取到spring配置文件[对应的资源流]
        // 下面这行代码 不管运行环境在哪  都会去找类的加载路径
        // 在tomcat下找到类的加载路径 target/classes 并获取到要扫描的包
        InputStream inputStream = XMLParser.class.getClassLoader().getResourceAsStream(xmlFile);

        try {
            //得到xmlFile文档
            Document document = saxReader.read(inputStream);

            Element rootElement = document.getRootElement();

            Element componentScanElement = rootElement.element("component-scan");

            Attribute attribute = componentScanElement.attribute("base-package");
            String basePackage = attribute.getText();
            return basePackage;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "";
    }
}
