package com.hspedu.jdbc.myjdbc;

/**
 * @author yangda
 * @description:
 * @create 2023-04-13-22:21
 */
public class OracleJdbcImpl implements JdbcInterface {

    @Override
    public Object getConnection() {
        System.out.println("得到 oracle的连接 升级");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成 对oracle的增删改查");

    }

    @Override
    public void close() {

        System.out.println("关闭 oracle的连接");

    }
}
