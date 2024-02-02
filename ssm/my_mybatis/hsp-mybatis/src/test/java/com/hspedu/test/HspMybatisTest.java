package com.hspedu.test;

import com.hspedu.entity.Monster;
import com.hspedu.hspmybatis.config.MapperBean;
import com.hspedu.hspmybatis.sqlsession.HspConfiguration;
import com.hspedu.hspmybatis.sqlsession.HspExecutor;
import com.hspedu.hspmybatis.sqlsession.HspSessionFactory;
import com.hspedu.hspmybatis.sqlsession.HspSqlSession;
import com.hspedu.mapper.MonsterMapper;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author yangda
 * @create 2023-10-19-16:06
 * @description:
 */
public class HspMybatisTest {

    @Test
    public void build(){

        HspConfiguration hspConfiguration = new HspConfiguration();
        Connection connection =
                hspConfiguration.build("hsp-mybatis.xml");
        System.out.println("connection= " + connection);

    }


    @Test
    public void query(){


        HspExecutor hspExecutor = new HspExecutor();
        String sql = "select * from `monster` where `id`=?";
        //这里传进去的是一个字符串的1 但是对应的数据库表使用的是INT类型
        // 数据库中查询也能成功 但不建议
        String parameter = "1";
        //int parameter = 1;
        Object monster = hspExecutor.query(sql, parameter);
        Monster monster1 = (Monster) monster;
        System.out.println("monster1= " + monster1);

    }

    @Test
    public void selectOne(){

        HspSqlSession hspSqlSession = new HspSqlSession();
        String sql = "select * from monster where id=?";
        Integer parameter = 1;

        Monster monster = hspSqlSession.selectOne(sql, parameter);

        System.out.println("selectOne monster= " + monster);

    }


    @Test
    public void readMapper(){

        HspConfiguration hspConfiguration = new HspConfiguration();
        MapperBean mapperBean = hspConfiguration.readMapper("MonsterMapper.xml");

        System.out.println("mapperBean= " + mapperBean);

    }

    @Test
    public void getMapper(){

        HspSqlSession hspSqlSession = new HspSqlSession();
        MonsterMapper mapper = hspSqlSession.getMapper(MonsterMapper.class);

        //这里返回的mapper应该是一个代理对象
        System.out.println("mapper==" + mapper.getClass());
        //mapper==class com.sun.proxy.$Proxy4

        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster= " + monster);

    }


    @Test
    public void openSession(){

        HspSqlSession hspSqlSession = HspSessionFactory.openSession();

        // 这里 alt+enter 自动补全的返回 MonsterMapper 类型 因为
        //public <T> T getMapper(Class<T> clazz){} 返回代理对象
        // Class<T> 这里传入时 指定了T的类型！！！
        //
        MonsterMapper mapper = hspSqlSession.getMapper(MonsterMapper.class);
        // 测试 确实是 在getMapper(Class<T> clazz) 传入时 指定了T的类型！！！
        // 返回类型也按照 T 类型进行返回的
        //Goods goods = hspSqlSession.getMapper(Goods.class);


        // 这里自动补全的就是Monster类型 是因为在接口中声明的返回类型就是Monster
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster====== " + monster);


    }



}
