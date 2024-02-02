package com.hspedu.test;

import com.hspedu.entity.IdenCard;
import com.hspedu.mapper.IdenCardMapper;
import com.hspedu.mapper.IdenCardMapperAnnotation;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-02-16:42
 * @description:
 */
public class IdenCardMapperAnnotationTest {
    //属性
    private SqlSession sqlSession;
    private IdenCardMapperAnnotation idenCardMapperAnnotation;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        idenCardMapperAnnotation = sqlSession.getMapper(IdenCardMapperAnnotation.class);
        System.out.println("idenCardMapperAnnotation.getClass()= " + idenCardMapperAnnotation.getClass());
    }


    @Test
    public void getIdenCardById() {

        IdenCard idenCard = idenCardMapperAnnotation.getIdenCardById(1);

        System.out.println("idenCard 注解方式 = " + idenCard);

        if (sqlSession != null){
            sqlSession.close();
        }

    }

}
