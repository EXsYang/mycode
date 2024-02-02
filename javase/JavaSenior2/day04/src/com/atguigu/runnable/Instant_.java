package com.atguigu.runnable;

import java.util.Date;
import java.time.Instant;

/**
 * @author yangda
 * @create 2022-06-04-21:37
 */
public class Instant_ {
    public static void main(String[] args) {

        //1.通过静态方法 now()  获取表示当前时间戳的对象
        Instant instant = Instant.now();
        System.out.println(instant);

        //2.通过from 可以把Instant转成 Date
        Date date = Date.from(instant);

        //3.通过date的toInstant()可以把date转成Instant对象
        Instant instant2 = date.toInstant();







    }
}
