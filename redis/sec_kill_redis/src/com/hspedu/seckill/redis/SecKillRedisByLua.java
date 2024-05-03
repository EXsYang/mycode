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
    /**
     * Lua 脚本在 Redis 中的使用非常关键，尤其是在需要原子性操作的场景下
     * ，如秒杀、库存减少等。使用 Lua 脚本可以在单个事务中执行多个操作，
     * 而且不会被其他命令打断，确保了操作的原子性。
     * 这是因为 Redis 是单线程的，一旦开始执行 Lua 脚本，
     * 它将完成整个脚本的执行，中间不会插入其他命令。
     *
     *
     *
     * local userid=KEYS[1]; -- 用户ID
     * local ticketno=KEYS[2]; -- 票务编号
     * local stockKey='sk:'..ticketno..":ticket"; -- 库存的键
     * local usersKey='sk:'..ticketno..":user"; -- 已购用户集合的键
     *
     * local userExists=redis.call("sismember",usersKey,userid); -- 检查用户是否已经在已购用户集合中
     * if tonumber(userExists)==1 then
     *     return 2; -- 如果用户已经存在，则返回2，表示不能重复购买
     * end
     *
     * local num= redis.call("get" ,stockKey); -- 获取当前票的库存数量
     * if tonumber(num)<=0 then
     *     return 0; -- 如果库存不足，返回0，表示票已经卖光了
     * else
     *     redis.call("decr",stockKey); -- 否则，减少库存数量
     *     redis.call("sadd",usersKey,userid); -- 并把用户添加到已购用户集合中
     * end
     *
     * return 1; -- 抢票成功，返回1
     *
     *
     * 步骤解释
     * 用户和票据识别：脚本首先定义了两个输入参数 userid 和 ticketno，
     * 分别表示用户 ID 和票务编号。
     *
     * 构造键名：通过拼接字符串构造库存键 (stockKey) 和已购用户集合键 (usersKey)，
     * 这些键将用于访问和修改 Redis 中的数据。
     *
     * 检查用户是否重复购买：使用 sismember
     * 命令检查指定用户是否已经在 usersKey 对应的集合中，
     * 如果已经存在，表示该用户已购买过，返回 2。
     *
     * 检查库存：通过 get 命令获取 stockKey 对应的库存数量。
     * 如果库存数量小于或等于 0，则返回 0 表示库存已经卖完。
     *
     * 执行购买操作：如果库存充足，使用 decr 命令减少库存数量，
     * 并使用 sadd 命令将用户 ID 添加到已购集合中。最后返回 1 表示抢票成功。
     *
     * 通过将这些操作集成到单个 Lua 脚本中，Redis 保证了这些步骤在执行时的原子性。
     * 即使有成千上万的请求同时到达，每个 Lua 脚本的执行都是连续的，
     * 不会被其他 Redis 命令中断。这对于秒杀等需要快速且准确处理的场景尤为重要，
     * 可以有效防止超卖和数据不一致的问题。
     *
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
