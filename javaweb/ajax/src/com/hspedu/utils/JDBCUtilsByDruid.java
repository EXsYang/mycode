package com.hspedu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 韩顺平
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            String path = JDBCUtilsByDruid.class.getResource("/").getPath();// 得到的是工作目录，而不是源码目录
            System.out.println("JDBCUtilsByDruid path= " + path);

            //properties.load(new FileInputStream("src\\druid.properties"));
            // 自己写的：下面使用的是模拟tomcat时 读取xml 的方法实现文件的加载的
            //properties.load(new FileInputStream(path + "druid.properties"));

            //老韩解读
            //1.目前我们是javaweb方式启动
            //2. 所以要获取src目录下的文件，需要使用类加载器
            properties.load(JDBCUtilsByDruid.class.getClassLoader()
                    .getResourceAsStream("druid.properties"));

            ds = DruidDataSourceFactory.createDataSource(properties);
            System.out.println("Druid连接池创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编写getConnection方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //关闭连接, 老师再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
