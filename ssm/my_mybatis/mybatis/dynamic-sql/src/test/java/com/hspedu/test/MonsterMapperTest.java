package com.hspedu.test;

import com.hspedu.entity.Monster;
import com.hspedu.mapper.MonsterMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-30-21:54
 * @description:
 */
public class MonsterMapperTest {
    //属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        //获取到MonsterMapper对象 class com.sun.proxy.$Proxy7 是一个代理对象
        //,底层使用的动态代理机制，后面我们自己实现底层机制时会讲到
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("代理对象monsterMapper 运行类型= " + monsterMapper.getClass());
    }


    @Test
    public void findMonsterByAge() {

        // List<Monster> monsters = monsterMapper.findMonsterByAge(-1);
        List<Monster> monsters = monsterMapper.findMonsterByAge(10);

        for (Monster monster : monsters) {
            System.out.println("monster= " + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }

    @Test
    public void findMonsterByIdAndName() {

        Monster monster = new Monster();
        // monster.setId(6);
        monster.setId(-1);
        monster.setName("老鼠精");

        List<Monster> monsterList = monsterMapper.findMonsterByIdAndName(monster);
        for (Monster m : monsterList) {
            System.out.println("m= " + m);
        }


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }


    @Test
    public void findMonsterByIdAndName_choose() {

        HashMap<String, Object> map = new HashMap<>();
        // map.put("name","老鼠精");
        // map.put("id",6);

        List<Monster> monsterList = monsterMapper.findMonsterByIdAndName_choose(map);
        for (Monster m : monsterList) {
            System.out.println("m= " + m);
        }


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }


    @Test
    public void findMonsterById_forEach() {

        HashMap<String, Object> map = new HashMap<>();
        // map.put("ids", Arrays.asList(6,7,8));

        List<Monster> monsterList = monsterMapper.findMonsterById_forEach(map);
        for (Monster m : monsterList) {
            System.out.println("m= " + m);
        }


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }


    @Test
    public void findMonsterByName_Trim() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "老鼠精");
        map.put("age", 6);

        List<Monster> monsterList = monsterMapper.findMonsterByName_Trim(map);
        for (Monster m : monsterList) {
            System.out.println("m= " + m);
        }


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }


    @Test
    public void updateMonster_set() {

        HashMap<String, Object> map = new HashMap<>();

        map.put("id",12);
        map.put("age", 767);

        // 类型不匹配 下面这个会失败 不行 需要传一个String类型
        // map.put("birthday", new Date());

        map.put("birthday", "2000-03-23");
        map.put("email", "sha1@qq.com");
        map.put("gender", 0);
        map.put("name", "鲨鱼精1");
        map.put("salary", 77771);

        monsterMapper.updateMonster_set(map);


        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("ok");
    }


}
