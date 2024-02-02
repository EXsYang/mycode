package com.hspedu.test;

import com.hspedu.entity.Monster;
import com.hspedu.mapper.MonsterMapper;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yangda
 * @create 2023-11-05-16:56
 * @description:
 */
public class MonsterMapperTest {

    //属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    //编写方法完成初始化

    /**
     * 1. 当方法标注 @Before, 表示在执行你的目标测试方法前，会先执行该方法
     * 即执行目标测试方法前都要先执行init方法
     * 2. 执行测试方法前都会先去拿到 sqlSession 和 monsterMapper
     * 3. 这样写的好处 提高代码复用性 不用在每个测试方法中再写一遍了
     */
    @Before
    public void init() {

        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        //获取到MonsterMapper对象 class com.sun.proxy.$Proxy7 是一个代理对象
        //,底层使用的动态代理机制，后面我们自己实现底层机制时会讲到
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("monsterMapper= " + monsterMapper.getClass());
    }


    @Test
    public void getMonsterById() {

        Monster monster = monsterMapper.getMonsterById(6);
        System.out.println("monster= " + monster);
        //如果是查询，不需要提交事务 但是关闭连接释放资源还是必要的
        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("查询一条记录成功..");

    }


    //测试一级缓存
    @Test
    public void level1CacheTest() {
        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        //----------------------------测试一级缓存[一个一个的规则进行测试.]-----------------------
        //1. 当我们再次查询 id=3 的 Monster 时，直接从一级缓存获取,不会再次发出 sql
        System.out.println("------因为一级缓存默认是打开的，当你再次查询相同的id时，不会再发出sql------");
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        monster = monsterMapper.getMonsterById(6);
        System.out.println("--" + monster + "--");

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }
    //测试一级缓存，关闭了 sqlSession 后 一级缓存失效
    @Test
    public void level1CacheTest2() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        //关闭sqlSession,一级缓存失效
        if (sqlSession != null){
            sqlSession.close();
        }

