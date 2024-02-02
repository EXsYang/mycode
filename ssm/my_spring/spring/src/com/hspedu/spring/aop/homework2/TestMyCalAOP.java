package com.hspedu.spring.aop.homework2;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-09-22:18
 */
public class TestMyCalAOP {

    @Test
    public void testAOPByAnnotation(){
        //MyCal myCal = new MyCal();
        //
        //System.out.println("连续相加到n= " + myCal.cal1(5));
        //System.out.println("连续相乘到n= " + myCal.cal2(5));


        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans10.xml");

        Cal cal = ioc.getBean(Cal.class);

        cal.cal1(4);

        System.out.println("===========");
        cal.cal2(4);


    }
}
