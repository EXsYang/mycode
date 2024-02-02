package com.hspedu.mhl.utils;

import com.alibaba.druid.pool.PoolableWrapper;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author yangda
 * @description:
 * @create 2023-04-29-0:53
 */
public class Test01 {
    public static void main(String[] args) throws SQLException {
        //
//        System.out.println("请输入一个整数：");
//        int i = Utility.readInt();
//        System.out.println("i = " + i);

//        public String toString() {
//            return getClass().getName() + "@" + Integer.toHexString(hashCode());
//        }

// package java.sql;
// public class DruidPooledConnection extends PoolableWrapper implements PooledConnection, Connection {

       Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection.toString());//com.mysql.jdbc.JDBC4Connection@1ae369b7
        System.out.println(connection.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
        System.out.println(connection.getClass().getName());//class com.alibaba.druid.pool.DruidPooledConnection

        String s = UUID.randomUUID().toString();
        System.out.println(s);
    }
}
