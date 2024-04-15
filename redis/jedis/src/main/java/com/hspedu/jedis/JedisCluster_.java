package com.hspedu.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangda
 * @create 2024-04-15-0:58
 * @description: 使用Java代码操作redis集群
 */
public class JedisCluster_ {
    public static void main(String[] args) {

        /**
         * 1. 这里的set可以放入多个入口 HostAndPort
         * 2. 因为我们没有做日志配置，输出时会有些红色警告提示，但不影响使用
         * 3. 如果我们使用的是集群，需要把相关的端口都打开,否则会报错
         * 4. JedisCluster 有许多种构造器，也可以直接传入HostAndPort 代码如下:
         *  public JedisCluster(HostAndPort node) {
         *         this(Collections.singleton(node));
         *     }
         */
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.198.135",6379));

        JedisCluster jedisCluster = new JedisCluster(set);
        jedisCluster.set("address","北京~bj");
        //存入redis集群后 中文是以十六进制码的形式存入的如下:
        //127.0.0.1:6379> get address
        // "\xe5\x8c\x97\xe4\xba\xac~bj"

        String address = jedisCluster.get("address");
        System.out.println("address-->" + address);
        //jedis从redis集群拿回来的中文会被处理为正常的显示格式
        // address-->北京~bj

        jedisCluster.close();


    }


}
