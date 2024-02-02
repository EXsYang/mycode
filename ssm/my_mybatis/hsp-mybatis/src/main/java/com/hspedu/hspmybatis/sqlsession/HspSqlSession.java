package com.hspedu.hspmybatis.sqlsession;

import java.lang.reflect.Proxy;

/**
 * @author yangda
 * @create 2023-10-19-23:06
 * @description:
 * HspSqlSession: 搭建Configuration(连接) 和Executor之间的桥梁
 * 这里有操作DB的方法
 *
 */

public class HspSqlSession {

    //属性
    //执行器
    private Executor executor = new HspExecutor();
    //配置
    private HspConfiguration hspConfiguration =
            new HspConfiguration();

    //如果要写的更完善 在这里还要写 selectList - update - delete - insert ...



    //编写方法SelectOne 返回一条记录-对象[做了简化]
    //在原生的Mybatis中 statement 不是sql ，而是 要执行的接口方法
    //这里做了简化
    public <T> T selectOne(String statement,Object parameter){
        return executor.query(statement,parameter);
    }

    //返回mapper的动态代理对象，这里clazz 到时传入的是 MonsterMapper.class
    // 即传入的是MonsterMapper接口的clazz 对象
    // 想要返回该接口的代理对象
    /**
     * Proxy.newProxyInstance() 是按照接口类型返回代理对象的 即按照传入的=> new Class[]{clazz}
     * 返回 MonsterMapper.class 接口类型的代理对象
     *
     * 1. 返回mapper的动态代理对象
     * 2. 这里的clazz 到时传入的是MonsterMapper.class
     * 3. 返回的就是MonsterMapper接口代理对象
     * 4. 当执行接口方法时(通过代理对象执行)，根据动态代理机制，会执行到invoke方法
     *
     * @param clazz
     * @param <T>
     * @return
     */
    //编写方法 返回代理对象 Class<T> 这里传入时 指定了T的类型！！！
    public <T> T getMapper(Class<T> clazz){

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},
                new HspMapperProxy(hspConfiguration,this,clazz));
    }

}
