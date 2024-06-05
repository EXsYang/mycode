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


    /**
     * 在 Java 中，除了非常常用的 `synchronized` 关键字用于实现线程同步之外，还有一个不那么常用的关键字与线程安全相关，那就是 `volatile`。
     *
     * ### volatile 关键字
     *
     * `volatile` 是 Java 提供的一种轻量级的同步机制，主要用于确保变量的可见性和防止指令重排序，但它不像 `synchronized` 那样能提供原子性保证。使用 `volatile` 声明的变量可以确保所有线程都能看到该变量的最新值。
     *
     * #### 使用场景和作用
     *
     * 1. **可见性**：当一个变量被声明为 `volatile` 后，意味着每次线程访问变量时都会读取变量的最新值，而不是从线程的私有内存（线程缓存）中读取。同时，当变量的值被修改后，新值立即被写回主存中，其他线程读取时可以看到更新后的值。
     *
     * 2. **防止指令重排序**：在 Java 内存模型中，编译器和处理器可能会对操作顺序进行重排序，以提高性能。但是，当变量声明为 `volatile` 时，会有一个内存屏障，它可以防止特定类型的指令重排序，从而保证了代码执行的顺序性。
     *
     * #### volatile 与 synchronized 的区别
     *
     * - **锁的粒度**：`volatile` 不涉及互斥锁，只确保变量的可见性和有序性，而 `synchronized` 既确保可见性也确保原子性，通过锁机制阻止了多个线程同时执行同步代码段。
     * - **性能开销**：`volatile` 通常比 `synchronized` 更轻量，不会造成线程的阻塞，但是 `volatile` 不适用于一些复杂的状态对的同步，如计数器或累加操作。
     *
     * 使用 `volatile` 关键字需要非常小心，因为它的错误使用可能导致复杂的并发问题。正确使用时，它是实现线程安全的有用工具之一，尤其是在状态标志或完成、中断标志等场景中非常有用。
     */


    //解读volatile的作用[详细的说明，看Redis随手笔记]
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
