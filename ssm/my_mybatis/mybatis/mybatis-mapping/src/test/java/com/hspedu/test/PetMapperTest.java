package com.hspedu.test;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import com.hspedu.mapper.PersonMapper;
import com.hspedu.mapper.PetMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-15:07
 * @description:
 */
public class PetMapperTest {

    //属性
    private SqlSession sqlSession;
    private PetMapper petMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        petMapper = sqlSession.getMapper(PetMapper.class);
        System.out.println("petMapper.getClass()= " + petMapper.getClass());
    }


    @Test
    public void getPetByUserId() {

        List<Pet> pets = petMapper.getPetByUserId(1);
        for (Pet pet : pets) {
            System.out.println("pet.name= " + pet.getNickname());
            User user = pet.getUser();
            System.out.println("user.name= " + user.getName());
            System.out.println("=============================");
        }

        if (sqlSession != null){
            sqlSession.close();
        }

        System.out.println("ok");
    }
    @Test
    public void getPetById() {

        // Pet pet = new Pet(3, "fufu",
        //         new User(33, "33娘", Arrays.asList(new Pet(), new Pet())));
        // System.out.println(pet);
        // 经过测试发现：
        // ArrayList 直接输出 也会调用集合中元素的toString 方法

        Pet pet = petMapper.getPetById(1);
        System.out.println("pet.nickname= " + pet.getNickname());

        if (sqlSession != null){
            sqlSession.close();
        }

        System.out.println("ok");
    }

}
