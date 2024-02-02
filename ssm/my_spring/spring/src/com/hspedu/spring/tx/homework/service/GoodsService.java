package com.hspedu.spring.tx.homework.service;

import com.hspedu.spring.tx.homework.dao.GoodsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yangda
 * @description:
 * @create 2023-09-20-16:02
 */
@Service
public class GoodsService {


    // 定义属性GoodsDao
    @Resource
    private GoodsDao goodsDao;

    // 编写方法，完成用户购买商品业务
    /**
     *
     * @param userId 用户id
     * @param goodsId 商品id
     * @param amount 购买数量
     */
    public void buyGoods(int userId,int goodsId,int amount){
        // 输出购买相关信息
        System.out.println("用户购买信息 userId= " + userId + " goodsId= " + goodsId + " " +
                "amount= " + amount);

        // 查询商品单价
        Float price = goodsDao.queryPriceById(goodsId);

        // 减少用户账户余额
        goodsDao.updateBalance(userId,price * amount);

        // 减少商品库存 如果下面数据库操作失败
        goodsDao.updateAmount(goodsId,amount);

    }


    //编写一个方法，完成用户购买商品的业务, 这里主要是讲解事务管理
    /**
     * @Transactional 注解解读
     * 1. 使用@Transactional 可以进行声明式事务控制
     * 2. 即将使用该注解标识的方法中，对数据库的操作作为一个事务管理
     * 3. @Transactional 底层使用的仍然是AOP机制
     * 4. 底层是使用动态代理对象来调用buyGoodsByTx
     * 5. 在执行buyGoodsByTx() 方法 先调用 事务管理器(DataSourceTransactionManager)的
     *    doBegin() , 调用 buyGoodsByTx() 如果执行没有发生异常，则调用
     *    事务管理器的 doCommit(), 如果发生异常 调用事务管理器的 doRollback()

     * 注意点 ： 只加一个@Transactional注解不管用
     *  1. 需要在ioc容器配置文件中 配置 DataSourceTransactionManager事务管理器对象/据源事务管理器
     *  2. 同时指定属性 dataSource 数据源 和 容器中配置的 jdbcTemplate 使用的数据源是同一个
     *  3. 最后 加入 开启基于注解的声明式事务管理功能 标签 tx:annotation-driven 并指定
     *     ,你启用的是哪一个数据源事务管理器
     *
     * @param userId 用户id
     * @param goodsId 商品id
     * @param amount 购买数量
     */
    //@Transactional // 等价于下面这种写法 事务传播机制默认是REQUIRED
    //@Transactional(propagation = Propagation.REQUIRED)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyGoodsBuyTx(int userId,int goodsId,int amount){
        // 输出购买相关信息
        System.out.println("用户购买信息 userId= " + userId + " goodsId= " + goodsId + " " +
                "amount= " + amount);

        // 查询商品单价
        Float price = goodsDao.queryPriceById(goodsId);

        // 减少用户账户余额
        goodsDao.updateBalance(userId,price * amount);

        // 减少商品库存 如果下面数据库操作失败
        goodsDao.updateAmount(goodsId,amount);

    }

    // 这个方法是第二套进行商品购买的方法
    //@Transactional
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void buyGoodsBuyTx2(int userId,int goodsId,int amount){
        // 输出购买相关信息
        System.out.println("用户购买信息 userId= " + userId + " goodsId= " + goodsId + " " +
                "amount= " + amount);

        // 查询商品单价
        Float price = goodsDao.queryPriceById2(goodsId);

        // 减少用户账户余额
        goodsDao.updateBalance2(userId,price * amount);

        // 减少商品库存 如果下面数据库操作失败
        goodsDao.updateAmount2(goodsId,amount);

    }

    /**
     * 老师说明
     * 1. 在默认情况下  声明式事务的隔离级别是 mysql的隔离级别 REPEATABLE_READ
     * 2. 我们将buyGoodsByTxISOLATION的隔离级别设置为 Isolation.READ_COMMITTED
     * ,表示只要是提交的数据，在当前事务是可以读取到最新数据
     */
    @Transactional
    //@Transactional(isolation = Isolation.DEFAULT)
    //@Transactional(isolation = Isolation.REPEATABLE_READ)

    //@Transactional(isolation = Isolation.READ_COMMITTED)
    public void buyGoodsByTxISOLATION() {

        //查询两次商品的价格
        Float price = goodsDao.queryPriceById(1);
        System.out.println("第一次查询的price= " + price);

        // 在这里下一个断点卡住程序 然后使用sqlyog 修改表的数据进行隔离级别的测试
        // 测试默认情况下 使用的是 可重复读
        Float price2 = goodsDao.queryPriceById(1);
        System.out.println("第二次查询的price= " + price2);

    }

    /**
     * 老韩解读
     * 1. @Transactional(timeout = 2)
     * 2. timeout = 2 表示 buyGoodsByTxTimeout 如果执行时间超过了2秒
     *    , 该事务就进行回滚.
     * 3. 如果你没有设置 timeout, 默认是 -1，表示使用事务的默认超时时间,
     *    或者不支持
     */
    //@Transactional
    @Transactional(timeout = 2)
    public void buyGoodsByTxTimeout(int userId, int goodsId, int amount) {

        //输出购买的相关信息
        System.out.println("用户购买信息 userId=" + userId
                + " goodsId=" + goodsId + " 购买数量=" + amount);

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById2(goodsId);
        //2. 减少用户的余额
        goodsDao.updateBalance2(userId, price * amount);

        //模拟超时
        System.out.println("=====超时开始4s=====");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====超时结束4s=====");

        //3. 减少库存量
        goodsDao.updateAmount2(goodsId, amount);

        System.out.println("用户购买成功~");


    }


    //增加声明式事务注解
    @Transactional
    public void  buyerBuyGoods (int buyer_id,int seller_id,int taoBao_id,int goodsId, int amount ){

        //1.得到商品的价格
        Float price = goodsDao.queryPriceById2(goodsId);
        goodsDao.updateBuyerAndSellerBalance(buyer_id,seller_id,taoBao_id,amount*price);
        System.out.println("买家购买商品成功！");
    }



}
