package com.hspedu.furn.test;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.dao.FurnMapper;
import com.hspedu.furn.service.FurnService;
import com.hspedu.furn.service.impl.FurnServiceImpl;
import org.apache.ibatis.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangda
 * @create 2023-11-14-12:56
 * @description:
 */
public class FurnServiceTest {

    //属性
    private ApplicationContext ioc;

    // 从spring容器中,获取的是furnService接口对象/代理对象
    // furnService也是一个代理对象!!!
    // SSM项目中 我们都是通过接口来去使用 furnService 的
    // 而不可以写死成 FurnServiceImpl furnServiceImpl;
    // 没有这样去用的!
    // 在Service层 返回的对象依然是一个代理对象 这是它的开发的一个规则!!!
    // 在DAO层 使用扫描器MapperScannerConfigurer 扫描所有的 dao 接口的实现，加入到 ioc 容器中
    // 导致DAO层从ioc容器中获取 扫描进去的mapper接口实现类的对象,返回的是代理对象
    // 而在Service层 @Resource又自动装配了 通过扫描器扫描进ioc容器中的mapper接口的对象
    // 又导致 在Service层 返回的对象依然是一个代理对象
    private FurnService furnService;

    @Before
    public void init(){
        // 1. 首先获取 ioc 容器
         ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 可以使用 接口的方式 获取 furnMapper

        // 1.按照接口类型FurnService.class 获取furnService!! 不要乱写
        // 2.furnService接口对象/代理对象
        furnService = ioc.getBean(FurnService.class);
        // 查看获取到的furnService 是否为代理对象 结果:是代理对象
        System.out.println("furnService= " + furnService.getClass());
        // furnService= class com.sun.proxy.$Proxy21
    }


    @Test
    public void save(){
        // 1. 首先获取 ioc 容器
        // ApplicationContext ioc =
        //         new ClassPathXmlApplicationContext("applicationContext.xml");

        // 可以使用 接口的方式 获取 furnMapper

        // 2.按照接口类型 获取furnService!!
        // FurnService furnService = ioc.getBean(FurnService.class);

        // 按照FurnServiceImpl.class来获取bean会报错 NoSuchBeanDefinitionException: No qualifying bean of type 'com.hspedu.furn.service.impl.FurnServiceImpl' available
        // 是按照接口类型来获取furnService的代理对象的!!!
        // FurnServiceImpl furnServiceImpl  = ioc.getBean(FurnServiceImpl.class);

        // 查看获取到的furnService 是否为代理对象 结果:是代理对象
        // System.out.println("furnService= " + furnService.getClass());
        // furnService= class com.sun.proxy.$Proxy20

        //如果这里 OK, 说明 spring 和 mybatis 整合 OK
        //System.out.println(furnMapper);
        Furn furn =
                new Furn(null, "北欧风格沙发!!@@@33", "顺平家居@@@", new BigDecimal(180), 666, 27, " ");
        //

        furnService.save(furn);
        // 保存完之后 它使用的连接会自动释放回连接池 不用管它
        // transactional SqlSession 事务性的连接,是因为 在spring配置文件中配置了
        // 配置/开启基于 XML配置+切入表达式来配置 声明式事务管理功能 <tx:advice id="txAdvice">

        // Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@59a008ba]
        // 正在释放事务性SqlSession
        // Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@59a008ba]
        // Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@59a008ba]
        // Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@59a008ba]


        System.out.println("保存 OK");
    }


    @Test
    public void findAll() {
        List<Furn> furnList = furnService.findAll();
        for (Furn furn : furnList) {
            System.out.println(furn);
            // 返回给前端的list中 furn对象 都有id
            // Furn{id=1, name='hh桌子1', maker='tt家居1', price=180.00, sales=666, stock=7, imgPath='assets/images/product-image/1.jpg'}

        }
        // System.out.println("furnList= " + furnList);


    }

    @Test
    public void update(){

        Furn furn = new Furn();
        furn.setId(1);
        furn.setName("hh桌子1");
        furn.setMaker("tt家居1");
        // 显示的，设置null
        // furn.setImgPath(null);

        furnService.update(furn);
        // 为什么没有设置img_path 自动生成sql时也会生成该子句 =》img_path = ?
        // 因为Furn对象 的imgPath属性 设置了默认值,因此 就相当于,给该字段设置了值
        // 因为 updateByPrimaryKeySelective() 方法 判断只要 对应的字段不为null就
        // 会自动的生成该字段的sql子句 解决方法 使用setImgPath() 显示的，设置null
        // 即如果不希望没有设置imgPath时 对imgPath字段修改 设置null


        // update furn SET name = ?, maker = ?, img_path = ? where id = ?
        // 显示的，设置null furn.setImgPath(null); 后自动生成的sql如下
        // update furn SET name = ?, maker = ? where id = ?


        System.out.println("更新furnOK");

    }

    @Test
    public void del() {
        furnService.del(1);
        // delete from furn where id = ?
        System.out.println("del ok");

    }


    @Test
    public void findFurnById() {
        Furn furn = furnService.findFurnById(9);
        // delete from furn where id = ?

        System.out.println("furn= " + furn);

        System.out.println("findFurnById ok");

    }

    @Test
    public void findByCondition(){

        // List<Furn> furns = furnService.findByCondition("");
        // List<Furn> furns = furnService.findByCondition(null);
        // List<Furn> furns = furnService.findByCondition("    ");
        // Preparing: select id, name, maker, price, sales, stock, img_path from furn

        List<Furn> furns = furnService.findByCondition("台");
        // ==>  Preparing: select id, name, maker, price, sales, stock, img_path from furn WHERE ( name like ? )
        // ==> Parameters: %台%(String)

        for (Furn furn : furns) {
            System.out.println("furn= " + furn);
        }

    }

}
