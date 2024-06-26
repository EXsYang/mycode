package com.hspedu.furns.entity;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description:
 * @create 2023-08-05-13:11
 */
public class OrderItem {

//   -- 创建订单项表
//    CREATE TABLE `order_item`(
//            `id` INT PRIMARY KEY AUTO_INCREMENT,-- 订单项的id
//`name` VARCHAR(64) NOT NULL, -- 家居名
//`price` DECIMAL(11,2) NOT NULL,-- 家居价格
//`count`	INT NOT NULL, -- 数量
//`total_price` DECIMAL(11,2) NOT NULL,-- 订单项的总价
//`order_id` VARCHAR(64) NOT NULL -- 对应的订单号
//)CHARSET utf8 ENGINE INNODB;

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;
    private String orderId;

    //一定要提供无参构造器, 否则反射会出问题.
    public OrderItem() {
    }

    public OrderItem(Integer id, String name, BigDecimal price, Integer count, BigDecimal totalPrice, String orderId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
