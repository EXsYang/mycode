package com.hspedu.seckill.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yangda
 * @create 2024-04-13-18:02
 * @description:
 * 使用连接池的方式获取Redis连接 单例模式-懒汉式
 * 使用连接池的方式优化了直接连接redis的连接超时问题，因为直接获取一个连接肯定比较慢，这里优化了连接超时问题
 * 提高了对redis连接的使用效率，但连接池并不会解决火车票的超卖问题
 */
public class JedisPoolUtil {

    //解读volatile的作用
    //1. 线程的可见性: 当一个线程去修改一个共享变量时, 另外一个线程可以读取这个修改的值
    //2. 顺序的一致性: 禁止指令重排
    //volatile的作用就是保证不会出错
    private static volatile JedisPool jedisPool = null;

    //私有化构造器 防止用户通过JedisPoolUtil创建对象 保证jedisPool是单例的
    private JedisPoolUtil(){

    }

    //保证每次调用返回的 jedisPool是单例 - 这里使用的是双重校验 保证返回的对象一定是单例的
    public static JedisPool getJedisPoolInstance(){

        if (null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if (null == jedisPool){
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    //对jedis连接池进行配置
                    jedisPoolConfig.setMaxTotal(200);
                    jedisPoolConfig.setMaxIdle(32);
                    jedisPoolConfig.setMaxWaitMillis(60 * 1000);
                    //连接池中的连接用光了会阻塞在这里
                    jedisPoolConfig.setBlockWhenExhausted(true);
                    //检查连接是否可以 ping()
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(jedisPoolConfig,"192.168.198.135",6379,60000);
                }
            }
        }
        return jedisPool;
    }


    //释放连接资源
    public static void release(Jedis jedis){

        if (null != jedis){
            jedis.close();// 如果这个jedis是从连接池获取的,这里jedis.close(),就是将jedis对象/连接, 释放/放回jedis连接池
        }

    }

}
