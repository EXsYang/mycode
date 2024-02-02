package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.hspmybatis.config.Function;
import com.hspedu.hspmybatis.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-26-22:38
 * @description: 通过配置文件，获取数据库连接
 */
public class HspConfiguration {


    //类的加载器 不可以直接new 而是通过静态方法获取
    //private ClassLoader classLoader = new ClassLoader();


    //定义属性-类的加载器
    // ClassLoader是 java.lang 包下的类 可以直接拿来使用
    private ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    // 创建方法 读取yd_config.xml
    // 解析xml文件 返回连接Connection
    public Connection build(String resource) {

        try {
            //拿到yd_config.xml文件对应的输入流
            InputStream stream = classLoader.getResourceAsStream(resource);

            //构建SAXReader对象 解析xml文件
            SAXReader saxReader = new SAXReader();

            Document document = saxReader.read(stream);
            Element root = document.getRootElement();

            //写一个方法 解析root节点 返回连接对象
            Connection connection = evalDataSource(root);



            //System.out.println("读取到yd_config.xml文件的root根节点= " + root);

            // 返回连接
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    //写一个方法 解析root节点 返回连接
    public Connection evalDataSource(Element rootElement) {

        if ("database".equalsIgnoreCase(rootElement.getName())) {
            //说明是想要拿的根节点

            //和数据库连接 必要的属性
            //驱动Driver
            String driverClassName = null;
            //连接 url
            String url = null;
            String username = null;
            String password = null;


            //获取根节点中的所有property节点元素
            List list = rootElement.elements("property");
            for (Object p : list) {
                // 拿到了 每一个property节点
                Element property = (Element) p;
                //<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
                //获取 节点的属性的值
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");

                // 进行匹配
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
                        throw new RuntimeException("数据库连接配置信息 没有匹配上");

                }

            }

            try {
                // 循环结束 进行连接
                //注册驱动
                Class.forName(driverClassName);

                //得到连接
                Connection connection = DriverManager.getConnection(url, username, password);
                //返回连接
                return connection;



            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @SuppressWarnings("rawtypes")
    public MapperBean readMapper(String path){

        MapperBean mapperBean = new MapperBean();

        try {
            InputStream resourceAsStream = classLoader.getResourceAsStream(path);

            //使用dom4j 进行解析
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(resourceAsStream);
            // 得到根节点
            Element rootElement = document.getRootElement();
            // 解析根节点 完成封装
            if ("mapper".equals(rootElement.getName())){
                //说明是我们想要的节点
                //System.out.println("rootElement= " + rootElement);

                // 得到接口名 封装到 mapperBean 对象中
                // 从这里可以看出  mapperBean对象
                // 只是对mapper.xml文件 读取到的信息进行了封装
                // 当前 mapperBean对象 和MonsterMapper 接口还没有联系
                // 因为 mapperBean对象的 两个属性的值 都来自 mapper.xml 文件
                String nameSpace = rootElement.attributeValue("nameSpace").trim();
                //System.out.println("nameSpace= " + nameSpace);



                // 该根节点中可能会有很多配置的 sql 如下
                /*
                  <select id="getMonsterById" resultType="com.hspedu.entity.Monster">
                     select * from monster where id = ?
                  </select>
                  <select id="getMonsterById2" resultType="com.hspedu.entity.Monster">
                     select * from monster where id = ?
                  </select>
                */

                // 获取根元素的元素迭代器
                Iterator iterator = rootElement.elementIterator();

                // 创建一个List集合 用于保存 在mapper.xml 文件中读取到的
                // function 信息  因为可能存在多个 MonsterMapper 接口的配置实现方法

                List<Function> functions = new ArrayList<>();

                while (iterator.hasNext()){
                    Element element = (Element)iterator.next();

                    //创建一个Function对象 用于保存mapper.xml 文件中
                    //配置的实现方法的信息
                    Function function = new Function();

                    String sqlType = element.getName();
                    String id = element.attributeValue("id").trim();
                    String resultType = element.attributeValue("resultType").trim();
                    String sql = element.getText();

                    // 开始封装 将方法信息封装到function对象中
                    function.setSqlType(sqlType);
                    function.setFuncName(id);

                    // 这里function的属性   private Object resultType; //返回类型
                    // 是得到的 String resultType (是一个全类名) 反射生成的实例
                    // 保存的返回类型是一个实例对象 使用反射生成实例对象并保存到function对象中
                    Object resultTypeInstance = Class.forName(resultType).newInstance();
                    function.setResultType(resultTypeInstance);
                    function.setSql(sql);

                    functions.add(function);
                }

                // 到这里 已经读取完了 配置实现方法
                // while循环结束 设置mapperBean对象相关属性并返回
                mapperBean.setInterfaceName(nameSpace);
                // 将functions集合 封装到 MapperBean 对象
                mapperBean.setFunctions(functions);
                // mapperBean对象封装完毕

                // 返回mapperBean对象
                return mapperBean;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
