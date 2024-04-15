package com.hspedu.seckill.redis;

import com.hspedu.seckill.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yangda
 * @create 2024-04-13-21:26
 * @description: 使用Lua脚本完成秒杀, 可以同时解决超卖和库存遗留问题！
 */
public class SecKillRedisByLua {


    /**
     * 老师说明
     * 1. 这个脚本字符串是在 lua 脚本上修改的, 但是要注意不完全是字符串处理
     * 2. 比如 : 这里我就使用了 \" , 还有换行使用了 \r\n
     * 3. 这些都是细节，如果你直接包 lua 脚本粘贴过来，不好使,一定要注意细节
     * 4. 如果写的不成功，就在老师这个代码上修改即可
     */
    static String secKillScript = "local userid=KEYS[1];\r\n" +
            "local ticketno=KEYS[2];\r\n" +
            "local stockKey='sk:'..ticketno..\":ticket\";\r\n" +
            "local usersKey='sk:'..ticketno..\":user\";\r\n" +
            "local userExists=redis.call(\"sismember\",usersKey,userid);\r\n" +
            "if tonumber(userExists)==1 then \r\n" +
            " return 2;\r\n" +
            "end\r\n" +
            "local num= redis.call(\"get\" ,stockKey);\r\n" +
            "if tonumber(num)<=0 then \r\n" +
            " return 0;\r\n" +
            "else \r\n" +
            " redis.call(\"decr\",stockKey);\r\n" +
            " redis.call(\"sadd\",usersKey,userid);\r\n" +
            "end\r\n" +
            "return 1";


    //使用lua脚本完成秒杀的核心方法
    public static boolean doSecKill(String uid, String ticketNo) {
        //先从redis连接池，获取连接
        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPoolInstance.getResource();

        //就是将lua脚本进行加载。即加载到内存
        String sha1 = jedis.scriptLoad(secKillScript);

        //result是根据指定的sha1校验码，执行缓存在redis服务器的脚本
        Object result = jedis.evalsha(sha1, 2, uid, ticketNo);

        //这里就是根据lua脚本中写的逻辑 从redis执行完lua脚本后返回的值
        String resString = String.valueOf(result);

        //根据lua脚本执行返回的结果，做相应的处理
        if ("0".equals(resString)) {
            System.out.println("lua脚本-票已经卖光了...");
            jedis.close();
            return false;
        }
        if ("2".equals(resString)) {
            System.out.println("lua脚本-不能重复购买...");
            jedis.close();
            return false;
        }
        if ("1".equals(resString)) {
            System.out.println("lua脚本-抢票成功...");
            jedis.close();
            return true;
        } else {
            System.out.println("lua脚本-抢票失败.......");
            jedis.close();
            return false;
        }

    }


}
