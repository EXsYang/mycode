package com.hspedu.test;

import com.hspedu.entity.Goods;
import com.hspedu.entity.Monster;
import com.hspedu.hspmybatis.config.MapperBean;
import com.hspedu.hspmybatis.sqlsession.HspConfiguration;
import com.hspedu.hspmybatis.sqlsession.HspExecutor;
import com.hspedu.hspmybatis.sqlsession.HspSessionFactory;
import com.hspedu.hspmybatis.sqlsession.HspSqlSession;
import com.hspedu.mapper.MonsterMapper;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author yangda
 * @create 2023-10-26-22:56
 * @description:
 */
public class MybatisTest {

    private HspConfiguration hspConfiguration = null;

    private HspSqlSession hspSqlSession = null;

    //private Connection connection = null;

    //
    /**
     * 1. 当方法标注 @Before, 表示在执行你的目标测试方法前，会先执行该方法
          注意 @Before 是junit的注解  import org.junit.Before;
     */
    //@Before
    //public void init(){
    //    hspConfiguration = new HspConfiguration();
    //    //connection = hspConfiguration.build("yd_config.xml");
    //    hspSqlSession = new HspSqlSession();
    //}


    @Test
    public void build(){

        HspConfiguration hspConfiguration = new HspConfiguration();
        Connection connection = hspConfiguration.build("yd_config.xml");

        System.out.println("connection= " + connection);
    }

    @Test
    public void lombok(){

        Monster monster = new Monster();

        System.out.println("monster= " + monster);
    }

    @Test
    public void testHspExecutor_query(){

        HspExecutor hspExecutor = new HspExecutor();

        Monster monster = hspExecutor.query("select * from monster where id = ?", 1);

        System.out.println("monster~= " + monster);
    }

    @Test
    public void selectOne(){
        HspSqlSession hspSqlSession = new HspSqlSession();

        Monster monster = hspSqlSession.selectOne("select * from monster where id = ?", 1);

        System.out.println("monster!!! = " + monster);
    }


    @Test
    public void readMapper(){

        MapperBean mapperBean = hspConfiguration.readMapper("MonsterMapper.xml");


        System.out.println("mapperBean= " + mapperBean);

    }


    @Test
    public void getMapper(){

        HspSqlSession hspSqlSession = new HspSqlSession();
        MonsterMapper mapper = hspSqlSession.getMapper(MonsterMapper.class);
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
        System.out.println("monster= " + monster);


    }


}
