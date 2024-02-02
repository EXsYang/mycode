package com.hspedu.test;

import com.hspedu.homework.entity.Husband;
import com.hspedu.homework.mapper.HusbandMapper;
import com.hspedu.homework.mapper.WifeMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-02-21:08
 * @description:
 */
public class HusbandMapperTest {
    //属性
    private SqlSession sqlSession;
    private HusbandMapper husbandMapper;

    //编写方法完成初始化


    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession= " + sqlSession);
        husbandMapper = sqlSession.getMapper(HusbandMapper.class);
        System.out.println("husbandMapper.getClass()= " + husbandMapper.getClass());
    }


    @Test
    public void getHusbandMapperById(){

        Husband husband = husbandMapper.getHusbandById(1);

        System.out.println("husband====" + husband);

        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok");
    }
    @Test
    public void getHusbandMapperById2(){

        Husband husband2 = husbandMapper.getHusbandById2(2);

        System.out.println("husband2====@@@" + husband2);

        if (sqlSession != null){
            sqlSession.close();
        }


        System.out.println("ok");
    }

}
