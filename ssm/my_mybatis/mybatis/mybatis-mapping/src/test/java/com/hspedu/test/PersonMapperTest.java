package com.hspedu.test;

import com.hspedu.entity.Person;
import com.hspedu.mapper.PersonMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-01-22:00
 * @description:
 */
public class PersonMapperTest {

    //属性
    private SqlSession sqlSession;
    private PersonMapper personMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        personMapper = sqlSession.getMapper(PersonMapper.class);
        System.out.println("personMapper.getClass()= " + personMapper.getClass());
    }


    @Test
    public void getPersonById() {

        Person person = personMapper.getPersonById(1);

        System.out.println("person= " + person);

        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok~~");


    }
    @Test
    public void getPersonById2() {

        Person person = personMapper.getPersonById2(2);

        System.out.println("person-----= " + person);

        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok~~");


    }
    @Test
    public void getPersonById3() {

        Person person3 = personMapper.getPersonById3(2);

        System.out.println("person3-----= " + person3);
        // person3-----= Person{id=2, name='张三2', card=null}
        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok~~");


    }


    @Test
    public void getPersonByCardId() {

        Person personByCardId = personMapper.getPersonByCardId(2);

        System.out.println("personByCardId-----= " + personByCardId);


        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok~~");


    }
}