        // 因为在上面关闭了，所以需要重新再获取一下sqlSession 和 monsterMapper
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        //1. 当我们再次查询 id=3 的 Monster 时，直接从一级缓存获取,不会再次发出 sql
        System.out.println("------如果你关闭了 sqlSession，当你再次查询相同的id时，仍然会再发出sql------");
        // 因为一级缓存 的存储结构在sqlSession中，sqlSession为null,更不会有一级缓存cache
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }


    //测试一级缓存，如果执行sqlSession.clearCache()   一级缓存失效
    @Test
    public void level1CacheTest3() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        /*@Override
        public void clearCache() {
            executor.clearLocalCache();
        }*/
        sqlSession.clearCache();

        //1. 当我们再次查询 id=3 的 Monster 时，直接从一级缓存获取,不会再次发出 sql
        System.out.println("------如果你执行sqlSession.clearCache()，当你再次查询相同的id时，会再发出sql------");
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }


    //测试一级缓存，. 当对同一个 monster 修改，该一级缓存[对象数据]会失效
    @Test
    public void level1CacheTest4() {

        //查询id=3的monster
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        monster.setName("黄鼠狼");


        //1. 当我们再次查询 id=3 的 Monster 时，直接从一级缓存获取,不会再次发出 sql
        System.out.println("------ 如果你修改了同一个对象，该对象在一级缓存会失效，当你再次查询相同的id时，会再发出sql------");
        // 当对同一个 monster 修改，该一级缓存[对象数据]会失效
        monsterMapper.updateMonster(monster);
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }



    //测试二级缓存
    @Test
    public void level2CacheTest() {
        //查询id=3的monster
        // 第1次在二级缓存没有拿到数据 所以 Cache Hit Ratio: 0.0
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        //关闭sqlSession,一级缓存失效
        if (sqlSession != null){
            sqlSession.close();
        }

        // 因为在上面关闭了，所以需要重新再获取一下sqlSession 和 monsterMapper
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);


        System.out.println("------虽然前面关闭了sqlSession,因为配置了二级缓存，" +
                "当你再次查询相同的id时，依然不会再发出sql,而是从二级缓存获取------");
        // 第2次在二级缓存 拿到数据 所以 Cache Hit Ratio: 0.5
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        // 第3次在二级缓存 拿到数据 所以 Cache Hit Ratio: 0.6666666666666666
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }




    // 缓存执行顺序是：二级缓存-->一级缓存-->数据库
    //
    @Test
    public void cacheSeqTest() {


        System.out.println("查询第 1 次");
        // 这里会去DB查询，发出SQL语句 ,这里会将数据放入到一级缓存 Cache Hit Ratio: 0.0
        Monster monster1 = monsterMapper.getMonsterById(3);
        System.out.println(monster1);

        //这里关闭sqlSession,一级缓存数据没有了
        //当我们关闭一级缓存的时候，如果你配置了二级缓存，那么一级缓存的数据会放入到二级缓存
        //即 会在sqlSession关闭的时候，将一级缓存中的数据放入到二级缓存
        sqlSession.close();

        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);

        System.out.println("查询第 2 次");
        // 这里是从二级缓存获取 id=3 的monster数据的信息，不会发出SQL Cache Hit Ratio: 0.5
        Monster monster2 = monsterMapper.getMonsterById(3);
        System.out.println(monster2);

        System.out.println("查询第 3 次");
        // 这里是从二级缓存获取 id=3 的monster数据的信息 也不会发出SQL Cache Hit Ratio: 0.6666666666
        Monster monster3 = monsterMapper.getMonsterById(3);
        System.out.println(monster3);

        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }


    //分析缓存执行顺序
    //二级缓存=>一级缓存=>DB
    //重要的一个原则：二级缓存(数据)是在一级缓存关闭后才有的
    @Test
    public void cacheSeqTest2() {

        System.out.println("查询第 1 次");
        // 到DB查询数据，会发出SQL语句，Cache Hit Ratio: 0.0
        Monster monster1 = monsterMapper.getMonsterById(1);
        System.out.println(monster1);

        //这里我们没有关闭sqlSession(即不会将一级缓存的数据放入到二级缓存)

        System.out.println("查询第 2 次");
        // 从一级缓存获取id=3的数据，会发出SQL语句，Cache Hit Ratio: 0.0
        Monster monster2 = monsterMapper.getMonsterById(1);
        System.out.println(monster2);

        System.out.println("查询第 3 次");
        // 还是从一级缓存获取id=3的数据，会发出SQL语句，Cache Hit Ratio: 0.0
        Monster monster3 = monsterMapper.getMonsterById(1);
        System.out.println(monster3);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }


    //测试 ehCache 级缓存，及其失效情况
    @Test
    public void ehCacheTest() {

        System.out.println("查询第 1 次");
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        //----------------------------测试 ehCache 缓存-----------------------

        // if (sqlSession != null){ // 一级缓存[数据]失效 => 将数据放入到二级缓存[ehcache]
        //     sqlSession.close();
        // }
        //
        // sqlSession = MyBatisUtils.getSqlSession();
        // monsterMapper = sqlSession.getMapper(MonsterMapper.class);


        //. 当我们关闭 sqlSession 会话后，再次查询 id =3 的 monster 时，如果有 ehCache 缓存
        // 就会从 ehCache 缓存读取. 不会发出 sql
        System.out.println("=======关闭 sqlSession 会话后, 再次查询同一数据，如果配置了" +
                "二级缓存(ehCache),不会到数据库查询 不会发出SQL,而是到二级缓存[ehcache]获取数据 ========");

        //再次查询id=3的monster信息
        System.out.println("查询第 2 次");
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");

        //再次查询id=3的monster信息,仍然到二级缓存(ehcache),获取数据，不会发出SQL
        System.out.println("查询第 3 次");
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");



        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }



}
