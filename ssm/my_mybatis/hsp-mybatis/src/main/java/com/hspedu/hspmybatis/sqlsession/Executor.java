package com.hspedu.hspmybatis.sqlsession;

/**
 * @author yangda
 * @create 2023-10-19-20:01
 * @description:
 */
public interface Executor {

    //泛型方法
    public <T> T query(String statement,Object parameter);
}
