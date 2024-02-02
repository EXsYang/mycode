package com.hspedu.jdbc.myjdbc;

/**
 * @author yangda
 * @description:
 * @create 2023-04-13-22:17
 */
public class TestJDBC {
    public static void main(String[] args) {
        //完成对Mysql的操作
        JdbcInterface jdbcInterface = new MysqlJdbcImpl();
        jdbcInterface.getConnection();
        jdbcInterface.crud();
        jdbcInterface.close();

        //完成对oracle的操作
        System.out.println("================================");
        jdbcInterface = new OracleJdbcImpl();
        jdbcInterface.getConnection();
        jdbcInterface.crud();
        jdbcInterface.close();
    }
}
