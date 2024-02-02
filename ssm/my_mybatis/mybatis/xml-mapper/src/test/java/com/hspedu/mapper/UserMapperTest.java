package com.hspedu.mapper;

import com.hspedu.entity.User;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-29-22:50
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
        //获取到MonsterMapper对象 class com.sun.proxy.$Proxy7 是一个代理对象
        //,底层使用的动态代理机制，后面我们自己实现底层机制时会讲到
        userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("userMapper 运行类型= " + userMapper.getClass());
    }


    @Test
    public void addUser() {

        User user = new User();
        user.setUseremail("hsp@sohu.com");
        user.setUsername("hsp");

        userMapper.addUser(user);

        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("添加user成功");

    }


    @Test
    public void findAllUser() {

        //查询所有的user
        List<User> allUser = userMapper.findAllUser();
        for (User user : allUser) {
            // 因为这里 对象的属性名和表的字段名不一致！！
            // 同时 在UserMapper.xml 文件中 配置的sql语句 为 select * from `user`;
            // 查询回来的结果 是按照表字段名返回的 所以匹配不到 对应的setter/getter方法
            // 导致查询回来的对应不一致的 属性值为null
            System.out.println("user= " + user);
        }


        if (sqlSession != null){
            // sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("添加user成功");

    }
}
