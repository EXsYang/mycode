package com.hspedu.test;

import com.hspedu.entity.IdenCard;
import com.hspedu.mapper.IdenCardMapper;
import com.hspedu.mapper.PersonMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-01-22:31
 * @description:
 */
public class IdenCardMapperTest {
    //属性
    private SqlSession sqlSession;
    private IdenCardMapper idenCardMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        idenCardMapper = sqlSession.getMapper(IdenCardMapper.class);
        System.out.println("idenCardMapper.getClass()= " + idenCardMapper.getClass());
    }


    @Test
    public void getIdenCardById(){

        IdenCard idenCard = idenCardMapper.getIdenCardById(2);

        System.out.println("idenCard= " + idenCard);

        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok");
    }
    @Test
    public void getIdenCardById2(){

        IdenCard idenCard2 = idenCardMapper.getIdenCardById2(5);

        System.out.println("idenCard2= " + idenCard2);

        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok");
    }
}
