package com.hspedu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author yangda
 * @create 2024-04-10-21:23
 * @description:
 */
public class Jedis_ {


    /**
     * 连接Redis
     * 1. 如果Redis设置了密码，则需要进行身份校验 requirepass foobared
     * 2. 因为需要连接到redis对应的端口，比如6379, 就需要配置Linux防火墙,放开端口
     * firewall-cmd --add-port=6379/tcp --permanent
     * firewall-cmd --reload
     * firewall-cmd --list-all
     * 3. 注意修改配置文件 bind 字段 即需要注销bind字段, 支持远程连接
     * <p>
     * 4. 关闭保护模式 `protected-mode no`  即将yes设置为no
     *    (1)如果使用了requirepass密码认证，
     *       保护模式就不是必要的，此时即使保护模式不设置为no也可以远程连接上。
     *    (2)如果不使用密码 requirepass ,就需要把保护模式改为no才可以连接上
     *       如果连不上提示 protected mode enable ... 就需要 将protected-mode yes设置为no
     */
    @Test
    public void con() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");

        String res = jedis.ping();
        System.out.println("连接成功 ping 返回结果=" + res);

        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器

    }

    /**
     * key操作
     */
    @Test
    public void key() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");

        //设置key
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        //获取key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println("key ==> " + key);
        }

        //判断key是否存在exists ，ttl
        System.out.println(jedis.exists("k1"));//T
        System.out.println(jedis.ttl("k2"));//-1
        System.out.println(jedis.get("k3"));//v3


        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器


    }

    /**
     * string操作
     */
    @Test
    public void string() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");

        //批量设置k-v
        jedis.mset("k1", "jack", "k2", "tom", "k3", "hsp");
        //批量获取k-v
        List<String> mget = jedis.mget("k1", "k2");
        for (String s : mget) {
            System.out.println("s--> " + s);
        }


        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器


    }

    /**
     * list 操作
     */
    @Test
    public void list() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");


        //添加list数据
        jedis.lpush("name_list", "jack", "tom", "hsp");
        List<String> name_list = jedis.lrange("name_list", 0, -1);
        for (String name : name_list) {
            System.out.println("name=" + name);
        }


        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器


    }

    /**
     * set 操作
     */
    @Test
    public void set() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");

        jedis.sadd("city", "北京", "上海", "广东");
        jedis.sadd("city", "大连");
        jedis.sadd("city", "南京");
        Set<String> cities = jedis.smembers("city");
        for (String city : cities) {
            System.out.println("city=" + city);
        }

        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器


    }

    /**
     * hash 操作
     */
    @Test
    public void hash() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");

        jedis.hset("hash01", "name", "李白");
        jedis.hset("hash01", "age", "23");

        //先构建一个Map,然后加入
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("job","java工程师");
        map.put("name","smith");
        map.put("email","smith@qq.com");

        jedis.hset("hash02",map);


        //获取单个属性/域 数据
        String name = jedis.hget("hash01", "name");
        String age = jedis.hget("hash01", "age");

        System.out.println("name=" + name + " age=" + age);

        //同时获取多个属性/域 数据  hash01
        List<String> hmget = jedis.hmget("hash01", "age", "name");
        System.out.println("同时获取多个属性/域 数据 hmget =>" + hmget);

        System.out.println("-------------");
        //同时获取多个属性/域 数据  hash02
        List<String> person = jedis.hmget("hash02", "name", "job", "email");
        System.out.println(person);
        for (String s : person) {
            System.out.println("s===>" + s);
        }

        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器


    }

    /**
     * zset 操作 有序集合
     */
    @Test
    public void zset() {

        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        jedis.auth("foobared");

        //添加
        jedis.zadd("hero2",1,"a");
        jedis.zadd("hero2",2,"b");
        jedis.zadd("hero2",3,"c");
        jedis.zadd("hero2",4,"d");
        jedis.zadd("hero2",5,"e");

        //这里是基本数据类型long 填入int类型会自动类型提升,但如果是Long包装类型 则不行
        //Redis ZRANGE 命令返回有序集中，指定区间内的成员，
        // 其中成员的按分数值递增(从小到大)来排序，
        // 具有相同分数值的成员按字典序(lexicographical order )来排列。
        Set<String> hero2 = jedis.zrange("hero2", 0, -1L); //按照评分从小到大排序 [a, b, c, d, e]
        // Set<String> hero2 = jedis.zrevrange("hero2", 0, -1L); //按照评分从大到小排序 [e, d, c, b, a]

        System.out.println(hero2);


        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器


    }


}
