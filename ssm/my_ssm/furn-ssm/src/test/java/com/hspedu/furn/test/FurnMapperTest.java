package com.hspedu.furn.test;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.dao.FurnMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

/**
 * @author yangda
 * @create 2023-11-08-20:50
 * @description: 测试使用 mybatisGenerator 逆向工程 自动生成的 FurnMapper 接口中的 方法
 */
public class FurnMapperTest {


    @Test
    public void insertSelective(){
        // 1. 首先获取 ioc 容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 可以使用 接口的方式 获取 furnMapper
        // 因为在applicationContext.xml 中配置了 扫描器 将所有的dao接口的实现加入到 ioc 容器中
        /*  <!-- 配置扫描器，将 mybatis 接口的实现加入到 ioc 容器中
            1. 我们的mapper接口放在com.hspedu.furn.dao
            2. mybatis就是处于DAO层，操作DB
            -->
            <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
                <!--
                1. 扫描所有的 dao 接口的实现，加入到 ioc 容器中
                2. 这里 dao 接口，就是 mapper 接口
                -->
                <property name="basePackage" value="com.hspedu.furn.dao"/>
            </bean>*/

        // 常规方式 先获取sqlSessionFactory -> sqlSession -> getMapper -> mapper.insert()
        // SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)ioc.getBean("sqlSessionFactory");
        // SqlSession sqlSession = sqlSessionFactory.openSession();
        // FurnMapper furnMapper = sqlSession.getMapper(FurnMapper.class);
        // System.out.println("使用常规方式获取到的 furnMapper1= " + furnMapper1);
        // System.out.println("使用常规方式获取到的 furnMapper1.getClass() 运行类型= " + furnMapper1.getClass());
        // System.out.println("------------------------------------");

        // 2.获取furnMapper
        // 使用 下面这种方式 获取furnMapper 会自动关闭 相关联的sqlSession对象 更方便
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        // System.out.println("使用spring配置文件 中配置扫描器 将所有的dao接口的实现加入到ioc容器中" +
        //         " ,直接从ioc容器中获取的方式 furnMapper= " + furnMapper);
        System.out.println("使用spring配置文件 中配置扫描器 将所有的dao接口的实现加入到ioc容器中" +
                " ,直接从ioc容器中获取的方式 furnMapper.getClass() 运行类型= " + furnMapper.getClass());
        // furnMapper.getClass() 运行类型= class com.sun.proxy.$Proxy17

        //如果这里 OK, 说明 spring 和 mybatis 整合 OK
        //System.out.println(furnMapper);

        Furn furn =
                new Furn(null, "北欧风格沙发!!~@", "顺平家居~@", new BigDecimal(180), 666, 27, "assets/images/product-image/1.jpg");

        int affected = furnMapper.insertSelective(furn);

        // Furn furn = new Furn();
        // furn.setName("ss沙发");
        // furn.setMaker("ttt");
        // furn.setPrice(new BigDecimal(333));
        // furn.setSales(3);
        // furn.setStock(100);
        // furn.setImgPath("/img.jpg");
        //furn没有设置id 使用的是insert() 逆向工程生成的insert() 默认设置所有字段
// ==>  Preparing: insert into furn (id, name, maker, price, sales, stock, img_path) values (?, ?, ?, ?, ?, ?, ?)
// ==> Parameters: null, ss沙发(String), ttt(String), 333(BigDecimal), 3(Integer), 100(Integer), /img.jpg(String)

        // int affected = furnMapper.insert(furn);


        // Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7ef27d7f]
        // 正在关闭非事务性SqlSession 因为 这里dao层没有在spring配置文件 配置事务管理
        System.out.println("affected= " + affected);

        System.out.println("添加 OK");

    }

    @Test
    public void deleteByPrimaryKey(){

        // 1. 首先获取 ioc 容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2.获取furnMapper
        // 使用 下面这种方式 获取furnMapper 会自动关闭 相关联的sqlSession对象 更方便
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);

        int affected = furnMapper.deleteByPrimaryKey(6);

        // Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7ef27d7f]
        System.out.println("affected= " + affected);

        System.out.println("删除 OK");

    }


    @Test
    public void updateByPrimaryKey(){

        // 1. 首先获取 ioc 容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2.获取furnMapper
        // 使用 下面这种方式 获取furnMapper 会自动关闭 相关联的sqlSession对象 更方便
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);

        Furn furn = new Furn();
        furn.setId(5);
        furn.setName("顺平家居！！-喜喜沙发");
        furn.setPrice(new BigDecimal(999));

        //会修改所有的字段，如果没有设置字段对应的属性值，那么默认会在sql语句中 set null 值
        // 如果创建的数据库表的字段有非空约束 就会报错
        // int affected = furnMapper.updateByPrimaryKey(furn);

        // 根据你设置属性对应的字段，有选择性/动态 的生成sql语句 这个更灵活
        int affected = furnMapper.updateByPrimaryKeySelective(furn);

        // Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7ef27d7f]
        System.out.println("affected= " + affected);

        System.out.println("修改 OK");

    }


    @Test
    public void selectByPrimaryKey(){

        // 1. 首先获取 ioc 容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2.获取furnMapper
        // 使用 下面这种方式 获取furnMapper 会自动关闭 相关联的sqlSession对象 更方便
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);


        // 根据你设置属性对应的字段，有选择性/动态 的生成sql语句 这个更灵活
        Furn furn = furnMapper.selectByPrimaryKey(1);
        System.out.println("furn= " + furn);

        System.out.println("查询 OK");

    }


}
