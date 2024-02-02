package com.hspedu.furns.test;

import com.hspedu.furns.dao.OrderItemDAO;
import com.hspedu.furns.dao.daoimpl.OrderItemDAOImpl;
import com.hspedu.furns.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description:
 * @create 2023-08-05-18:48
 */
public class OrderItemDAOTest {

    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void saveOrderItem(){
        // 创建一个OrderItem对象
        OrderItem orderItem = new OrderItem(null, "简约小台灯",
                new BigDecimal(40), 2, new BigDecimal(80), "sn-00002");
        //
        Integer affectedRow = orderItemDAO.saveOrderItem(orderItem);
        System.out.println(affectedRow);
    }
    @Test
    public void queryOrderItemByOrderId(){

        System.out.println(orderItemDAO.queryOrderItemByOrderId("1691403140053-10"));
    }
}
