package com.hspedu.furns.dao.daoimpl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.OrderItemDAO;
import com.hspedu.furns.entity.OrderItem;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-05-18:40
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {

    /**
     * 提供向保存OrderItem对象的方法
     * @param orderItem
     * @return 受影响的行数
     */
    @Override
    public Integer saveOrderItem(OrderItem orderItem) {
        // 构建sql
        String sql = "INSERT INTO `order_item`( `id`,`name` , `price` , `count`, `total_price`, `order_id`) " +
                "VALUES(?,?,?,?,?,?);";

        return update(sql, orderItem.getId(), orderItem.getName(), orderItem.getPrice(), orderItem.getCount(), orderItem.getTotalPrice(), orderItem.getOrderId());



    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {

        // 构建sql
        String sql = "SELECT `id`,`name` , `price` , `count`, `total_price` `totalPrice`, `order_id` `orderId` FROM `order_item` " +
                "WHERE `order_id` = ?";

        return queryMulti(sql,OrderItem.class,orderId);

    }
}
