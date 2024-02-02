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
 * @create 2023-10-29-13:16
 * @description: 测试直接使用 SqlSession 原生API 对数据库进行操作
 */
public class MyBatisNativeAPITest {

    private SqlSession sqlSession;


    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //sqlSession 返回的对象是 DefaultSqlSession
        System.out.println("sqlSession= " + sqlSession);
    }

    @Test
    public void t1() {

        Monster monster =
                sqlSession.selectOne("com.hspedu.mapper.MonsterMapper.getMonsterById");

        System.out.println("t1...");
        System.out.println("monster= " + monster);
        //如果是查询，不需要提交事务 但是关闭连接释放资源还是必要的
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    //使用 sqlSession 原生的 API 调用我们编写的方法[了解]
    //最终还是会去读取MonsterMapper.xml 文件中写的sql语句
    @Test
    public void myBatisNativeCrud() {

        /**
         *   @Override
         *   public int insert(String statement, Object parameter) {
         *     return update(statement, parameter);
         *   }
         *
         *   statement: 就是 Mapper(这里是MonsterMapper接口)接口方法的完整声明
         *              例如："com.hspedu.mapper.MonsterMapper.addMonster"
         *   parameter: 入参
         *
         */

        //=============add start==================
        //Monster monster = new Monster();
        //monster.setAge(200);
        //monster.setBirthday(new Date());
        //monster.setEmail("hspedu100@sohu.com");
        //monster.setGender(2);
        //monster.setName("白骨精x");
        //monster.setSalary(9234.89);

        //
        //在选择原生API 时 有一个带参数的 一个不带参数的 取决于 Mapper接口中定义的方法有没有参数
        //如 MonsterMapper 接口中 添加monster的方法 [public void addMonster(Monster monster);]
        // 上面[] 中的接口方法带了参数 就选择： insert(String statement, Object parameter)
        // 而不选择 sqlSession.insert(String statement)
        //


        //int insert = sqlSession.insert("com.hspedu.mapper.MonsterMapper.addMonster", monster);
        //System.out.println("insert= " + insert);
        //System.out.println("使用原生API插入monster成功！");
        //=============add end===================


        //=============delete start==================
        //int delete = sqlSession.delete("com.hspedu.mapper.MonsterMapper.delMonster", 5);
        //System.out.println("delete= " + delete);
        //System.out.println("使用原生API删除monster成功！");
        //=============delete end===================


        //=============update start==================
        //Monster monster = new Monster();
        //monster.setAge(200);
        //monster.setBirthday(new Date());
        //monster.setEmail("hspedu100@sohu.com");
        //monster.setGender(2);
        //monster.setName("白骨精x");
        //monster.setSalary(9234.89);
        //monster.setId(9); // 这个id一定要设置！ 否则就不知道要修改哪个对象了!

        //int update = sqlSession.update("com.hspedu.mapper.MonsterMapper.updateMonster", monster);
        //System.out.println("update= " + update);
        //System.out.println("使用原生API修改monster成功！");
        //=============update end===================

        //=============select start==================
        //Monster monster1 = sqlSession.selectOne("com.hspedu.mapper.MonsterMapper.getMonsterById", 6);
        //System.out.println("monster1= " + monster1);
        //System.out.println("使用原生API查询一个monster成功！");

        //=============select end===================


        //=============select all start==================
        List<Monster> monsters = sqlSession.selectList("com.hspedu.mapper.MonsterMapper.findAllMonster");
        for (Monster monster1 : monsters) {
            System.out.println("monster = " + monster1);
        }
        System.out.println("使用原生API查询所有monster成功！");

        //=============select all end===================



        //如果是增删改，需要提交事务
        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
