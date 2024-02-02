package com.hspedu.spring.tx.homework.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yangda
 * @description:
 * @create 2023-09-20-19:47
 */
@Service
public class MultiplyService {

    // 定义属性
    @Resource
    private GoodsService goodsService;

    /**
     * 老师解读
     * 1. multiBuyGoodsByTx 这个方法 有两次购买商品操作
     * 2. buyGoodsByTx 和 buyGoodsByTx2 都是声明式事务
     * 3. 当前buyGoodsByTx 和 buyGoodsByTx2 使用的传播属性是默认的 REQUIRED [这个含义老师前面讲过了
     *    即会当做一个整体事务进行管理 , 比如buyGoodsByTx方法成功，但是buyGoodsByTx2() 失败，会造成 整个事务的回滚
     *    即会回滚buyGoodsByTx]
     *
     * 4. 如果 buyGoodsByTx 和 buyGoodsByTx2 事务传播属性修改成 REQUIRES_NEW
     *    , 这时两个方法的事务是独立的，也就是如果 buyGoodsByTx成功 buyGoodsByTx2失败
     *    , 不会造成 buyGoodsByTx回滚.
     *
     */

    @Transactional
    public void multiBuyGoodsByTx(){
        //

        // 调用了另一个声明式事务
        // 当第一条默认传播正确 第二条独立传播错误 一二条方法执行前后顺序如何改变都不改变表数据
        // 当第一条 语句正确且独立时 就算第二条语句错误默认传播 也会执行第一条成功

        // 总结：只要是写了REQUIRES_NEW 就会单开一个事务独立 成功就成功 失败就单独回滚
        // 注意：如果在一个声明式事务中 并列执行的两个声明式事务 如果第一个事务出现了异常，
        // 那么就不会执行到第二个事务的语句！ 就直接报错了

        /*以后再回来看*/
        //如果
        // multiBuyGoodsByTx => @Transactional
        // buyGoodsBuyTx  => @Transactional
        // buyGoodsBuyTx2  => @Transactional(propagation = Propagation.REQUIRES_NEW)
        // 这两个方法同时操作数据库中的同一条数据 就会导致死锁
        //goodsService.buyGoodsBuyTx(1,1,1);
        //goodsService.buyGoodsBuyTx2(1,1,1);
        // 这里的知识涉及数据库互斥锁 乐观锁&悲观锁等知识... 以后再回来看

        /* 参考1
        这就是问题的关键了，methodA()方法在新建的事务中执行，调用methodB()时，
        methodB()将methodA()的事务挂起，并且以非事务执行。这样导致methodA()的
        update school一直没提交，methodB()又去select school,造成了死锁（互斥锁）
        。因为methodA()的事务被挂起，methodB()没有事务，也不会发生事务超时。
        日志只能看到很久之后，dbserver close connection 的日志。无法定位程序问题。
        最后将schoolIds 的获取放到methodA()中，并将schoolIds作为参数传入methodB()中解决。

        链接：https://www.jianshu.com/p/bf523acc2f68

         参考2 https://juejin.cn/post/7200264747450531897#comment


     */


        goodsService.buyGoodsBuyTx(1,1,1);

        // 注意上面的声明式事务的方法 如果执行过程出现异常 后面的代码就不走了！
        //System.out.println("第一个方法正常执行完");
        // 调用了另一个声明式事务
        goodsService.buyGoodsBuyTx2(1,1,1);

    }




}
