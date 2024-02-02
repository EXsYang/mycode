package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.OrderItemDAO;
import com.hspedu.furns.dao.daoimpl.OrderItemDAOImpl;
import com.hspedu.furns.entity.OrderItem;
import com.hspedu.furns.service.OrderItemService;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-07-21:13
 */
public class OrderItemServiceImpl implements OrderItemService {
    // 提供属性
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    /**
     * 提供向保存OrderItem对象的方法
     * @param orderItem
     * @return
     */
    @Override
    public Integer saveOrderItem(OrderItem orderItem) {
        return orderItemDAO.saveOrderItem(orderItem);
    }

    /**
     * 根据OrderId 查询OrderItem
     * @param orderId
     * @return 返回OrderItem 集合
     */
    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        return orderItemDAO.queryOrderItemByOrderId(orderId);
    }
}
