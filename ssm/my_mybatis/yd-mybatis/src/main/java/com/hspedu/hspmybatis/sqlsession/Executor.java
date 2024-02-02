package com.hspedu.hspmybatis.sqlsession;

/**
 * @author yangda
 * @create 2023-10-27-13:08
 * @description:
 */
public interface Executor {

    public <T> T query(String statement, Object parameter);
}
