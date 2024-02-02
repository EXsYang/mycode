package com.hspedu.test;

import com.hspedu.entity.Hero;
import com.hspedu.mapper.HeroMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.print.attribute.standard.NumberUp;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author yangda
 * @create 2023-10-31-17:16
 * @description:
 */
public class HeroMapperTest {

    private SqlSession sqlSession;
    private HeroMapper heroMapper;
    @Before
    public void init(){
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession.getClass()= " + sqlSession.getClass());
        heroMapper = sqlSession.getMapper(HeroMapper.class);
        System.out.println("heroMapper.getClass()= " + heroMapper.getClass());
    }


    @Test
    public void addHero(){
        Hero hero = new Hero();
        // id 是自增长的 不用设置
        // hero.setId(1);

        hero.setNickname("矮之巨人");
        hero.setRank(1);
        hero.setSkill("反手立体机动装置2");
        hero.setSalary(200000.0);
        // hero.setDate(new Date());

        // 如果日期在javaBean中使用的是String类型，表中使用的Date类型
        // 设置时需要按照指定格式填写 否则报错
        hero.setDate("2002-03-12");
        heroMapper.addHero(hero);

        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功");
    }


    @Test
    public void findHeroByRank(){
        // 测试1
        // List<Hero> heroes = heroMapper.findHeroByRank(2);

        // 测试2
        // Hero hero1 = new Hero();
        // hero1.setRank(2);
        // List<Hero> heroes = heroMapper.findHeroByRank(hero1);

        // 测试3
        Map<String, Object> map = new HashMap<>();
        map.put("rank",2);
        List<Hero> heroes = heroMapper.findHeroByRank(map);

        for (Hero hero : heroes) {
            System.out.println("hero= " + hero);
        }


        if (sqlSession != null){
            // sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功");
    }


    @Test
    public void findHeroByRanks_foreach(){

        Map<String, Object> map = new HashMap<>();
        map.put("ranks", Arrays.asList(3,6,8));
        // map.put("ranks", Arrays.asList(1,2,3));
        List<Hero> heroes = heroMapper.findHeroByRanks_foreach(map);

        for (Hero hero : heroes) {
            System.out.println("hero= " + hero);
        }


        if (sqlSession != null){
            // sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功");
    }
    @Test
    public void updateHero_set(){

        Hero hero = new Hero();

        hero.setNickname("小光头埼玉1");
        hero.setSkill("普通一拳");
        hero.setId(1);

        heroMapper.updateHero_set(hero);


        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功");
    }


    @Test
    public void findHeroById(){

        HashMap<String, Object> map = new HashMap<>();
        // map.put("id",2);

        List<Hero> heroes = heroMapper.findHeroById(map);

        for (Hero hero : heroes) {
            System.out.println("hero= " + hero);
        }


        if (sqlSession != null){
            // sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功");
    }


    @Test
    public void findHeroByIdOrName_choose(){

        HashMap<String, Object> map = new HashMap<>();
        // map.put("id",2);
        // map.put("name","光头披风侠");
        map.put("nickname","光头披风侠");


        List<Hero> heroes = heroMapper.findHeroByIdOrName_choose(map);

        for (Hero hero : heroes) {
            System.out.println("hero= " + hero);
        }


        if (sqlSession != null){
            // sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("操作成功");
    }

}
