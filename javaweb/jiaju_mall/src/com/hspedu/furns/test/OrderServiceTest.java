package com.hspedu.furns.test;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.CartItem;
import com.hspedu.furns.service.OrderService;
import com.hspedu.furns.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description:
 * @create 2023-08-07-11:57
 */
public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void saveOrder(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(245,"2简约风格小椅子",2,new BigDecimal(180.00),new BigDecimal(360.00)));
        cart.addItem(new CartItem(246,"3典雅风格小台灯",2,new BigDecimal(180.00),new BigDecimal(360.00)));

        String orderId = orderService.saveOrder(cart, 11);

        System.out.println(orderId);


    }
}
