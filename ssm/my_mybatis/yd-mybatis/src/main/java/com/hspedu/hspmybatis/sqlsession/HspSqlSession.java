package com.hspedu.hspmybatis.sqlsession;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yangda
 * @create 2023-10-27-19:17
 * @description:
 * HspSqlSession: 搭建Configuration(连接) 和Executor之间的桥梁
 * 这里有操作DB的方法
 */
public class HspSqlSession {

    private Executor executor = new HspExecutor();

    private HspConfiguration hspConfiguration = new HspConfiguration();

    //编写方法SelectOne 返回一条记录-对象[做了简化]
    //在原生的Mybatis中 statement 不是sql ，而是 要执行的接口方法
    //这里做了简化
    public <T> T selectOne(String statement,Object parameter){
        return executor.query(statement,parameter);
    }


    //编写方法 返回代理对象 Class<T> 这里传入时 指定了T的类型！！！
    public <T> T getMapper(Class<T> clazz){

        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new HspMapperProxy(hspConfiguration,this,clazz));
    }


}
