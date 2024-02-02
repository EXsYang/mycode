package com.hspedu.test;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import com.hspedu.mapper.PetMapperAnnotation;
import com.hspedu.mapper.UserMapper;
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
public class PetMapperAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private PetMapperAnnotation petMapperAnnotation;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        petMapperAnnotation = sqlSession.getMapper(PetMapperAnnotation.class);
        System.out.println("petMapperAnnotation.getClass()= " + petMapperAnnotation.getClass());
    }


    @Test
    public void getPetByUserId() {

        List<Pet> petByUserId = petMapperAnnotation.getPetByUserId(2);
        for (Pet pet : petByUserId) {
            System.out.println("宠物信息 pet.nickname= " + pet.getNickname() +
                    " pet.id= " + pet.getId() + " pet.user= " + pet.getUser() );
        }


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }
    @Test
    public void getPetById() {

        Pet petById = petMapperAnnotation.getPetById(2);

        System.out.println("宠物信息 pet.id= " + petById.getId() + " pet.nickname= " + petById.getNickname() + " " +
                "pet.User= " + petById.getUser());

        User user = petById.getUser();
        System.out.println("主人信息 -" + user.getId() + "-" + user.getName());


        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }
}
