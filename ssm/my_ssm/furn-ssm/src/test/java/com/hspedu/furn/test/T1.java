package com.hspedu.furn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * @author yangda
 * @create 2023-11-08-16:14
 * @description:
 */
public class T1 {

    @Test
    public void t1(){

        //看看spring配置的Bean是否可以获取到

        // 拿到spring ioc容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 注意 德鲁伊数据源对象 实现的DataSource接口是 javax.sql.DataSource; 不要引错了
        DataSource dataSource = ioc.getBean(DataSource.class);

        Object pooledDataSource = ioc.getBean("pooledDataSource");
        Object sqlSessionFactory = ioc.getBean("sqlSessionFactory");
        System.out.println("dataSource= " + dataSource);
        System.out.println("dataSource.getClass()= " + dataSource.getClass());
        System.out.println("sqlSessionFactory= " + sqlSessionFactory);
        System.out.println("sqlSessionFactory.getClass()= " + sqlSessionFactory.getClass());
        System.out.println("pooledDataSource= " + pooledDataSource);
        System.out.println("ok");


    }
}
