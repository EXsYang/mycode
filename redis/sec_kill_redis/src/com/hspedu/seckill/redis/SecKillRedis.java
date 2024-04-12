package com.hspedu.seckill.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author yangda
 * @create 2024-04-12-23:12
 * @description: 秒杀类 完成秒杀，抢购
 */
public class SecKillRedis {

    /**
     * 秒杀方法
     *
     * @param uid      用户id - 在后台生成
     * @param ticketNo 票的编号，比如北京-成都的ticketNo 就是bj_cd
     * @return
     */
    public static boolean doSecKill(String uid, String ticketNo) {

        //1. uid 和 ticketNo进行非空校验
        //||（逻辑或）：这个逻辑或操作符表示只要 uid 或 ticketNo 其中任何一个为 null，整个条件判断就为真（true）。因此，如果任一变量未被正确初始化或赋值，条件判断结果为真。
        if (uid == null || ticketNo == null) {
            return false;
        }

        //- 连接到Redis 得到jedis对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);

        //- 拼接票的库存key
        String stockKey = "sk:" + ticketNo + ":ticket";

        //- 拼接秒杀用户要存放到的set集合对应的key，这个set集合可以存放对个userId
        String userKey = "sk:" + ticketNo + ":user";

        //- 获取到对应的票的库存,判断是否为null
        String stock = jedis.get(stockKey);
        if (stock == null) {
            System.out.println("秒杀还没有开始,请等待...");
            jedis.close();
            return false;
        }

        //- 判断用户是否重复秒杀/复购
        if (jedis.sismember(userKey, uid)) {
            System.out.println(uid + " 不能重复秒杀...");
            jedis.close();
            return false;
        }

        //- 判断火车票是否还有剩余
        if (Integer.parseInt(stock) <= 0) {
            System.out.println("票已经卖完了,秒杀结束...");
            jedis.close();
            return false;
        }

        // 到这里，就是可以正常买票的逻辑了
        //1. 将票的库存量减1
        jedis.decr(stockKey);
        //2. 将该用户加入到抢票成功对应的set集合中(不会有重复的uid)
        //sadd <key><value1><value2> ..... 将一个或多个 member 元素加入到集合 key 中
        // ，已经存在的member 元素将被忽略
        jedis.sadd(userKey, uid);

        System.out.println(uid + " 秒杀成功..");
        jedis.close();

        return true;
    }

    /**
     * 编写一个方法，看看是否能够连通到指定的Redis
     * <p>
     * 连接Redis
     * 1. 如果Redis设置了密码，则需要进行身份校验 requirepass foobared
     * 2. 因为需要连接到redis对应的端口，比如6379, 就需要配置Linux防火墙,放开端口
     * firewall-cmd --add-port=6379/tcp --permanent
     * firewall-cmd --reload
     * firewall-cmd --list-all
     * 3. 注意修改配置文件 bind 字段 即需要注销bind字段, 支持远程连接
     * <p>
     * 4. 关闭保护模式 `protected-mode no`  即将yes设置为no
     * (1)如果使用了requirepass密码认证，
     * 保护模式就不是必要的，此时即使保护模式不设置为no也可以远程连接上。
     * (2)如果不使用密码 requirepass ,就需要把保护模式改为no才可以连接上
     * 如果连不上提示 protected mode enable ... 就需要 将protected-mode yes设置为no
     */
    @Test
    public void testRedis() {
        //创建连接对象
        Jedis jedis = new Jedis("192.168.198.135", 6379);
        //如果Redis设置了密码，则需要进行身份校验
        //否则报错 NOAUTH Authentication required.
        // jedis.auth("foobared");

        System.out.println(jedis.ping());

        jedis.close();//关闭当前连接，注意并没有关闭Redis服务器

    }


}
