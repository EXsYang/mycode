package com.hspedu.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yangda
 * @create 2024-04-11-1:18
 * @description:
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {


    // 装配RedisTemplate，操作Redis 注意RedisTemplate<K, V> 中可以指定泛型，如RedisTemplate<String,User>
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 编写方法，使用Redis分布式锁，完成对key 为num的数据的+1操作
     * 测试如下:使用ab工具模拟高并发
     * [root@hspEdu100 ~]# ab -n 1000 -c 100 http://192.168.198.1:8080/redisTest/lock
     *
     * 对该方法的详细解释可以参考redis随手笔记.md 和老韩的redis笔记
     */
    @GetMapping("/lock")
    public void lock() {

        //先得到一个uuid值,作为锁的值，防止误删其他用户的锁
        String uuid = UUID.randomUUID().toString();

        //1. 获取锁/设置锁 key->lock :
        // redisTemplate.opsForValue().setIfAbsent() 就相当于执行 setnx 指令
        // Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "ok");

        //设置锁的同时设置过期时间为3秒，防止在业务代码中因异常退出
        // ，没有执行到释放锁的语句 redisTemplate.delete("lock"); 造成死锁。
        // 注意设置的过期时间，一定要比执行业务代码所有的时间长一些，否则会造成获取锁控制不住了，造成锁紊乱
        // Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "ok",3, TimeUnit.MICROSECONDS);//微秒
        // Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "ok",3, TimeUnit.MILLISECONDS); //毫秒
        // Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "ok",3, TimeUnit.SECONDS); //秒

        // 将前面得到的uuid值,作为锁的值
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS); //秒

        if (lock) { //true, 说明获取锁/设置锁成功
            // key 为 num 的数据 要事先在redis初始化
            Object value = redisTemplate.opsForValue().get("num");
            //判断返回的value是否有值
            if (null == value || !StringUtils.hasText(value.toString())) {
                /**
                 * 返回值类型： 返回值  vs 没返回值
                 *   如果方法有返回值，则必须在方法声明时，指定返回值的类型。同时，方法中，需要使用
                 *   return关键字来返回指定类型的变量或常量：“return 数据”。
                 *   如果方法没返回值，则方法声明时，使用void来表示。通常，没返回值的方法中，就不需要
                 *   使用return.但是，如果使用的话，只能“return;”表示结束此方法的意思。
                 */
                //如果 value没有值 直接返回，即结束方法
                return;
            }

            //value有值，就将其转为int类型
            // int num = Integer.parseInt((String) value);
            int num = Integer.parseInt(value.toString());
            //将num+1 再重新设置回redis,注意这里使用的是前加加
            redisTemplate.opsForValue().set("num", ++num);

            //1释放锁 - lock
            // redisTemplate.delete("lock");


            //2释放锁 - lock。为了防止误删除其他用户的锁，先判断当前的锁是不是前面获取/设置到redis的锁，如果相同，再释放
            // if (uuid.equals((String) redisTemplate.opsForValue().get("lock"))){
            //     redisTemplate.delete("lock");
            // }

            //3释放锁=====使用 lua 脚本来释放锁, 控制删除原子性========
            // 注意Lua脚本的代码也可以做成一个文件放在resources目录下进行读取,通过配置文件注入DefaultRedisScript对象进行使用
            // ,具体可以参考高并发秒杀项目V7.0版本的秒杀方法doSeckill()

            // 定义 lua 脚本
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            // 使用 redis 执行 lua 执行
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);

            // 设置一下返回值类型 为 Long
            // 因为删除判断的时候，返回的 0,给其封装为数据类型。如果不封装那么默认返回 String 类型，
            // 那么返回字符串与 0 会有发生错误。
            redisScript.setResultType(Long.class);

            // 第一个是 script 脚本 ，第二个需要判断的 key，第三个就是 key 所对应的值
            // 老 韩 解 读 Arrays.asList("lock") 会 传 递 给 script 的 KEYS[1] , uuid 会 传 递 给 ARGV[1] , 其它的小伙伴应该很容易理解
            redisTemplate.execute(redisScript, Arrays.asList("lock"), uuid);

        } else { //设置锁/获取锁失败，就休眠100ms,再重新设置锁/获取锁

            try {
                Thread.sleep(100);
                lock(); //递归，重新执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }


    //编写第一个测试方法
    //演示测试数据和获取数据
    // http://localhost:8080/redisTest/t1
    @GetMapping("/t1")
    public String t1() {

        //设置值到redis
        redisTemplate.opsForValue().set("book", "天龙八部~");

        //从redis获取值
        String book = (String) redisTemplate.opsForValue().get("book");

        return book;

    }

    //编写方法测试 list,set,hash,zset 存数据和获取数据
    // http://localhost:8080/redisTest/t2
    @GetMapping("/t2")
    public String t2() {

        //list-存
        redisTemplate.opsForList().leftPush("books", "西游记");
        redisTemplate.opsForList().leftPush("books", "红楼梦");
        //list-一次存多个
        redisTemplate.opsForList().leftPushAll("books", "三国演义", "水浒传");

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
     * 比如 redisTemplate 模糊查找 key 数据为空 ，即在Java中使用redisTemplate keys * 查询为空
     * <p>
     * http://localhost:8080/redisTest/t3
     */
    @RequestMapping("/t3")
    public String t3() {

        System.out.println("t3 redisTemplate=" + redisTemplate);
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
     * <p>
     * <p>
     * 编写方法，通过Java程序的redisTemplate
     * 获取通过redis-cli客户端或者jedis连接存入到redis的数据
     * localhost:8080/redisTest/t4
     * <p>
     * 解决方案 : 最简单的就是用程序重新 set 一遍即可
     * ,即使用redis-cli客户端存就使用redis-cli客户端取
     * 使用redisTemplate存就使用redisTemplate取,
     * 因为redisTemplate在存时会序列化，取时会进行反序列化
     * 而redis-cli客户端存取都不进行序列化，
     * 因此当使用redisTemplate反序列化取redis-cli客户端存的
     * 未经过序列化的数据时会出现序列化造成的一个类型转换异常
     */

    @RequestMapping("/t4")
    public String t4() {

        // String val = (String)redisTemplate.opsForValue().get("k1");
        // at [Source: (byte[])"jack"; line: 1, column: 5]; nested exception is com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'jack': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
        //  at [Source: (byte[])"jack"; line: 1, column: 5]] with root cause

        //这里的book，是在t1中使用redisTemplate放入redis的,所以在使用redisTemplate取出不会报错
        String val = (String) redisTemplate.opsForValue().get("book");
        System.out.println("t4 val=> " + val);
        return val;
    }



    @RequestMapping("/t5")
    public String t5() {

        redisTemplate.opsForValue().set("k1",10);
        redisTemplate.opsForValue().set("k2",100);


        //经过测试发现, Long decrement(K key, long delta);  第二个参数为步长

        System.out.println("k1原来的值=" + redisTemplate.opsForValue().get("k1"));
        Long k1 = redisTemplate.opsForValue().decrement("k1");
        System.out.println("decr之后返回的k1的值=" + k1);

        System.out.println("k2原来的值=" + redisTemplate.opsForValue().get("k2"));
        Long k2 = redisTemplate.opsForValue().decrement("k2",3);
        System.out.println("decr之后返回的k2的值=" + k2);


        return k1.toString();
    }
    @RequestMapping("/t6")
    public String t6() {

        //redisTemplate.hasKey() 方法在背后使用的 Redis 命令确实是 EXISTS。
        // 这个命令用于检查给定的键是否存在于 Redis 数据库中。
        // 如果键存在，EXISTS 命令返回 1，如果不存在，则返回 0。
        // 在 Java 的 Spring Data Redis 库中，
        // 这个命令被封装在 hasKey() 方法里，该方法会返回一个布尔值，
        // true 表示键存在，false 表示键不存在。
        Boolean k1 = redisTemplate.hasKey("k1");

        return k1.toString();
    }


}
