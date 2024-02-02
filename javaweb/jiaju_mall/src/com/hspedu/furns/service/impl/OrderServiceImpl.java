package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.dao.OrderDAO;
import com.hspedu.furns.dao.OrderItemDAO;
import com.hspedu.furns.dao.daoimpl.FurnDAOImpl;
import com.hspedu.furns.dao.daoimpl.OrderDAOImpl;
import com.hspedu.furns.dao.daoimpl.OrderItemDAOImpl;
import com.hspedu.furns.entity.*;
import com.hspedu.furns.service.OrderService;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author yangda
 * @description:
 * @create 2023-08-07-11:11
 */
public class OrderServiceImpl implements OrderService {


    private OrderDAO orderDAO = new OrderDAOImpl();

    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    private FurnDAO furnDAO = new FurnDAOImpl();

    //老师说明: 这里同学们应该感受javaee分层的好处 , 在service层, 通过组合多个dao的方法
    //完成某个业务, 慢慢体会好处.

    /**
     * 生成订单的方法 应该返回一个订单号 用于前端展示
     * 分析
     * 1. 生成订单
     * 2. 订单是根据cart来生成, cart对象在session,通过web层，传入saveOrder
     * 3. 订单是和一个会员关联
     *
     * @param cart     从session中获取
     * @param memberId 从session中获取
     * @return 返回一个订单号
     */
    @Override
    public String saveOrder(Cart cart, Integer memberId) {
        //这里的业务逻辑相对综合
        //完成任务时将 cart购物车的数据->以order和 orderItem形式保存到db

        //老师说明：因为生成订单会操作多表，因此会涉及到多表事务的问题 ThreadLocal+Mysql事务机制+过滤器
        //关于事务的处理，考虑的点比较多，老师后面专门处理

        //1. 通过cart对象, 构建对应的Order对象
        //先生成一个UUID, 表示当前的订单号, 订单号要保证是唯一
        String orderId = System.currentTimeMillis() + "-" + memberId;

        //根据购物车构建Order对象 和 OrderItem对象
        //Order(String id, Date createTime,
        //        BigDecimal price, Integer status, Integer memberId)
        //根据cart对象 创建订单对象order
        Order order = new Order(orderId, new Date(), cart.getCartTotalPrice(), 0, memberId);
        //将订单对象保存到数据库中 保存order到数据表.
        orderDAO.saveOrder(order);

        //根据cart对象,memberId 创建订单项对象orderItem
        //OrderItem(Integer id, String name, BigDecimal price,
        // Integer count, BigDecimal totalPrice, String orderId)

        //2.通过cart对象 ,遍历出CartItem, 构建OrderItem对象， 并保存到对应的表order_item
        // 需要取出购物车中的购物车项 构建orderItem对象
        //HashMap items = cart.getItems();

        //HashMap<Integer, CartItem> items = cart.getItems();
        //// java基础遍历hashmap
        //Set<Integer> integers = items.keySet();
        //for (Integer id : integers) {
        //    //通过cartItem对象构建了OrderItem
        //    CartItem item = items.get(id);
        //
        //    //根据 购物车项 构建订单项
        //    OrderItem orderItem = new OrderItem(null, item.getName(), item.getPrice(), item.getCount(), item.getTotalPrice(), orderId);
        //
        //    // 将订单项的数据保存到数据库
        //    orderItemDAO.saveOrderItem(orderItem);
        //
        //    // 同时更新furn表中的 库存stock和销量sales
        //    // 根据items的 key => id 获取到数据库中对应的furn对象的数据
        //    //(1) 获取到furn对象
        //    Furn furn = furnDAO.queryFurnById(id);
        //    //(2) 更新一下这个furn的sales销量, stock存量
        //    furn.setSales(furn.getSales() + item.getCount());
        //    furn.setStock(furn.getStock() - item.getCount());
        //    //(3) 更新到数据表
        //    furnDAO.updateFurn(furn);
        //}


        //=======通过entrySet的方式遍历items 取出CartItem===
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {

            CartItem item = entry.getValue();

            //根据 购物车项 构建订单项
            //通过cartItem对象构建了OrderItem
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getPrice(), item.getCount(), item.getTotalPrice(), orderId);

            // 将订单项的数据保存到数据库
            orderItemDAO.saveOrderItem(orderItem);

            // 同时更新furn表中的 库存和销量
            // 根据items的 key => id 获取到数据库中对应的furn对象的数据
            //Furn furn = furnDAO.queryFurnById(entry.getKey());
            Furn furn = furnDAO.queryFurnById(item.getId());
            // 更新furn对象的 销量和库存
            furn.setSales(furn.getSales() + item.getCount());
            furn.setStock(furn.getStock() - item.getCount());

            // 将新的furn对象更新到库中
            furnDAO.updateFurn(furn);
        }

        // 清空购物车 防止反复的下订单 下一次就可以了
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryOrderByMemberId(Integer memberId) {
        return orderDAO.queryOrderByMemberId(memberId);
    }

    @Override
    public Order queryOrderById(String id) {

        return orderDAO.queryOrderById(id);

    }
}
