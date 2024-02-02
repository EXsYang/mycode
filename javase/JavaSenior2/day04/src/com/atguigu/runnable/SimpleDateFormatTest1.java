package com.atguigu.runnable;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangda
 * @create 2022-06-04-17:33
 */
public class SimpleDateFormatTest1 {

    @Test
    public void test1() {
        //Date类掌握两个构造器和两个方法
        /*   Date(),Date(long date)
         * toString():显示当前的年月日时分秒
         * getTime()：获取当前Date对象对应的毫秒数。（时间戳）
         * */
        Date date = new Date();
        System.out.println(date);//Sat Jun 04 17:39:00 CST 2022
        //String date1 = new String(date);//Cannot resolve constructor 'String(java.util.Date)'

        //util下的Date转换为sql下的Date，共通的点是毫秒数

        java.sql.Date date1 = new java.sql.Date(date.getTime());

        date.getTime();
        date1.getTime();

        String date2 = "5242623535";
        Long l = Long.parseLong(date2);
        Date date3 = new Date(156456);//自动类型提升，int转换为long
        System.out.println(date3);
        int a = 5434;
        long b = a + 33;


    }
@Test
public void test2() throws ParseException {
    Date date = new Date();
    System.out.println("当前日期："+ date);
//  实例化SimpleDateFormat:使用默认的构造器
    SimpleDateFormat sdf = new SimpleDateFormat();
    System.out.println(sdf);//java.text.SimpleDateFormat@b5341f2a
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
    //格式化：日期 --> 字符串

    String format = sdf.format(date);//默认格式化
    String format1 = sdf1.format(date);//自定义格式化

    Date date1 = sdf.parse(format);
    System.out.println("date1:  "+ date1);//date1:  Sat Jun 04 19:28:00 CST 2022

    Date date2 = sdf1.parse(format1);
    System.out.println("date2:  "+ date2);//date2:  Sat Jun 04 19:28:40 CST 2022
    System.out.println("date3:  "+ sdf1.format(date2));//date3:  2022.06.04 公元 at 19:29:49 CST


    System.out.println(format);
    System.out.println(format1);


}




}
