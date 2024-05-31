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
     */
    @Transactional//开启事务
    @Test
    public void testTrans(){

        User user = new User();
        user.setUname("铁锤333");
        userMapper.insert(user);

        System.out.println("user.getId() = " + user.getId());

        User user2 = userMapper.selectById(user.getId());
        System.out.println("user2 = " + user2);

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
