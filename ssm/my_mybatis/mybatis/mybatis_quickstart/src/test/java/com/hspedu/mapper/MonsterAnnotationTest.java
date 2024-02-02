package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-29-16:11
 * @description:
 */
public class MonsterAnnotationTest {

    //属性
    private SqlSession sqlSession;
    //使用注解的方式 完成对数据库的操作
    // 该属性 monsterAnnotation 的操作方式 类比 MonsterMapper
    private MonsterAnnotation monsterAnnotation;

    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //sqlSession 返回的对象是 DefaultSqlSession
        System.out.println("sqlSession= " + sqlSession);
        // sqlSession= org.apache.ibatis.session.defaults.DefaultSqlSession@6f195bc3

        // 该属性 monsterAnnotation 的操作方式 类比 MonsterMapper
        // 返回的依然是接口的一个代理对象
        monsterAnnotation = sqlSession.getMapper(MonsterAnnotation.class);
        System.out.println("monsterAnnotation= " + monsterAnnotation);
        // monsterAnnotation= org.apache.ibatis.binding.MapperProxy@3234e239
        System.out.println("monsterAnnotation运行类型= " + monsterAnnotation.getClass());
        // monsterAnnotation运行类型= class com.sun.proxy.$Proxy11
    }

    @Test
    public void addMonster() {

        // 1. 如果是通过注解的方式，可不再使用 MonsterMapper.xml
        // 2. 但是需要在 mybatis-config.xml 注册/引入含注解的类/接口

        Monster monster = new Monster();
        monster.setAge(100);
        monster.setBirthday(new Date());
        monster.setEmail("666@qq.com");
        monster.setGender(1);
        monster.setName("狐狸精");
        monster.setSalary(1000);

        //使用在接口方法配置注解方式完成对DB的操作
        //addMonster() 方法没有返回值 所以 .var 没有反应
        /*底层走的依然是 sqlSession的insert方法
        @Override
        public int insert(String statement, Object parameter) {
            return update(statement, parameter);
        }*/
        monsterAnnotation.addMonster(monster);
        //添加后monster的 id 是
        System.out.println("添加后monster的 id(DB中自增长的) 是 = " + monster.getId());


        //如果是增删改，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("保存成功..");
    }


    @Test
    public void findAllMonster(){

        //使用接口配置注解的方式操作DB
        List<Monster> allMonster = monsterAnnotation.findAllMonster();
        for (Monster monster : allMonster) {
            System.out.println("monster== " + monster);
        }
        //如果是增删改，需要提交事务
        if (sqlSession != null) {
            // sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("查询所有结果成功..");
    }







}
