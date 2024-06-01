package com.atguigu.shardingjdbcdemo;

import com.atguigu.shardingjdbcdemo.entity.Order;
import com.atguigu.shardingjdbcdemo.entity.OrderItem;
import com.atguigu.shardingjdbcdemo.entity.User;
import com.atguigu.shardingjdbcdemo.maper.OrderItemMapper;
import com.atguigu.shardingjdbcdemo.maper.OrderMapper;
import com.atguigu.shardingjdbcdemo.maper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/*
在 Spring 框架中，`@SpringBootTest` 注解是专门为 Spring Boot 应用中的集成测试设计的。它提供了以下几个关键功能：

1. 启动Spring应用上下文：`@SpringBootTest` 注解确保在测试开始前，Spring Boot 应用的上下文完整启动，就像在生产环境中运行一样。这意味着所有的配置、服务和组件都会被初始化。

2. 自动装配支持：该注解通过 Spring Boot 的自动装配机制自动装配测试类中所需要的组件。这包括但不限于由 Spring 管理的 beans，如通过 `@Autowired` 或 `@Inject` 注入的组件。

3. 环境独立：它允许测试在隔离的环境中执行，确保不会干扰到生产环境或开发环境的数据。同时，你可以通过配置不同的属性来模拟不同的环境设置。

4. 灵活的测试属性：通过给 `@SpringBootTest` 注解添加属性，可以定制应用的启动方式。例如，可以指定运行特定的配置类或不启动web环境（通过设置 `webEnvironment = SpringBootTest.WebEnvironment.NONE`）。

5. 集成测试辅助：与 `@Test` 注解结合使用，使得可以在实际的 Spring Boot 环境中执行全面的集成测试，测试数据库交互、HTTP请求处理等。

总之，`@SpringBootTest` 注解是进行 Spring Boot 应用集成测试时的一个重要工具，它确保测试可以在一个模拟的真实运行环境中执行，同时提供强大的自动装配和配置功能。
*/

@SpringBootTest
class ReadwriteTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    //=======以下分库分表 关联==================================================================

    /**
     * 测试关联表插入
     */
    @Test
    public void testInsertOrderAndOrderItem(){

        for (long i = 0; i < 2; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(1L);
            orderMapper.insert(order); //自动主键回填

            for (long j = 0; j < 2; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setUserId(1L);
                orderItem.setOrderId(order.getId());
                orderItem.setPrice(new BigDecimal(10));
                orderItem.setCount(2);
                orderItemMapper.insert(orderItem);
            }
        }

        for (long i = 0; i < 2; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(2L);
            orderMapper.insert(order); //自动主键回填

            for (long j = 0; j < 2; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setUserId(2L);
                orderItem.setOrderId(order.getId());
                orderItem.setPrice(new BigDecimal(5));
                orderItem.setCount(2);
                orderItemMapper.insert(orderItem);
            }
        }

    }
    //=======以下水平分库分表 ==================================================================
    /**
     * 水平分片：分库插入数据测试
     */
    @Test
    public void testInsertOrderDatabaseStrategy(){
        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(i + 1);
            orderMapper.insert(order);
        }
    }


    /**
     * 水平分片：分表插入数据测试
     */
    @Test
    public void testInsertOrderTableStrategy(){
        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(1L);
            orderMapper.insert(order);
        }

        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(2L);
            orderMapper.insert(order);
        }
    }

    //=======以下水平拆表 暂时只测试 order_ds_0.t_order0==================================================================
    /**
     * 水平分片：插入数据测试
     */
    @Test
    public void testInsertOrder(){
        Order order = new Order();
        order.setOrderNo("ATGUIGU001");
        order.setUserId(1L);
        orderMapper.insert(order); //17063 05908 25606 3490 雪花算法生成的19位id整数值
    }

    //=======以下垂直拆库==================================================================
    /**
     * 垂直分片：插入数据测试
     */
    @Test
    public void testInsertOrderAndUser(){

        User user = new User();
        user.setUname("强哥");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("ATGUIGU001");
        order.setUserId(user.getId());
        //order.setAmount(new BigDecimal(100));
        orderMapper.insert(order);

    }

    /**
     * 垂直分片：查询数据测试
     */
    @Test
    public void testSelectFromOrderAndUser(){
        User user = userMapper.selectById(1L);
        Order order = orderMapper.selectById(1L);
    }

    //=======以下读写分离==================================================================

    /**
     * 写入数据的测试
     */
    @Test
    public void testInsert(){
        User user = new User();
        user.setUname("张三丰");
        userMapper.insert(user);
    }

    /**
     * 负载均衡测试
     */
    @Test
    public void testSelect(){
        for (int i = 0; i < 100; i++) {
            User user1 = userMapper.selectById(1);
        }
    }

    /**
     * 事务测试
     * 对应 .yaml 文件中的 transactionalReadQueryStrategy 属性
     * 注意:
     * 1. 要想配置的transactionalReadQueryStrategy生效 需要开启事务
     *    如 配置的是 transactionalReadQueryStrategy: PRIMARY
     *    则读写都走同一个库，这里是都走write_ds
     * 2. @Transactional和@Test注解同时使用，会自动进行数据清理,
     *    即插入的数据并不会真正的插入到数据库中,但是会导致主键id已经被使用过了,会继续累加
     *    ,即会占用id,即使数据清理了,数据库测试时自动分配的id值 一旦分配就不会再撤销了.
     *
     *
     */
    @Transactional//开启事务
    @Test
    public void testTrans(){

        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user2.setUname("铁锤2");
        user3.setUname("铁锤3");
        user4.setUname("铁锤4");
        userMapper.insert(user2);
        userMapper.insert(user3);
        userMapper.insert(user4);

        // System.out.println("user.getId() = " + user.getId());

        userMapper.selectById(user2.getId());
        userMapper.selectById(user3.getId());
        userMapper.selectById(user4.getId());
        // System.out.println("user2 = " + user2);

    }

    /**
     * 事务测试
     */
    @Transactional//开启事务
    @Test
    public void testTrans2(){

        User user = new User();
        user.setUname("铁锤333");
        userMapper.insert(user);

        System.out.println("user.getId() = " + user.getId());

        User user1 = userMapper.selectById(1);
        User user2 = userMapper.selectById(2);
        User user3 = userMapper.selectById(3);
        System.out.println("user1 = " + user1);
        System.out.println("user2 = " + user2);
        System.out.println("user3 = " + user3);

    }
}
