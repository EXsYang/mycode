package com.hspedu.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author yangda
 * @create 2024-04-11-1:18
 * @description:
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {


    //装配RedisTemplate
    @Resource
    private RedisTemplate redisTemplate;

    //编写第一个测试方法
    //演示测试数据和获取数据
    // http://localhost:8080/redisTest/t1
    @GetMapping("/t1")
    public String t1(){

        //设置值到redis
        redisTemplate.opsForValue().set("book","天龙八部~");

        //从redis获取值
        String book = (String)redisTemplate.opsForValue().get("book");

        return book;

    }
    //编写方法测试 list,set,hash,zset 存数据和获取数据
    // http://localhost:8080/redisTest/t2
    @GetMapping("/t2")
    public String t2(){

        //list-存
        redisTemplate.opsForList().leftPush("books","西游记");
        redisTemplate.opsForList().leftPush("books","红楼梦");
        //list-一次存多个
        redisTemplate.opsForList().leftPushAll("books","三国演义","水浒传");

        String bookList = "";
        //list-获取
        List books = redisTemplate.opsForList().range("books", 0, -1);
        for (Object book : books) {
            System.out.println("book-->" + book.toString());
            bookList += book.toString() + " ";
        }

        //hash
        // redisTemplate.opsForHash()
        //set
        // redisTemplate.opsForSet()
        //zset
        // redisTemplate.opsForZSet()


        return bookList;

    }

    /**
     * 编写一个方法，获取所有的key
     * 测试如果没有RedisConfig类是否会出现错误
     * 1. 如果没有提供 RedisConfig 配置类 , springboot 会使用默认配置, 也可以使用
     * 2. 如果没有提供 RedisConfig 配置类 , springboot 会使用默认配置, 但是会存在问题,
     *    比如 redisTemplate 模糊查找 key 数据为空 ，即在Java中使用redisTemplate keys * 查询为空
     *
     * http://localhost:8080/redisTest/t3
     */
    @RequestMapping("/t3")
    public String t3(){

        System.out.println("t3 redisTemplate="+redisTemplate);
        // t3 redisTemplate=org.springframework.data.redis.core.RedisTemplate@50f40653


        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            //如果没有RedisConfig配置类中的常规配置，这里什么也不会输出！！
            System.out.println("key===> " + key);
        }

        return "OK";
    }


    /**
     * Unrecognized token 'beijing': was expecting ('true', 'false' or 'null')
     * 看报错，是 jason 转换异常，实际上是因为 redisTemplate 在做数据存储的时候会把存
     * 储的内容序列化，所以，redisTemplate 读取的时候也会反序列化，而在 redis 客户端
     * set 的时候并不会做序列化，因此 set 的进去的值在用 redisTemplate 读的时候就会报类
     * 型转换异常了
     *
     *
     * 编写方法，通过Java程序的redisTemplate
     * 获取通过redis-cli客户端或者jedis连接存入到redis的数据
     * localhost:8080/redisTest/t4
     *
     * 解决方案 : 最简单的就是用程序重新 set 一遍即可
     * ,即使用redis-cli客户端存就使用redis-cli客户端取
     * 使用redisTemplate存就使用redisTemplate取,
     * 因为redisTemplate在存时会序列化，取时会进行反序列化
     * 而redis-cli客户端存取都不进行序列化，
     * 因此当使用redisTemplate反序列化取redis-cli客户端存的
     * 未经过序列化的数据时会出现序列化造成的一个类型转换异常
     */

    @RequestMapping("/t4")
    public String t4(){

        // String val = (String)redisTemplate.opsForValue().get("k1");
        // at [Source: (byte[])"jack"; line: 1, column: 5]; nested exception is com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'jack': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
        //  at [Source: (byte[])"jack"; line: 1, column: 5]] with root cause

        //这里的book，是在t1中使用redisTemplate放入redis的,所以在使用redisTemplate取出不会报错
        String val = (String)redisTemplate.opsForValue().get("book");
        System.out.println("t4 val=> " + val);
        return val;
    }


}
