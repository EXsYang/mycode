package com.hspedu.furns.service;

import com.hspedu.furns.entity.OrderItem;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-07-21:12
 */
public interface OrderItemService {

    //提供向保存OrderItem对象的方法
    public Integer saveOrderItem(OrderItem orderItem);

    // 根据OrderId 查询OrderItem
    public List<OrderItem> queryOrderItemByOrderId(String orderId);

}
