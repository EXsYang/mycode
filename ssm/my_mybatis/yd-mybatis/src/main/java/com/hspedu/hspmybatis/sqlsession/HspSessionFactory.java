package com.hspedu.hspmybatis.sqlsession;

/**
 * @author yangda
 * @create 2023-10-27-22:46
 * @description:
 */
public class HspSessionFactory {

    public static HspSqlSession openSession(){
        return new HspSqlSession();
    }

}
