package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Order;

import java.util.List;

/**
 * @author yangda
 * @description: 订单表的DAO层
 * @create 2023-08-05-13:17
 */
public interface OrderDAO{

    // saveOrder 保存订单方法
    public Integer saveOrder(Order order);

    // 根据用户id 查询所有的订单
    public List<Order> queryOrderByMemberId(Integer memberId);

    // 根据订单id 返回该订单对象数据
    public Order queryOrderById(String id);

}
