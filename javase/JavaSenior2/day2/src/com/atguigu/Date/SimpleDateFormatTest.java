package com.atguigu.Date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangda
 * @description:format()、parse() 空参构造器和带参构造器
 * @create 2022-11-10-16:31
 */
public class SimpleDateFormatTest {
    @Test
    public void testSdf() throws ParseException {
        //字符串:"2020-11-03"转换为java.sql.Date

        Date date = new Date();
        Date date1 = new Date(3233242143l);
        System.out.println(date);//Thu Nov 10 16:34:10 CST 2022

        long time = date.getTime();
        System.out.println(time);//1668069316240

        System.out.println(date.toString());//Thu Nov 10 16:35:16 CST 2022

        //创建SimpleDateFormat的对象，用对象调方法
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        //格式化  日期 ---> 字符串
        String format = simpleDateFormat.format(date);
        System.out.println(format);//22-11-10 下午4:39

        //解析    字符串 ---> 日期
        Date parse = simpleDateFormat.parse(format);//parse()会抛异常
        Date parse1 = simpleDateFormat.parse("23-11-10 下午4:46");//parse()会抛异常
        System.out.println(parse);
        System.out.println(parse1);

        System.out.println("********************************");

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date parse2 = simpleDateFormat1.parse("2020-11-03");
        System.out.println(parse2);//Tue Nov 03 00:00:00 CST 2020

        long time1 = parse2.getTime();
        java.sql.Date date2 = new java.sql.Date(time1);

        System.out.println("转换成sql下的Date：" + date2 + date2.getClass());


    }















}
