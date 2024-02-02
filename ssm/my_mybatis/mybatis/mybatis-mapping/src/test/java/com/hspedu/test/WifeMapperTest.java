package com.hspedu.test;

import com.hspedu.homework.entity.Wife;
import com.hspedu.homework.mapper.WifeMapper;
import com.hspedu.mapper.IdenCardMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-02-21:08
 * @description:
 */
public class WifeMapperTest {
    //属性
    private SqlSession sqlSession;
    private WifeMapper wifeMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        wifeMapper = sqlSession.getMapper(WifeMapper.class);
        System.out.println("wifeMapper.getClass()= " + wifeMapper.getClass());
    }


    @Test
    public void getWifeById(){

        Wife wife = wifeMapper.getWifeById(1);

        System.out.println("wife====" + wife);

        if (sqlSession != null){
            sqlSession.close();
        }

        System.out.println("ok");
    }

}
