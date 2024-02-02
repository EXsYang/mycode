package com.hspedu.spring.tx;

import com.hspedu.spring.tx.dao.GoodsDao;
import com.hspedu.spring.tx.service.GoodsService;
import com.hspedu.spring.tx.service.MultiplyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-20-15:11
 */
public class TxTest {

    @Test
    public void queryPriceById() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);

        Float price = goodsDao.queryPriceById(1);

        System.out.println("查询到的商品price= " + price);
    }


    //测试 减少对应id 账户的余额 同时传进来要减少的多少money
    @Test
    public void updateBalance() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);

        goodsDao.updateBalance(1,10.0f);

        System.out.println("减少用户余额成功！");
    }

    // 减少传进来的商品id的库存 减少的值也传进来
    @Test
    public void updateAmount() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);

        goodsDao.updateAmount(1,1);

        System.out.println("减少用户库存成功！");
    }


    // 购买商品
    @Test
    public void buyGoodsTest() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);

        goodsService.buyGoods(1,1,1);
        System.out.println("购买商品成功");
    }

    // 测试购买商品方法通过事务
    @Test
    public void buyGoodsByTxTest() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);

        goodsService.buyGoodsBuyTx(1,1,1);
        System.out.println("购买商品ByTx 成功");
    }


    // 测试 事务传播机制
    @Test
    public void multiBuyGoodsByTxTest() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("tx_ioc.xml");

        MultiplyService multiplyService = ioc.getBean(MultiplyService.class);

        // 总结：只要是写了REQUIRES_NEW 就会单开一个事务独立 成功就成功 失败就单独回滚
        // 注意：如果在一个声明式事务中 并列执行的两个声明式事务 如果第一个事务出现了异常，
        // 那么就不会执行到第二个事务的语句！ 就直接报错了

        multiplyService.multiBuyGoodsByTx();
        //multiplyService.multiBuyGoodsByTx2();

    }


    //测试声明式事务的隔离级别
    @Test
    public void buyGoodsByTxISOLATIONTest() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);

        goodsService.buyGoodsByTxISOLATION();
    }


    //测试timeout 属性
    @Test
    public void buyGoodsByTxTimeoutTest() {

        //获取到容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);

        //Transaction timed out

        goodsService.buyGoodsByTxTimeout(1,1,1);
    }

}