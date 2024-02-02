package com.hspedu.furns.entity;

import java.math.BigDecimal;

/**
 * 一个购物车项 表示购物车的一项
 * @author yangda
 * @description:
 * @create 2023-08-02-19:09
 */
public class CartItem {

    // 唯一标识 就是家居的id
    private Integer id;
    // 家居名
    private String name;
    // 数量
    private Integer count;
    // 家居价格
    private BigDecimal price;
    // 该家居item 总价   因为此id的家居 可能购买多个 所以提供一个总体的价格
    private BigDecimal totalPrice;

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
