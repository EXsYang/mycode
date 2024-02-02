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
 * @create 2023-10-17-19:40
 * @description:
 */
public class MonsterMapperTest {

    //属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    //编写方法完成初始化

    /**
     * 1. 当方法标注 @Before, 表示在执行你的目标测试方法前，会先执行该方法
     * 即执行目标测试方法前都要先执行init方法
     * 2. 执行测试方法前都会先去拿到 sqlSession 和 monsterMapper
     * 3. 这样写的好处 提高代码复用性 不用在每个测试方法中再写一遍了
     */
    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //获取到MonsterMapper对象 class com.sun.proxy.$Proxy7 是一个代理对象
        //,底层使用的动态代理机制，后面我们自己实现底层机制时会讲到
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("monsterMapper= " + monsterMapper.getClass());
    }


    @Test
    public void t1() {
        System.out.println("t1()...");
    }

    @Test
    public void t2() {

        System.out.println("t2()...");
    }


    @Test
    public void addMonster() {

        for (int i = 0; i < 2; i++) {
            Monster monster = new Monster();
            monster.setAge(100 + i);
            monster.setBirthday(new Date());
            monster.setEmail("2323311@qq.com");
            monster.setGender(1);
            monster.setName("大象精-" + i);
            monster.setSalary(1000 + i * 100);
            monsterMapper.addMonster(monster);
            System.out.println("添加对象==" + monster);
            // 给MonsterMapper.xml mapper-insert 配置属性
            // useGeneratedKeys="true"
            // keyProperty="id"
            // 可以获取到自增长的主键id 自增长后的值 返回来 在Java程序中通过monster.getId()拿到
            System.out.println("添加到表中后，自增长的id=" + monster.getId());
            System.out.println("添加到表中后，自增长的name=" + monster.getName());

        }

        //如果是增删改，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("添加成功..");
    }

    @Test
    public void delMonster() {
        // 问题 这里重复删除不会报错？？！

        monsterMapper.delMonster(4);
        //如果是增删改，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }



        System.out.println("删除成功..");

    }

    @Test
    public void updateMonster() {

        Monster monster = new Monster();
        monster.setAge(100 + 5);
        monster.setBirthday(new Date());
        monster.setEmail("2323311@qq.com");
        monster.setGender(1);
        monster.setName("小狗精2-" + 5);
        monster.setSalary(1000 + 5 * 100);
        monster.setId(5);

        monsterMapper.updateMonster(monster);

        //如果是增删改，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("修改成功..");

    }

    @Test
    public void getMonsterById() {

        Monster monster = monsterMapper.getMonsterById(5);
        System.out.println("monster= " + monster);
        //如果是查询，不需要提交事务 但是关闭连接释放资源还是必要的
        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("查询一条记录成功..");

    }

    @Test
    public void findAllMonster() {

        List<Monster> allMonster = monsterMapper.findAllMonster();
        //System.out.println("返回的Monster集合= " + allMonster);

        for (Monster monster : allMonster) {
            System.out.println("monster-" +monster);
        }

        //如果是查询，不需要提交事务 但是关闭连接释放资源还是必要的
        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("查询所有记录成功");

    }

}
