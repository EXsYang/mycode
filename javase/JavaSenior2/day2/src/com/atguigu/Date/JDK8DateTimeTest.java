package com.atguigu.Date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author yangda
 * @description:
 * @create 2022-11-11-9:07
 */
@SuppressWarnings({"all"})
public class JDK8DateTimeTest {

    @Test
    public void testDate() {

        Date date = new Date(2022, 11, 10);
        System.out.println(date);//Sun Dec 10 00:00:00 CST 3922
        Date date1 = new Date(2022 - 1900, 11 - 1, 10);
        System.out.println(date1);//Thu Nov 10 00:00:00 CST 2022
    }

    @Test
    public void test1() {
        //now():获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);//2022-11-11
        System.out.println(localTime);//09:36:30.860
        System.out.println("now: " + localDateTime);//2022-11-11T09:21:15.988

        //of():设置指定的年、月、日、时、分、秒。没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 11, 11, 9, 42, 44);
        System.out.println("of: " + localDateTime1);

//        localDateTime.getTime();  没有getTime()方法

        //getXxx()
        int dayOfMonth = localDateTime.getDayOfMonth();
        System.out.println(dayOfMonth);//11
        Month month = localDateTime.getMonth();
        System.out.println(month);//NOVEMBER
        int monthValue = localDateTime.getMonthValue();
        System.out.println(monthValue);//11
        int year = localDateTime.getYear();
        System.out.println(year);//2022

        //withXxx() 相当于Set,体现不可变性
        LocalDateTime localDateTime2 = localDateTime.withDayOfMonth(22);
        System.out.println("当前时间：" + localDateTime);
        System.out.println("with:   " + localDateTime2);

        //plusXxx()
        LocalDate localDate1 = localDate.plusDays(4);
        System.out.println("localDate:  " + localDate);//2022-11-11
        System.out.println("plus后localDate:  " + localDate1);//2022-11-15
        //minusXxx()
        LocalTime localTime1 = localTime.minusHours(3);
        System.out.println("localTime: " + localTime);//09:54:13.118
        System.out.println("minus后localTime1: " + localTime1);//06:54:13.118

    }


    /*
  DateTimeFormatter:格式化或解析日期、时间
  类似于SimpleDateFormat


  */
    @Test
    public void test3(){
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日    HH : mm : ss");
        String format = dtf.format(ldt);
        System.out.println(format);


    }

    /*
    Instant的使用


     **/
    @Test
    public void test2() {
        //获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        //英国伦敦格林尼治天文台原址的那条经线称为0°经线，也叫本初子午线。
        System.out.println(instant);//2022-11-11T02:04:46.876Z  伦敦本初子午线时间

        //instant ---> Date
        Date date = Date.from(instant);
        System.out.println(date);
        //Date ---> instant
        Date date1 = new Date();
        Instant instant2 = date1.toInstant();
        System.out.println(instant2);


        //添加时间偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2022-11-11T10:04:46.876+08:00

        //获取瞬时点对应的自1970年0时0分0秒(UTC)开始的毫秒数
        long milli = instant.toEpochMilli();//类似于Date中的getTime()
        System.out.println(milli);

        //ofEpochMilli():通过给定的毫秒数，获取Instant实例
        Instant instant1 = Instant.ofEpochMilli(2234234242l);//类似于Date(long millis)
        System.out.println(instant1);


    }









}
