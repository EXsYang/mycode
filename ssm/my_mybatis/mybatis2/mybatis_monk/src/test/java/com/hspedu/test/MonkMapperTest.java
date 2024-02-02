package com.hspedu.test;

import com.hspedu.entity.Monk;
import com.hspedu.mapper.MonkMapper;
import com.hspedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-18-18:51
 * @description:
 */
public class MonkMapperTest {

    //定义属性
    private SqlSession sqlSession;

    private MonkMapper monkMapper;


    //编写方法获取连接和mapper对象
    @Before
    public void init(){
        //获取连接
        sqlSession = MyBatisUtil.getSqlSession();
        //获取到MonkMapper对象 class com.sun.proxy.$Proxy7 是一个代理对象
        //,底层使用的动态代理机制，后面我们自己实现底层机制时会讲到
        monkMapper = sqlSession.getMapper(MonkMapper.class);

        System.out.println("monkMapper= " + monkMapper.getClass());
    }

    @Test
    public void addMonk(){
        //monkMapper = sqlSession.getMapper(MonkMapper.class);
        //向数据库中添加一条数据
        for (int i = 0; i < 5; i++) {
            Monk monk = new Monk();
            // id是自增长的 不用设置 AUTO_INCREMENT
            //monk.setId(1);
            monk.setNickname("唐僧" + i);
            monk.setGrade(1);
            monk.setSkill("紧箍咒");
            monk.setSalary(100.0);
            // 日期类型 可以new一个Date()对象传进去 默认是当前系统时间 构造器中可以指定毫秒数
            monk.setBirthday(new Date()); //

            monk.setEntry(new Date());
            monkMapper.addMonk(monk);
            // 给MonkMapper.xml mapper-insert 配置属性
            // useGeneratedKeys="true" keyProperty="id"
            // 可以获取到自增长的主键id 自增长后的值 返回来 在Java程序中通过monster.getId()拿到
            System.out.println("添加的monk生成的主键id= " + monk.getId());
            System.out.println("添加一条记录成功！");
        }


        //如果是增删改，需要提交事务
        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }

    }

    //删除monster
    @Test
    public void delMonk(){
        //问题 这里重复删除不会报错 数据库数据也不会更改
        monkMapper.delMonk(3);
        System.out.println("删除成功");

        //如果是增删改，需要提交事务
        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }



    }

    //修改 Monster
    @Test
    public void updateMonk(){

        Monk monk = new Monk();
        // id是自增长的  AUTO_INCREMENT 更新是按照id来的 这里需要设置id
        monk.setId(7);
        monk.setNickname("猪八戒");
        monk.setGrade(1);
        monk.setSkill("九齿钉耙！！！");
        monk.setSalary(100.0);
        monk.setBirthday(new Date());
        monk.setEntry(new Date());

        monkMapper.updateMonk(monk);

        System.out.println("修改记录成功！");
        //如果是增删改，需要提交事务
        if (sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }

    }

    //查询-根据 id
    @Test
    public void getMonkById(){

        Monk monkById = monkMapper.getMonkById(4);

        System.out.println("monkById= " + monkById);

        System.out.println("根据id查询monk成功~~");

        //查询不用提交 但是关闭资源连接还是必要的
        if (sqlSession != null){
            sqlSession.close();
        }

    }

    //查询所有的 Monster
    @Test
    public void findAllMonk(){

        List<Monk> allMonk = monkMapper.findAllMonk();
        for (Monk monk : allMonk) {
            System.out.println("monk-- " + monk);
        }

        System.out.println("查询所有monk成功~~");
        //查询不用提交 但是关闭资源连接还是必要的
        if (sqlSession != null){
            sqlSession.close();
        }

    }

}
