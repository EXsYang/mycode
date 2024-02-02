package com.hspedu.furns.dao;

import com.hspedu.furns.entity.OrderItem;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-05-18:38
 */
public interface OrderItemDAO {

    //提供向保存OrderItem对象的方法
    public Integer saveOrderItem(OrderItem orderItem);

    // 根据OrderId 查询OrderItem
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
