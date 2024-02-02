package com.hspedu.test;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import com.hspedu.mapper.UserMapper;
import com.hspedu.mapper.UserMapperAnnotation;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-17:23
 * @description:
 */
public class UserMapperAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private UserMapperAnnotation userMapperAnnotation;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        userMapperAnnotation = sqlSession.getMapper(UserMapperAnnotation.class);
        System.out.println("userMapperAnnotation.getClass()= " + userMapperAnnotation.getClass());
    }


    @Test
    public void getUserById() {

        User user = userMapperAnnotation.getUserById(2);

        System.out.println("Annotation 主人信息= " + user);
        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println("宠物信息 pet.id= " + pet.getId() + " pet.nickname= " + pet.getNickname() + " " +
                    "pet.User= " + pet.getUser());
        }


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");

    }
}
