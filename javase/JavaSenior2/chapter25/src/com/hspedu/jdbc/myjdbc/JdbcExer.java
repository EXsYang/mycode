package com.hspedu.jdbc.myjdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2023-04-17-18:26
 */
public class JdbcExer {
    //创建actor表，使用jdbc 添加五条数据
    //修改id=1的记录，将name改成自己的名字
    //删除id=3的记录


    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //读取配置文件
        //FileInputStream("String pathName")
        // 在main方法中,此种方式读取文件位置默认为Project目录下
        // 在@Test方法中,此种方式读取文件位置默认为Module目录下
        //FileInputStream is = new FileInputStream("JDBC\\src\\Resource\\druid.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver); //注册驱动

//    for (int i = 0; i < 5; i++) {
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //执行sql
//    String sql = "create table actor02 (id int primary key auto_increment,`name` varchar(32) not null default '',sex char(1) not null default '女',borndate datetime,phone varchar(13))";
//        String sql = "alter table actor01 auto_increment = 2;";
//        String sql = "insert into actor01 values(null , '刘德华', '男', '1970-11-11', '110')";
//    String sql = "delete from actor01;";
        String sql = "delete from actor01 where id = 1;";
//    String sql = "insert into actor01 values(null, '刘德华', '男', '1970-11-11', '110')";
//    String sql = "update actor01 set `name` = '流露' where id = 1 ";

        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);// 如果是 dml语句，返回的就是影响行数     rows 返回1，sql数据添加成功，返回0数据添加失败
        //executeUpdate()对于createtable 或 droptable 删除表 等不操作行的语句，返回值总为0
        //executeUpdate() 里面放的是dml语句 增删改

//        ResultSet resultSet = statement.executeQuery(sql); //不是dml语句，即select查询语句，select是dql语句


        System.out.println(rows > 0 ? "成功" : "失败");

        //4.关闭连接资源
        statement.close();
        connection.close();
//    }


    }

    @Test
    public void test01() throws IOException, ClassNotFoundException, SQLException {
        //读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver); //注册驱动

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //执行sql
//    String sql = "create table actor02 (id int primary key auto_increment,`name` varchar(32) not null default '',sex char(1) not null default '女',borndate datetime,phone varchar(13))";
        String sql = "insert into actor01 values(null, '刘德华', '男', '1970-11-11', '110')";

        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);// 如果是 dml语句，返回的就是影响行数     rows 返回1，sql数据添加成功，返回0数据添加失败
        //executeUpdate()对于createtable 或 droptable 删除表 等不操作行的语句，返回值总为0


        System.out.println(rows > 0 ? "成功" : "失败");

        //4.关闭连接资源
        statement.close();
        connection.close();


    }


}
