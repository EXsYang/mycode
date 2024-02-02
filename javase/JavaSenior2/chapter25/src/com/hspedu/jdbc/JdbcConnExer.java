package com.hspedu.jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2023-04-26-20:39
 */
public class JdbcConnExer {
    @Test
    public void exer() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
//        String sql = "create table news (id int primary key auto_increment,news varchar(32) not null default '')";
//        statement.execute(sql);
        int nub = 1;

        for (int i = 0; i < 5; i++) {
            nub = nub++;//这样写，相当于韩顺平那道面试题
            System.out.println("nub++:" + nub);
        }


        for (int i = 1; i < 6; i++) {
//            String sql = "insert into news values(null,'这是第"+nub+++"条新闻')"; //正常输出nub++后的数字
            String sql = "insert into news values(null,'这是第"+nub+++"条新闻')"; //正常输出nub++后的数字
            statement.executeUpdate(sql);
        }
//        statement.executeQuery(sql);


    }
}
