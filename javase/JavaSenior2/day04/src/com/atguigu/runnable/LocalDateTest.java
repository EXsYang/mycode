package com.atguigu.runnable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yangda
 * @create 2022-06-04-20:56
 */
public class LocalDateTest {

    public static void main(String[] args) {

        //1.使用now返回表示当前日期时间的对象
        LocalDateTime ldt = LocalDateTime.now();//LocalDate.now()       LocalTime.now()
        System.out.println(ldt);//2022-06-04T21:00:21.484
        System.out.println("年："+ ldt.getYear());
        System.out.println("月："+ ldt.getMonth());//这个月份加过了，当前是六月份,此时也是JUNE
        System.out.println("月："+ ldt.getMonthValue());
        System.out.println("日："+ ldt.getDayOfMonth());
        System.out.println("时："+ ldt.getHour());
        System.out.println("分"+ ldt.getMinute());
        System.out.println("秒："+ ldt.getSecond());

        //2.使用DateTimeFormatter对象来进行格式化
        //创建DateTimeFormatter对象
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd日 HH小时mm分钟ss秒");
        String format = dateTimeFormatter.format(ldt);
        System.out.println("格式化的日期："+format);

        //提供plus 和 minus方法可以对当前时间进行加或减
        //看看3434天后 是什么时候 把年月日-时分秒输出
        LocalDateTime localDateTime = ldt.plusDays(3434);
        System.out.println("3434天后是："+dateTimeFormatter.format(localDateTime));

        //看看342分钟以前 是什么时候 把年月日-时分秒输出
        LocalDateTime localDateTime1 = ldt.minusMinutes(342);
        System.out.println("342分钟以前是："+dateTimeFormatter.format(localDateTime1));


    }




}
