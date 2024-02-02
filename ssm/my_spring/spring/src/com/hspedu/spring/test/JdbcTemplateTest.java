package com.hspedu.spring.test;

import com.hspedu.spring.bean.Monster;
import com.hspedu.spring.jdbctemplate.dao.MonsterDao;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @description:
 * JdbcTemplate-基本介绍
 * ● 基本介绍
 * 1. 通过 Spring 可以配置数据源，从而完成对数据表的操作
 * 2. JdbcTemplate 是 Spring 提供的访问数据库的技术。可以将 JDBC 的常用操作封装为模板方法。[JdbcTemplate 类图].
 * @create 2023-09-18-19:16
 */
public class JdbcTemplateTest {

    @Test
    public void testDatasourceByJdbcTemplate() throws SQLException {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        // 按照接口来获取
        // 通过接口获取 获取到的是实现类的对象 就是在容器中的那个对象
        // 要求容器中只有一个实现子类！！
        // 有多个会抛异常  expected single matching bean but found 2: dataSource,dataSource2

        // <bean class="com.mchange.v2.c3p0.ComboPooledDataSource" id="dataSource">
        // ComboPooledDataSource 打开类图可以发现 该类实现了DataSource接口，
        // 因此可以通过接口来获取该对象
        DataSource dataSource = ioc.getBean(DataSource.class);

        System.out.println("dataSource运行类型= " + dataSource.getClass());
        //dataSource运行类型= class com.mchange.v2.c3p0.ComboPooledDataSource

        Connection connection = dataSource.getConnection();
        System.out.println("得到连接connection= " + connection);
        //得到的连接是 返回的数据源的连接对象 是一个代理对象 是通过数据连接池返回的连接
        //连接的类型 其实是代理类型
        //得到连接connection= com.mchange.v2.c3p0.impl.NewProxyConnection@6200f9cb

        //关闭连接
        connection.close();
        System.out.println("ok");
    }

    //测试通过JdbcTemplate对象完成添加数据
    @Test
    public void addDataByJdbcTemplate() throws SQLException {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        //获取jdbcTemplate对象
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //添加方式1
        //String sql = "insert into monster values(400,'红孩儿','枪法')";
        //jdbcTemplate.execute(sql);

        //添加方式2
        String sql = "insert into monster values(?,?,?)";
        int affected = jdbcTemplate.update(sql, 500, "黄袍怪", "吐沙子");
        System.out.println("数据库表受影响的行数affected= " + affected);
        System.out.println("add数据ok");


    }

    //测试通过JdbcTemplate对象完成修改数据
    @Test
    public void updateDataByJdbcTemplate() throws SQLException {
        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        //获取jdbcTemplate对象
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //组织SQL
        String sql = "update monster set skill=? where id=?";
        //下面这条语句执行两次 数据库会再次受影响 不会因为两次修改的数据相同就不修改了
        //会再次覆盖上次的数据完成修改 它没有去判断 它不知道上次的"美女计"修改成功了
        int affected = jdbcTemplate.update(sql, "美女计", 300);
        System.out.println("update ok affected= " + affected);


    }


    //批量添加二个monster 白蛇精和青蛇精
    //这里有一个使用API的技巧
    /**
     * 老师说明
     * 1. 对于某个类， 有很多API, 使用的步骤
     * 2. 老韩的使用技巧（1） 先确定API名字 (2) 根据API提供相应的参数 [组织参数]
     *    (3) 把自己的调用思路清晰 (4) 根据API, 可以推测类似的用法和功能
     */

