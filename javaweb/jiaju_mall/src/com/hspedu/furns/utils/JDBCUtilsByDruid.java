package com.hspedu.furns.utils;

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

    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();
    //public static ThreadLocal<Object> threadLocal1 = new ThreadLocal<>();
    private static DataSource ds;

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            //properties.load() 这里传进去一个InputStream或者Reader类型
            //properties.load(new FileInputStream("src\\druid.properties"));

            //因为我们是web项目，他的工作目录在out, 文件的加载，需要使用类加载器
            //找到我们的工作目录
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));

            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编写getConnection方法
    //public static Connection getConnection() throws SQLException {
    //    return ds.getConnection();
    //}

    //改写getConnection方法 使同一次请求 用的是一个连接对象 事务管理
    //public static Connection getConnection() throws SQLException {
    public static Connection getConnection(){
        // 使用优化后的getConnection() 方法 会导致 到 数据库模糊 查询 出问题

        //return ds.getConnection();
        Connection connection = threadLocalConn.get();
        if (connection == null){//说明当前的threadLocalConn没有连接
            //就从数据库连接池中,取出一个连接放入, threadLocalConn
            try {
                connection = ds.getConnection();
                // 从数据库连接池中拿到连接后 立马将自动提交设置为false
                //将连接设置为手动提交, 即不要自动提交
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            threadLocalConn.set(connection);

        }

        return connection;

    }

    /**
     * 提交事务, java基础 mysql事务+线程+过滤器机制+ThreadLocal
     */
    public static void commit(){

        Connection connection = threadLocalConn.get();
        if (connection != null){//确保该连接是有效
            //connection = threadLocalConn.get();
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally{
                try {
                    connection.close();//关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //老师说明
        //1. 当提交后，需要把connection从 threadLocalConn 清除掉
        //2. 不然，会造成 threadLocalConn 长时间持有该连接, 会影响效率
        //3. 也因为我们Tomcat底层使用的是线程池技术
        threadLocalConn.remove();

    }

    /**
     * 老师说明:  所谓回滚，是回滚/撤销和 connection管理的操作删掉，修改，添加
     */
    public static void rollback(){
        Connection connection = threadLocalConn.get();
        if (connection != null){//保证当前的连接是有效
            //connection = threadLocalConn.get();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally{
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        //老师说明
        //1. 当提交后，需要把connection从 threadLocalConn 清除掉
        //2. 不然，会造成 threadLocalConn 长时间持有该连接, 会影响效率
        //3. 也因为我们Tomcat底层使用的是线程池技术
        threadLocalConn.remove();
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
