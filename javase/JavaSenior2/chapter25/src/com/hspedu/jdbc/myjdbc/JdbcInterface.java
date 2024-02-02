package com.hspedu.jdbc.myjdbc;

/**
 * @author yangda
 * @description:  我们规定的jdbc接口（方法）
 * @create 2023-04-13-22:07
 */
public interface JdbcInterface {

    //连接
    public Object getConnection();
    //crud
    public void crud();
    //关闭连接
    public void close();



}
