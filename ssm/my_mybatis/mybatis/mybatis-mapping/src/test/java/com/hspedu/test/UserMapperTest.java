package com.hspedu.test;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import com.hspedu.mapper.PetMapper;
import com.hspedu.mapper.UserMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-15:16
 * @description:
 */
public class UserMapperTest {
    //属性
    private SqlSession sqlSession;
    private UserMapper userMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("userMapper.getClass()= " + userMapper.getClass());
    }


    @Test
    public void getUserById() {

        // 经过测试发现：
        // 1. ArrayList 直接输出 也会调用集合中元素的toString 方法
        // 2. 直接输出一个对象 默认会调用该对象的toString方法

        // 这里说明即使两个方法互相调用，只要不栈溢出 就没事，可以一直往下取

        User user = userMapper.getUserById(1);
        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println("pet= " + pet);
            User user1 = pet.getUser();
            System.out.println("user1= " + user1);
            List<Pet> pets1 = user1.getPets();
            for (Pet pet1 : pets1) {
                User user2 = pet1.getUser();
                System.out.println("user2= " + user2);
            }
        }

        System.out.println("user= " + user);

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("ok");
    }

}





