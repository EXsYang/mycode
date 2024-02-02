package com.atguigu.Date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author yangda
 * @description:
 * @create 2022-11-10-17:22
 */
public class CalendarTest {

    @Test
    public void testCalendar() {

        //获取Calendar类的对象
        //方式一：
        Calendar instance = Calendar.getInstance();//静态方法
//        System.out.println(instance);
//        System.out.println(instance.getClass());

        //方式二；用Calendar类的实现类
        Calendar instance1 = new GregorianCalendar();

//        System.out.println(instance1);

        //常用方法：
        //get()
        int day = instance.get(Calendar.DAY_OF_MONTH);//获取这天是当月的第几天
        System.out.println(day);
//        System.out.println(Calendar.DAY_OF_YEAR);//6 Calendar.DAY_OF_YEAR是一个int类型的数

//        System.out.println(instance.get(5));

        //set()
        instance.set(Calendar.DAY_OF_MONTH,5);
        int days = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);


        //add()
        instance.add(Calendar.DAY_OF_MONTH,3);
        days = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //getTime()//日历类 ---> Date
        Date time = instance.getTime();
        System.out.println(time);

        //setTime()Date ---> 日历类 //原本的就改的，可变性
        Date date = new Date();
        instance.setTime(date);
         days = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));


    }


}
