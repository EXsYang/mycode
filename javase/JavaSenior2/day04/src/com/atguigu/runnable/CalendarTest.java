package com.atguigu.runnable;

import org.junit.Test;

import java.util.Calendar;

/**
 * @author yangda
 * @create 2021-12-07-10:02
 */
public class CalendarTest {
    @Test
    public void test6() {
        Calendar calendar = Calendar.getInstance();//getInstance()是static的，所以可以通过类.
        System.out.println(calendar);

        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));

        System.out.println(calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日");



    }

    @Test
    public void test7() {


    }





}
