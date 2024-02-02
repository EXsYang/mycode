package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.hspmybatis.config.Function;
import com.hspedu.hspmybatis.config.MapperBean;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-19-15:44
 * @description: 读取xml文件，建立连接
 */
public class HspConfiguration {

    //类的加载器 不可以直接new 而是通过静态方法获取
    //private ClassLoader classLoader = new ClassLoader();

    //定义属性-类的加载器
    // ClassLoader是 java.lang 包下的类 可以直接拿来使用
    private ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    //创建方法 解析xml文件 返回连接Connection
    public Connection build(String resource) {
        Connection connection = null;
        try {
            //加载配置hsp_mybatis.xml 获取到对应的InputStream
            //通过类的加载器 拿到xml文件的输入流
            //resources 下的文件 运行后 会放到类的加载路径 target/classes 目录下
            InputStream stream = classLoader.getResourceAsStream(resource);

            //使用dom4j技术 解析xml文件
            SAXReader saxReader = new SAXReader();
            //返回一个文档对象
            Document document = saxReader.read(stream);
            //获取到hsp_mybatis.xml 的根元素 <database>
            Element rootElement = document.getRootElement();
            System.out.println("rootElement= " + rootElement);
            //专门编写一个方法 解析根元素 返回Connection连接

            connection = evalDataSource(rootElement);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果evalDataSource()没有拿到连接 返回null
        return connection;
    } 

    //方法会解析hsp_mybatis.xml 返回Connection连接 import java.sql.Connection;
    //eval:评估/解析
    private Connection evalDataSource(Element rootElement) {

        if (!"database".equals(rootElement.getName())) {
            throw new RuntimeException("root 节点应该是<database>");
        }

        //连接DB的必要参数
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;

        //获取到指定名字 "property" 的元素的集合elements
        List elements = rootElement.elements("property");

        //遍历rootElement下的子节点 获取属性值
        for (Object item : elements) {
            //element 对应property节点
            Element element = (Element) item;
            //第一种获取element 属性值的方方式
            //拿到当前property节点的name属性值
            String name = element.attributeValue("name");
            //拿到当前property节点的value属性值
            String value = element.attributeValue("value");

            //判断是否得到name和value
            if (name == null || value == null){
                throw new RuntimeException("name或者value没有设置值");
            }

            switch (name) {
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("hsp-mybatis.xml 文件 hsp-mybatis.xml property-name瞎写了一个值,属性名没有匹配到");
            }

            //第二种获取element 属性值的方方式
            //Attribute name =
            //        element1.attribute("name");
            //name.getText();

        }


        Connection connection = null;
        try {
            // 获取Connection连接
            //1. 注册驱动
            Class.forName(driverClassName);//建议写上

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection; //返回connection
    }


    //读取XxxMapper.xml , 能够创建MapperBean对象
    //path 就是xml的路径+文件名，这里使用类的加载器读取 默认从类的加载路径classpath下进行读取的
    // 或者说是从类的加载路径计算的 ,如果是放在resources下面 直接传一个文件名即可，
    // 因为resources目录下的资源文件，运行后会自动地放在target/classes/ 目录下
    //@SuppressWarnings("rawtypes")
    public MapperBean readMapper(String path){

        MapperBean mapperBean = new MapperBean();

        try {
            //获取到xml文件对应的InputStream
            InputStream stream = classLoader.getResourceAsStream(path);

            SAXReader saxReader = new SAXReader();

            //拿到 mapper.xml 文件的文件对象
            Document document = saxReader.read(stream);
            //获取mapper.xml根节点
            /* root 就是下面这个<mapper>节点
            <mapper namespace="com.hspedu.mapper.MonsterMapper">
              <select id="getMonsterById" resultType="com.hspedu.entity.Monster">
                select * from `monster` where `id` = ?
              </select>
            </mapper>
           */
            Element root = document.getRootElement();
            //System.out.println("MonsterMapper.xml 的root根节点" + root.getName() +"= "  + root);

            // 解析根节点中的子节点 封装到Function对象中

            // 拿到根节点上的属性 namespace 就是 Mapper接口的全路径 全类名
            String interfaceName = root.attributeValue("namespace").trim();
            System.out.println("interfaceName= " + interfaceName);

            // 获取根节点的元素迭代器 用于遍历 获取到子节点/子元素-生成function对象
            Iterator rootIterator = root.elementIterator();

            // 创建一个List集合 保存所有的方法信息 存放生成的function对象
            List<Function> functions = new ArrayList<>();
            //遍历它的子节点/子元素-生成function对象
            while (rootIterator.hasNext()){

                // 每遍历一次就创建一个Function对象 用于保存Mapper.xml 文件中配置的实现方法的信息
                Function function = new Function();

                // 取出一个子元素 - dom4j 包下的Element
                /* 取出的子节点就是下面这个<select>节点
                <select id="getMonsterById" resultType="com.hspedu.entity.Monster">
                        select * from `monster` where `id` = ?
                </select>
                */
                Element e = (Element) rootIterator.next();
                String sqlType = e.getName();
                String id = e.attributeValue("id").trim();
                String resultType = e.attributeValue("resultType").trim();
                //String parameterType = e.attributeValue("parameterType").trim();
                String sql = e.getText();

                // 开始封装 将方法信息封装到function对象中
                function.setSql(sql);
                function.setFuncName(id);
                function.setSqlType(sqlType);

                // 这里function的属性   private Object resultType; //返回类型
                // 是得到的 String resultType (是一个全类名) 反射生成的实例
                // 保存的返回类型是一个实例对象 使用反射生成实例对象并保存到function对象中
                Object resultTypeInstance = Class.forName(resultType).newInstance();
                function.setResultType(resultTypeInstance);

                // 将生成的function对象 放入到List集合中
                functions.add(function);
            }

            // while循环结束 设置mapperBean对象相关属性并返回
            mapperBean.setInterfaceName(interfaceName);
            mapperBean.setFunctions(functions);

            // 返回mapperBean
            return mapperBean;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
