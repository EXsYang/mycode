package com.hspedu.springcloud.service.impl;

import com.hspedu.springcloud.dao.OrderDao;
import com.hspedu.springcloud.entity.Order;
import com.hspedu.springcloud.service.AccountService;
import com.hspedu.springcloud.service.OrderService;
import com.hspedu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-10-1:39
 * @description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    // http://localhost:10008/order/save?userId=666&productId=1&nums=1&money=100

    /**
     * 1. @GlobalTransactional: 分布式全局事务控制 io.seata.spring.annotation.GlobalTransactional;
     * 2. name = "hspedu-save-order" 名称,程序员自己指定，保证唯一即可
     *    ，"hspedu-save-order"会保存到seata数据库global_table表的transaction_name字段中
     * 3. rollbackFor = Exception.class ,表示指定什么异常就回滚，这里指定了Exception.class
     *    即，只要发生异常就回滚
     *
     * 注意：
     * 1. openfeign默认超时时间为1s,openfeign调用远程方法超过一秒即超时，会报错
     * 2. @GlobalTransactional 注解的属性 `int timeoutMills() default 60000;`
     *    ,即默认1分钟就会超时，此时就会回滚全局分布式事务
     * 3. 全局分布式事务提交和全局分布式事务回滚都会进行数据清洗，删除中间数据seata数据库三张表的数据，和回滚日志undo_log表中的数据
     *
     * GPT：
     * 当处理Seata的全局分布式事务时，涉及到的主要表包括global_table, branch_table, undo_log, 以及lock_table。这些表在全局分布式事务提交或回滚时的处理方式如下：
     *
     * 全局分布式事务提交:
     *
     * global_table和branch_table: 这些表记录了全局事务及其分支的信息。当全局事务提交成功时，与该事务相关的记录会从这些表中删除。
     * undo_log: 由于事务已成功提交，undo_log表中与该事务相关的回滚日志也会被删除。
     * lock_table: 如果事务涉及到了数据锁定，这些锁定记录将在事务成功提交后从lock_table中移除。这是因为事务提交意味着所有更改已被确认，不再需要保持数据锁定。
     * 全局分布式事务回滚:
     *
     * global_table和branch_table: 与提交操作类似，事务回滚后，这些表中关联到该事务的记录将被删除。
     * undo_log: 在回滚过程中，undo_log表提供了必要的信息来撤销已执行的分支事务对数据库的更改。回滚完成后，相应的undo_log记录会被删除。
     * lock_table: 与提交类似，如果事务在执行过程中对某些数据项加锁，那么在事务回滚后，这些锁定记录也会从lock_table中移除。这确保了数据锁定不会因为未完成的事务而持续存在。
     * 总结：在Seata处理全局分布式事务时，无论是提交还是回滚，系统都会对global_table, branch_table, undo_log, 和lock_table进行相应的数据清理。这保证了事务的完整性和一致性，同时维护了数据库的清洁和效率。
     */
    @Override
    @GlobalTransactional(name = "hspedu-save-order",rollbackFor = Exception.class)
    public void save(Order order) {

        log.info("======创建订单 start======");

        log.info("======本地生成订单 start======");
        orderDao.save(order); //调用本地方法生成订单order
        log.info("======本地生成订单 end======");

        log.info("======扣减库存 start======");
        //远程调用storage微服务扣减库存
        storageService.reduce(order.getProductId(),order.getNums());
        log.info("======扣减库存 end======");

        log.info("======扣减用户余额 start======");
        //远程调用account微服务扣减用户money
        accountService.reduce(order.getUserId(),order.getMoney());
        log.info("======扣减用户余额 end======");

        log.info("======本地修改订单状态 start======");
        //调用本地方法修改订单状态 0->1
        orderDao.update(order.getUserId(),0);
        log.info("======本地修改订单状态 end======");

        log.info("======创建订单 end======");
    }
}
