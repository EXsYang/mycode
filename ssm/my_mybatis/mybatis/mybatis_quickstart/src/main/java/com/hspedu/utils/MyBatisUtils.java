package com.hspedu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangda
 * @create 2023-10-17-19:07
 * @description:
 */
public class MyBatisUtils {

    //属性 会话工厂 可以理解成是连接池 通过sqlSessionFactory
    // 获取到SqlSession(可以理解成是连接)
    private static SqlSessionFactory sqlSessionFactory;

    //编写静态代码块-初始化sqlSessionFactory
    static {
        try {
            //指定资源文件，配置文件mybatis-config.xml
            String resource = "mybatis-config.xml";
            //这里有多个Resources类 注意选择 org.apache.ibatis.io.Resources
            //说明：加载文件时，默认到resources目录=>运行后的工作目录target/classes
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            System.out.println("sqlSessionFactory= " + sqlSessionFactory);
            // sqlSessionFactory= org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@36b4cef0
            System.out.println("sqlSessionFactory.getClass()= " + sqlSessionFactory.getClass());
            // sqlSessionFactory.getClass()= class org.apache.ibatis.session.defaults.DefaultSqlSessionFactory

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //编写方法，返回SqlSession对象-会话
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
