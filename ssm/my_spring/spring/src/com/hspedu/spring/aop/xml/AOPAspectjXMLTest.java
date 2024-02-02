package com.hspedu.spring.aop.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-08-17:40
 */
public class AOPAspectjXMLTest {

    @Test
    public void testAOPByXML(){

        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans09.xml");

        SmartAnimalable smartAnimalable = ioc.getBean(SmartAnimalable.class);

        //smartAnimalable.getSub(10,2); // 配置的是切入到getSum()! getSub() 没配置

        smartAnimalable.getSum(10,3);

    }



}
