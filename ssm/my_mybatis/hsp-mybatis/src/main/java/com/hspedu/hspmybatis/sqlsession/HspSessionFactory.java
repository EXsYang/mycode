package com.hspedu.hspmybatis.sqlsession;

/**
 * @author yangda
 * @create 2023-10-26-14:38
 * @description:
 */
public class HspSessionFactory {

    public static HspSqlSession openSession(){
        return new HspSqlSession();
    }
}
