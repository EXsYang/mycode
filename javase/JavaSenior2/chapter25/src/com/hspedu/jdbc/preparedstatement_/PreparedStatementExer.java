package com.hspedu.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2023-04-27-22:46
 */
public class PreparedStatementExer {
    public static void main(String[] args) throws Exception {
        //通过配置文件得到连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        //获取相关信息
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //加载驱动/注册驱动
        Class.forName(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //组织sql
//        String sql = "create table admin11 (id int primary key auto_increment,name varchar(32) not null default '')";
//        String sql = "insert into admin11 values(null,?)";
//        String sql = "update admin11 set name = ? where id = ?";
//        String sql = "delete from admin11 where id = ? ";
        String sql = "select * from admin11";

        //获取preparedStatement 预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1,1);//? 必须指定
//        preparedStatement.setInt(2,1);
//        preparedStatement.setString(1, "tttt");
//        preparedStatement.executeUpdate();
//        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + "\t" + name);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();


    }


}
