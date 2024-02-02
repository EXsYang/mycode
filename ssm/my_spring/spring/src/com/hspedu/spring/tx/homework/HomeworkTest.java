package com.hspedu.spring.tx.homework;

import com.hspedu.spring.tx.homework.dao.GoodsDao;
import com.hspedu.spring.tx.homework.service.GoodsService;
import com.hspedu.spring.tx.homework.service.MultiplyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-20-22:37
 */
public class HomeworkTest {

    @Test
    public void queryPriceByIdTest(){

        // 先测试一下 jdbcTemplate 对象操作数据库 配置一下jdbcTemplate

        ApplicationContext ioc = new ClassPathXmlApplicationContext("shopping_ioc.xml");

        //GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        //
        //Float price = goodsDao.queryPriceById(1);
        //System.out.println("查询到的price= " + price);

        // 配置到这里 jdbcTemplate 好使了

        // 继续配置 使用声明式事务
        // 测试不使用事务购买商品
        //GoodsService goodsService = ioc.getBean(GoodsService.class);
        //goodsService.buyGoods(1,1,1);

        // 测试使用声明式事务购买商品
        //goodsService.buyGoodsBuyTx(1,1,1);
        //只在.java文件中写了@Transactional注解不好使 需要进行配置
        //(1)配置事务管理器对象 DataSourceTransactionManager
        //(2)开启基于注解的声明式事务管理功能 <tx:annotation-driven ...

        // 测试声明式事务的属性 传播机制 隔离级别 超时回滚
        // 完成作业需求
        //测试传播机制
        //MultiplyService multiplyService = ioc.getBean(MultiplyService.class);
        //multiplyService.multiBuyGoodsByTx();

        //测试隔离级别
        //GoodsService goodsService = ioc.getBean(GoodsService.class);
        //goodsService.buyGoodsByTxISOLATION();

        //测试超时回滚
        //goodsService.buyGoodsByTxTimeout(1,1,1);

        // 完成作业需求

        // 测试买家1购买商品
        GoodsService goodsService = ioc.getBean(GoodsService.class);
        // 查询要购买的商品的价格
        goodsService.buyerBuyGoods(1,1,1,1,1);

        // 测试ok






    }
}
