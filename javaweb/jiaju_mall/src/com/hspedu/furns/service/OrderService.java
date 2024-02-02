package com.hspedu.furns.service;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.Order;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-07-11:02
 */
public interface OrderService {

    // 生成订单的方法 应该返回一个订单号 用于前端展示
    //分析
    //1. 生成订单
    //2. 订单是根据cart来生成, cart对象在session,通过web层，传入saveOrder
    //3. 订单是和一个会员关联
    public String saveOrder(Cart cart,Integer memberId);


    public List<Order> queryOrderByMemberId(Integer memberId);

    // 根据订单id 返回该订单对象数据
    public Order queryOrderById(String id);
}
