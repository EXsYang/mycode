package com.atguigu.Date;

import org.junit.Test;

import java.util.Date;

/**
 * @author yangda
 * @description:掌握两个构造器，两个方法
 * @create 2022-11-10-9:08
 */
public class DateTest {
    @Test
    public void testDate(){
        //两个构造器，两个方法
        Date date = new Date();//空参构造器
        System.out.println(date);//Fri Nov 11 06:53:26 CST 2022
        System.out.println("toString(): " + date.toString());//和直接打印效果一样    Fri Nov 11 06:53:26 CST 2022
        long time = date.getTime();//1668120601909
        System.out.println(time);//1668120601909
        Date date1 = new Date(322354553L);//带参构造器
        long time1 = date1.getTime();
        System.out.println(time1);
        System.out.println("date1:" + date1);


        java.sql.Date date2 = new java.sql.Date(435545345);
        Date date3 = new Date();
        Date date4 = date2;//多态
        System.out.println(date4.getClass());
        long time2 = date3.getTime();
        java.sql.Date date5 = new java.sql.Date(time2);
        System.out.println(date5.toString());//2022-11-10


    }








}
