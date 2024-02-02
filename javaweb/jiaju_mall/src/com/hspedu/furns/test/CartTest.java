package com.hspedu.furns.test;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description:
 * @create 2023-08-02-19:33
 */
public class CartTest {



    @Test
    public void addCartItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"沙发发",2,new BigDecimal(10),new BigDecimal(10)));
        //cart.addItem(new CartItem(1,"沙发发",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"沙发发",2,new BigDecimal(10),new BigDecimal(10)));
        System.out.println(cart);
    }
}
