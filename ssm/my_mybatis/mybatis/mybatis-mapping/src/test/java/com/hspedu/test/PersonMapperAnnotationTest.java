package com.hspedu.test;

import com.hspedu.entity.IdenCard;
import com.hspedu.entity.Person;
import com.hspedu.mapper.IdenCardMapperAnnotation;
import com.hspedu.mapper.PersonMapperAnnotation;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-02-17:00
 * @description:
 */
public class PersonMapperAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private PersonMapperAnnotation personMapperAnnotation;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        personMapperAnnotation = sqlSession.getMapper(PersonMapperAnnotation.class);
        System.out.println("idenCardMapperAnnotation.getClass()= " + personMapperAnnotation.getClass());
    }


    @Test
    public void getPersonById() {

        Person person = personMapperAnnotation.getPersonById(2);

        System.out.println("person 注解方式 = " + person);

        if (sqlSession != null){
            sqlSession.close();
        }

    }
}
