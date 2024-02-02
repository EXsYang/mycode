package com.hspedu.jdbc.myjdbc;

/**
 * @author yangda
 * @description: mysql 数据库实现了jdbc接口[模拟] 【mysql厂商开发】
 * @create 2023-04-13-22:12
 */
public class MysqlJdbcImpl implements JdbcInterface {
    @Override
    public Object getConnection() {
        System.out.println("得到 mysql 的连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成 mysql 的增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭 mysql 的连接");
    }
}