    /**
     * batch add data
     * 批量添加二个monster 白蛇精和青蛇精-update(sql,List<Object[]>)
     */
    @Test
    public void addBatchDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);//添加..

        //1. 先确定,猜测API名称 batchUpdate[如果出现问题，才重新玩]
        //public int[] batchUpdate(String sql, List<Object[]> batchArgs){}
        //2. 准备参数 将来这里的sql语句也可以写成 delete / update / insert
        String sql = "INSERT INTO monster VALUES(?, ?, ?)";
        //String sql = "insert into monster values(?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{600, "老鼠精", "偷粮食"});
        batchArgs.add(new Object[]{700, "老猫精", "吃老鼠"});

        //3. 调用
        //说明：返回结果是一个数组，每个元素对应上面的sql语句对表的影响记录数
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        //输出
        for (int anInt : ints) {
            System.out.println(" anInt= " + anInt);
        }

        System.out.println("batch add ok..");
    }


    //查询id=100的monster并封装到Monster实体对象[在实际开发中，非常有用]

    @Test
    public void selectDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //1. 确定API ： queryForObject()
        //public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)

        //2.准备参数
        //组织SQL
        //String sql = "SELECT `id` ,`name` ,`skill` FROM monster WHERE `id` = 100";
        //上面这行sql语句会导致  monsterId=null 不会报错

        String sql = "SELECT `id` AS `monsterId`  ,`name` ,`skill` FROM monster WHERE `id` = 100";
        //Mapper :映射器;映像器;映射程序;变换器;映射对象
        //创建RowMapper实现子类BeanPropertyRowMapper对象
        //使用RowMapper 接口来对返回的数据，进行一个封装-》底层使用的反射->setter
        //这里有一个细节: 你查询的记录的表的字段需要和 Monster的对象字段名保持一致
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper(Monster.class);

        Monster monster = jdbcTemplate.queryForObject(sql, rowMapper);

        System.out.println("查询回来的monster= " + monster);

        System.out.println("查询ok");


    }


    //查询id>=200的monster并封装到Monster实体对象

    /**
     * 查询多条记录
     */
    @Test
    public void selectMulDataByJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        //组织SQL
        //通过BeanPropertyRowMapper获取rowMapper 是一个接口，可以将查询的结果，封装到你指定的Monster对象中.

        //1.    确定API
        //public <T> T query(String sql, RowMapper<T> rowMapper, Object... args){}
        //2. 组织参数
        String sql = "SELECT id AS monsterId, NAME, skill FROM monster WHERE id >= ?";
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        //3. 调用
        List<Monster> monsterList = jdbcTemplate.query(sql, rowMapper);

        for (Monster monster : monsterList) {
            System.out.println("monster= " + monster);
        }
    }

    //查询返回结果只有一行一列的值，比如查询id=100的怪物名

    /**
     * 查询返回结果只有一行一列的值
     */
    @Test
    public void selectScalarByJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //1. 确定API
        //public <T> T queryForObject(String sql, Class<T> requiredType)
        //2. 提供参数
        String sql = "SELECT NAME FROM monster WHERE id = 100";
        //Class<T> requiredType 表示你返回的单行单列的数据类型

        String name =
                jdbcTemplate.queryForObject(sql, String.class);
        System.out.println("返回name= " + name);

    }

    //使用Map传入具名参数完成操作，比如添加 螃蟹精.:name 就是具名参数形式需要使用NamedParameterJdbcTemplate 类, 语句形式: String sql = "INSERT INTO monster VALUES(:my_id, :name, :skill)";

    /**
     * 使用Map传入具名参数完成操作，比如添加
     */
    @Test
    public void testDataByNamedParameterJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到NamedParameterJdbcTemplate bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                ioc.getBean(NamedParameterJdbcTemplate.class);

        //1. 确定使用API
        //public int update(String sql, Map<String, ?> paramMap)
        //2. 准备参数 [:my_id, :name, :skill] 要求按照规定的名字来设置参数
        // 具名参数有什么好处？ 比较明确 传的值 到底是哪个
        // 如果使用的是原来的问号 ? 要求程序员手上必须要有表的具体结构 不然不知道哪个是哪个！！
        //String sql = "INSERT INTO monster VALUES(:id, :name, :skill)";

        // 下面的具名参数的名称任意,不需要和数据库表的字段名称相同
        // 作用还是占位 还是相当于以前的? 只不过是用来给程序员提示用的
        String sql = "INSERT INTO monster VALUES(:hspid, :name, :skill)";

        Map<String,Object> paramMap = new HashMap<>();

        //给paramMap填写数据
        paramMap.put("id",800);

        paramMap.put("hspid",802);

        //paramMap.put("id2",801);
        // 如果这里的参数id2 写错了 会报错
        // org.springframework.dao.InvalidDataAccessApiUsageException:
        // No value supplied for the SQL parameter 'id': No value registered for key 'id'
        //没有为SQL参数“id”提供值：没有为键“id”注册值

        paramMap.put("name","小蚂蚁精");
        paramMap.put("skill","打洞");

        //3. 调用
        int affected = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println("add ok affected=" + affected);

    }




    /**
     * 使用Spring Framework中的NamedParameterJdbcTemplate类通过两种不同的方法来执行数据库操作。
     * 具体来说，它演示了如何使用Map和SqlParameterSource来传递具名参数。下面是这两种方法的主要区别：
     * 使用Map传递参数
     * 灵活性：通过Map传递参数允许在运行时动态构建参数集。这意味着可以根据需要在Map中添加或删除参数。
     * 显式声明：在这种方法中，参数是显式地放入Map中的，这使得代码在某种程度上更容易理解和维护。
     * 参数名称和值的分离：参数的名称和值是分开的。你需要手动将每个参数的值放入Map中。
     * 使用SqlParameterSource传递参数
     * 对象映射：SqlParameterSource（特别是BeanPropertySqlParameterSource的实例）允许直接从一个对象映射参数。这意味着可以将一个Java对象（如Monster实例）的属性直接映射为SQL参数。
     * 减少样板代码：这种方法减少了手动添加参数值的需要，因为它是从对象的属性中自动提取的。
     * 类型安全：由于参数是从对象的属性中直接获取的，这种方法在某种程度上提供了更好的类型安全性。
     * 总结
     * 使用Map传递参数更加灵活，适合于参数集在运行时可能变化的情况。
     * 使用SqlParameterSource（尤其是通过BeanPropertySqlParameterSource）则更适合于那些已经有现成对象，且对象属性与数据库列直接映射的情况。
     * 在您的代码中，第一个测试方法使用Map传递参数，而第二个测试方法使用SqlParameterSource。选择哪种方法取决于具体的应用场景和开发者的偏好。
     */
    //使用sqlparametersoruce 来封装具名参数,还是添加一个Monster 狐狸精
    @Test
    public void operDataBySqlparametersoruce() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到NamedParameterJdbcTemplate bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                ioc.getBean(NamedParameterJdbcTemplate.class);

        //确定API
        //public int update(String sql, SqlParameterSource paramSource)
        //public BeanPropertySqlParameterSource(Object object)
        //准备参数 下面的这行sql会报错
        //InvalidDataAccessApiUsageException: No value supplied for the SQL parameter 'Id': Invalid property 'Id' of bean class [com.hspedu.spring.bean.Monster]: Bean property 'Id' is not readable or has an invalid getter method: Does the return type of the getter match the parameter type of the setter?
        //报错的原因是Monster的属性monsterId 和 下面填在 VALUES(:id )的 id 不匹配
        //因此会报错

        String sql = "INSERT INTO monster VALUES(:id, :name, :skill)"; //错误的
        //String sql = "INSERT INTO monster VALUES(:monsterId, :name, :skill)"; //正确的
        Monster monster = new Monster(900, "大象精", "搬运木头");
        SqlParameterSource sqlParameterSource =
                new BeanPropertySqlParameterSource(monster);
        //调用
        int affected =
                namedParameterJdbcTemplate.update(sql, sqlParameterSource);

        System.out.println("add ok affected= " + affected);
    }




    @Test
    public void testMonsterDaoSave(){

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        MonsterDao monsterDao = ioc.getBean(MonsterDao.class);

        //创建一个需要保存的Monster对象
        Monster monster = new Monster(1000, "孙悟空", "筋斗云");
        monsterDao.save(monster);

    }



}
